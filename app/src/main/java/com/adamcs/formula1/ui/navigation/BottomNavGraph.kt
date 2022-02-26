package com.adamcs.formula1.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.adamcs.formula1.ui.screen.NewsScreen
import com.adamcs.formula1.ui.screen.ResultScreen
import com.adamcs.formula1.ui.screen.ScheduleScreen

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.News.route
    ) {
        composable(route = BottomNavItem.News.route) {
            NewsScreen()
        }
        composable(route = BottomNavItem.Results.route) {
            ResultScreen()
        }
        composable(route = BottomNavItem.Schedule.route) {
            ScheduleScreen()
        }
    }
}