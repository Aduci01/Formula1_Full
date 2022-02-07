package com.adamcs.formula1.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.adamcs.formula1.ui.theme.scarlet

@Composable
fun BottomBar(navController: NavHostController,
              items: List<BottomNavItem>,
              onItemClick: (BottomNavItem) -> Unit
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    val bgColor = if (currentDestination?.route == BottomNavItem.News.route) Color.Black else Color.White

    BottomNavigation(
        backgroundColor = bgColor
    ) {
        items.forEach { item ->
            AddItem(
                item = item,
                currentDestination = currentDestination,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    item: BottomNavItem,
    currentDestination: NavDestination?,
    onItemClick: (BottomNavItem) -> Unit
) {
    val selected = item.route == currentDestination?.route

    BottomNavigationItem(
        icon = {
            Column(horizontalAlignment = CenterHorizontally) {
                Icon(
                    modifier = Modifier.height(24.dp).width(24.dp),
                    painter = painterResource(item.drawableId),
                    contentDescription = "Navigation Icon"
                )
                
                Spacer(modifier = Modifier.height(2.dp))
                
                if (selected){
                    Text(
                        text = stringResource(item.titleId),
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp)
                }
            }
        },
                
        selected = selected,
        selectedContentColor = scarlet,
        unselectedContentColor = Color.Gray,
        onClick = { onItemClick(item) },
    )
}