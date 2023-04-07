package com.inviostajyer.rickandmortycharacters.core

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inviostajyer.rickandmortycharacters.domain.interfaces.LocationRepository
import com.inviostajyer.rickandmortycharacters.view.details.DetailsPage
import com.inviostajyer.rickandmortycharacters.view.home.HomePage
import com.inviostajyer.rickandmortycharacters.view.home.HomeViewModel
import com.inviostajyer.rickandmortycharacters.view.splash.SplashPage

sealed class Pages(var route : String) {
    object SplashPage : Pages("splash_page")
    object HomePage : Pages("home_page")
    object DetailsPage : Pages("details_page")
}

@Composable
fun NavigateHost(
    preferences : SharedPreferences,
    locationRepository: LocationRepository,
    modifier : Modifier = Modifier,
    navController : NavHostController = rememberNavController(),
    startDestination : String = Pages.SplashPage.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(Pages.SplashPage.route) {
            val isFirstOpening = preferences.getBoolean("isFirstOpening", true)

            if(isFirstOpening){
                preferences.edit().putBoolean("isFirstOpening", false).apply()
                SplashPage(navController, "Welcome!")
            }else{
                SplashPage(navController, "Hello!")
            }
        }
        composable(Pages.HomePage.route) {
            val viewModel = HomeViewModel(locationRepository)
            viewModel.getAllLocation()
            HomePage(viewModel)
        }
        composable(Pages.DetailsPage.route) {
            DetailsPage()
        }
    }
}