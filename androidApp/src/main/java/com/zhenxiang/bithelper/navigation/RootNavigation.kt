package com.zhenxiang.bithelper.navigation

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhenxiang.bithelper.pages.accountdetails.AccountDetailsPage
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.rememberNavController
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RootNavigationComponent(
) {
    val navController = rememberNavController<RootNavigationScreen>(
        startDestination = RootNavigationScreen.Main,
    )

    NavBackHandler(navController)

    NavHost(navController) { screen ->
        when (screen) {
            RootNavigationScreen.Main -> MainNavigationComponent(navController)
            is RootNavigationScreen.AccountDetails -> AccountDetailsPage(
                navController,
                koinViewModel(
                    parameters = { parametersOf(screen.apiKeyId) }
                )
            )
        }
    }
}

sealed interface RootNavigationScreen : Parcelable {

    @Parcelize
    object Main : RootNavigationScreen

    @Parcelize
    data class AccountDetails(val apiKeyId: Long) : RootNavigationScreen
}
