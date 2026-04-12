package com.bitsandbits.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bitsandbits.designsystem.theme.theme.IndoTheme
import com.bitsandbits.presentation.screens.buildingDetailsScreen.BuildingDetailsScreen
import com.bitsandbits.presentation.screens.login.LoginScreen
import com.bitsandbits.presentation.screens.mainScreen.MainScreen

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("No NavController provided")
}

@Composable
fun IndoNavHost(startDestination: Destinations = Destinations.LoginScreenRoute){
    IndoTheme {
        val navController = rememberNavController()
        CompositionLocalProvider(LocalNavController provides navController){
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = startDestination,
            ){
                composable<Destinations.SplashScreenRoute> { }
                composable<Destinations.OnBoardingScreenRoute> { }
                composable<Destinations.LoginScreenRoute> { LoginScreen() }
                composable<Destinations.HomeScreenRoute> { MainScreen(selectedTab = MainScreenDestinations.Home ) }
                composable<Destinations.MapScreenRoute> { MainScreen(selectedTab = MainScreenDestinations.Map) }
                composable<Destinations.LibraryScreenRoute> { MainScreen(selectedTab = MainScreenDestinations.Library) }
                composable<Destinations.ProfileScreeRoute> { MainScreen(selectedTab = MainScreenDestinations.Profile) }
                composable<Destinations.BuildingDetailsScreenRoute> { BuildingDetailsScreen() }

            }
        }
    }
}