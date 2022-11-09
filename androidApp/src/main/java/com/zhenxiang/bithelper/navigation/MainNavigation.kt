package com.zhenxiang.bithelper.navigation

import android.os.Parcelable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhenxiang.bithelper.foundation.ModalBottomSheetDefaults
import com.zhenxiang.bithelper.foundation.top
import com.zhenxiang.bithelper.pages.apikeyslist.AddApiKeySheet
import com.zhenxiang.bithelper.pages.apikeyslist.ApiKeysListPage
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.material.BottomSheetNavHost
import dev.olshevski.navigation.reimagined.pop
import dev.olshevski.navigation.reimagined.rememberNavController
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainNavigationComponent(
) {
    val navController = rememberNavController<MainNavigationScreen>(
        startDestination = MainNavigationScreen.Accounts,
    )
    val sheetController = rememberNavController<MainNavigationSheet>(
        initialBackstack = emptyList()
    )

    NavBackHandler(navController)

    NavHost(navController) { screen ->
        when (screen) {
            is MainNavigationScreen.Accounts -> ApiKeysListPage(sheetController = sheetController, viewModel = viewModel())
        }
    }

    BackHandler(enabled = sheetController.backstack.entries.isNotEmpty()) {
        sheetController.pop()
    }

    BottomSheetNavHost(
        controller = sheetController,
        onDismissRequest = { sheetController.pop() },
        modifier = Modifier.statusBarsPadding(),
        sheetShape = MaterialTheme.shapes.extraLarge.top(),
        sheetElevation = ModalBottomSheetDefaults.elevation,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetContentColor = contentColorFor(MaterialTheme.colorScheme.surface),
        sheetPropertiesSpec = NavigationConstants.bottomSheetSpec,
    ) { destination ->
        when (destination) {
            MainNavigationSheet.AddAccount -> AddApiKeySheet(sheetController = sheetController, viewModel = viewModel())
        }
    }
}

sealed class MainNavigationScreen : Parcelable {

    @Parcelize
    object Accounts : MainNavigationScreen()
}

sealed class MainNavigationSheet : Parcelable {

    @Parcelize
    object AddAccount : MainNavigationSheet()
}