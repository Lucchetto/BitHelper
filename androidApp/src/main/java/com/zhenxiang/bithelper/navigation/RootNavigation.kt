package com.zhenxiang.bithelper.navigation

import android.os.Parcelable
import androidx.compose.runtime.Composable
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.rememberNavController
import kotlinx.parcelize.Parcelize

@Composable
fun RootNavigationComponent(
) {
    val navController = rememberNavController<RootNavigationScreen>(
        startDestination = RootNavigationScreen.Main,
    )

    NavBackHandler(navController)

    NavHost(navController) { screen ->
        when (screen) {
            is RootNavigationScreen.Main -> MainNavigationComponent()
        }
    }
}

sealed class RootNavigationScreen : Parcelable {

    @Parcelize
    object Main : RootNavigationScreen()
}
