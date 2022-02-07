package com.adamcs.formula1.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.adamcs.formula1.ui.screen.NewsScreen
import com.adamcs.formula1.ui.screen.ResultScreen

@ExperimentalMaterialApi
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Results.route
    ) {
        composable(route = BottomNavItem.News.route) {
            NewsScreen()
        }
        composable(route = BottomNavItem.Results.route) {
            ResultScreen()
        }
    }
}