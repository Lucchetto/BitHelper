package com.zhenxiang.bithelper.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.zhenxiang.bithelper.foundation.ModalBottomSheetDefaults
import com.zhenxiang.bithelper.foundation.top
import com.zhenxiang.bithelper.navigation.model.NavigationRoute
import com.zhenxiang.bithelper.pages.accountdetails.AccountDetailsPage
import com.zhenxiang.bithelper.pages.accounts.AddAccountSheet
import com.zhenxiang.bithelper.pages.withdraw.WithdrawPage
import com.zhenxiang.bithelper.viewmodel.WithdrawPageViewModel
import com.zhenxiang.bithelper.viewmodel.impl.AccountDetailsViewModelImpl
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun RootNavigationComponent(bottomSheetNavigator: BottomSheetNavigator, navController: NavHostController) {

    ModalBottomSheetLayout(
        bottomSheetNavigator,
        sheetShape = ModalBottomSheetDefaults.shape,
        sheetElevation = ModalBottomSheetDefaults.elevation,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetContentColor = contentColorFor(MaterialTheme.colorScheme.surface),
    ) {
        NavHost(navController = navController, startDestination = RootNavigationScreen.MAIN.route) {
            composable(RootNavigationScreen.MAIN.route) {
                MainNavigationComponent(RootNavigationScreen.MAIN, navController)
            }

            bottomSheet(RootNavigationScreen.ADD_ACCOUNT.route) {
                AddAccountSheet(navController = navController, viewModel = viewModel())
            }

            composable(
                RootNavigationScreen.ACCOUNT_DETAILS.route + "/{${AccountDetailsViewModelImpl.API_KEY_ID_ARG}}",
                arguments = listOf(navArgument(AccountDetailsViewModelImpl.API_KEY_ID_ARG) { type = NavType.LongType })
            ) {
                AccountDetailsPage(navController, koinViewModel())
            }

            composable(
                RootNavigationScreen.WITHDRAW_ASSET.route + "/{${WithdrawPageViewModel.API_KEY_ID_ARG}}" + "/{${WithdrawPageViewModel.ASSET_TICKER_ARG}}",
                arguments = listOf(
                    navArgument(WithdrawPageViewModel.API_KEY_ID_ARG) { type = NavType.LongType },
                    navArgument(WithdrawPageViewModel.ASSET_TICKER_ARG) { type = NavType.StringType }
                )
            ) {
                WithdrawPage(navController, koinViewModel())
            }
        }
    }
}

enum class RootNavigationScreen(
    override val route: String,
): NavigationRoute {

    MAIN("."),
    ADD_ACCOUNT("add_account"),
    ACCOUNT_DETAILS("account_details"),
    WITHDRAW_ASSET("withdraw_asset"),
}
