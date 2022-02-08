package com.adamcs.formula1.ui.navigation

import com.adamcs.formula1.R

sealed class BottomNavItem(
    val route: String,
    val titleId: Int,
    val drawableId: Int
) {
    object News : BottomNavItem(
        route = "news",
        titleId = R.string.news,
        drawableId = R.drawable.newspaper
    )

    object Results : BottomNavItem(
        route = "results",
        titleId = R.string.rankings,
        drawableId = R.drawable.leaderboard
    )

    object Schedule : BottomNavItem(
        route = "schedule",
        titleId = R.string.schedule,
        drawableId = R.drawable.calendar
    )
}