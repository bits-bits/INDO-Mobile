package org.bitsandbits.indo

import android.app.Application
import android.content.Context
import org.bitsandbits.indo.di.initKoin
import org.koin.android.ext.koin.androidContext

class AndroidApp: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        initKoin{
            androidContext(this@AndroidApp)
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}