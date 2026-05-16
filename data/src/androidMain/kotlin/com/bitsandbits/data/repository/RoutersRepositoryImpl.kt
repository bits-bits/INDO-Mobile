package com.bitsandbits.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Build
import androidx.core.app.ActivityCompat
import com.bitsandbits.entity.Router
import com.bitsandbits.repository.RoutersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlin.time.Clock.System.now

class RoutersRepositoryImpl(private val context: Context) : RoutersRepository {

    private val wifiManager =
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    private fun hasLocationPermission(): Boolean =
        ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun ScanResult.extractSsid(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            wifiSsid?.toString() ?: SSID
        } else {
            @Suppress("DEPRECATION")
            SSID.removeSurrounding("\"")
        }
    }

    @SuppressLint("MissingPermission")
    override fun getNearbyRouters(): Flow<List<Router>> {
        if (!hasLocationPermission()) return emptyFlow()

        return callbackFlow {

            val receiver = object : BroadcastReceiver() {
                override fun onReceive(ctx: Context, intent: Intent) {

                    val fresh = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)

                    val routers = try {
                        wifiManager.scanResults.map { result ->
                            Router(
                                ssid = result.extractSsid(),
                                bssid = result.BSSID,
                                signalStrength = result.level.toString(),
                                frequency = result.frequency.toString()
                            )
                        }
                    } catch (e: SecurityException) {
                        emptyList()
                    }

                    trySend(routers)

                    // Trigger next scan only if last one was fresh (not throttled)
                    // If throttled, Android will call us back again automatically
                    println("TOOG BOOB FRESH: $fresh")
                    if (fresh) wifiManager.startScan()
                }
            }

            context.registerReceiver(
                receiver,
                IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
            )

            // Kick off the first scan
            wifiManager.startScan()

            awaitClose {
                context.unregisterReceiver(receiver)
            }
        }
    }
}