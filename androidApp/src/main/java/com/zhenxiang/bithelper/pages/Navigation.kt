package com.zhenxiang.bithelper.pages

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.zhenxiang.bithelper.pages.apikeyslist.AddApiKeySheet
import com.zhenxiang.bithelper.pages.apikeyslist.ApiKeysListPage

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun NavigationComponent(navController: NavHostController, bottomSheetNavigator: BottomSheetNavigator) {
    ModalBottomSheetLayout(bottomSheetNavigator) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            navigation(".", "home") {
                composable(".") {
                    ApiKeysListPage(navController, viewModel = viewModel())
                }

                bottomSheet("add") {
                    AddApiKeySheet(navController, viewModel = viewModel())
                }
            }
        }
    }
}
