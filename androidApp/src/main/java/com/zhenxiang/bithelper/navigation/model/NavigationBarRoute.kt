package com.zhenxiang.bithelper.navigation.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import dev.icerock.moko.resources.StringResource

interface NavigationBarRoute {

    val route: String

    val title: StringResource

    val icon: @Composable () -> Unit
}
