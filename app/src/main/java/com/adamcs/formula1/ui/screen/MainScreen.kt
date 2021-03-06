package com.adamcs.formula1.ui.screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.adamcs.formula1.ui.navigation.BottomBar
import com.adamcs.formula1.ui.navigation.BottomNavGraph
import com.adamcs.formula1.ui.navigation.BottomNavItem

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem.Results,
        BottomNavItem.News,
        BottomNavItem.Schedule
    )

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                items,
                onItemClick = {
                    if (navController.currentDestination?.route != it.route)
                        navController.navigate(it.route)
                }
            ) }
    ) {
        BottomNavGraph(navController = navController)
    }
}

