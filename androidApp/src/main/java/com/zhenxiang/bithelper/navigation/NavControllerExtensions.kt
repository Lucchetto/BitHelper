package com.zhenxiang.bithelper.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import dev.olshevski.navigation.reimagined.*

fun <T> NavController<T>.bringToTopOrNavigate(route: T) {
    if (!moveToTop { it == route }) {
        navigate(route)
    }
}

@Composable
fun <T> NavigationBarBackHandler(navController: NavController<T>, initialRoute: T) {
    val currentRoute = navController.backstack.entries.lastOrNull()?.destination

    BackHandler(enabled = currentRoute != initialRoute) {
        navController.bringToTopOrNavigate(initialRoute)
    }
}
