package com.zhenxiang.bithelper.navigation

import android.os.Parcelable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhenxiang.bithelper.foundation.ModalBottomSheetDefaults
import com.zhenxiang.bithelper.foundation.top
import com.zhenxiang.bithelper.moko.composeResource
import com.zhenxiang.bithelper.navigation.model.NavigationBarItem
import com.zhenxiang.bithelper.navigation.model.createSharedScaffoldRouteBuilder
import com.zhenxiang.bithelper.pages.accounts.AddAccountSheet
import com.zhenxiang.bithelper.pages.accounts.AccountsPage
import com.zhenxiang.bithelper.shared.res.SharedRes
import dev.olshevski.navigation.reimagined.*
import dev.olshevski.navigation.reimagined.material.BottomSheetNavHost
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainNavigationComponent(
) {

    val navController = rememberNavController<MainNavigationScreen>(MainNavigationScreen.Accounts,)
    val sheetController = rememberNavController<MainNavigationSheet>(emptyList())

    val accountsRouteBuilder = createSharedScaffoldRouteBuilder(
        fab = {
            AccountsPage.Fab {
                sheetController.navigate(MainNavigationSheet.AddAccount)
            }
        }
    ) {
        AccountsPage.RouteContent(viewModel())
    }

    val currentDestination = navController.backstack.entries.last().destination
    val currentNavigationBarItem = MainNavigationScreensMap[currentDestination]
    val currentRouteBuilder = when (currentDestination) {
        MainNavigationScreen.Accounts -> accountsRouteBuilder
    }

    NavBackHandler(navController)

    Scaffold(
        topBar = { TopBar(currentNavigationBarItem, currentRouteBuilder.scrollBehavior) },
        floatingActionButton = currentRouteBuilder.fab,
        bottomBar = {
            BottomNavigation(
                currentDestination,
            ) { screen ->
                if (!navController.moveToTop { it == screen }) {
                    navController.navigate(screen)
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .nestedScroll(currentRouteBuilder.scrollBehavior.nestedScrollConnection)
                .padding(it)
        ) {
            NavHost(navController) { screen ->
                when (screen) {
                    MainNavigationScreen.Accounts -> accountsRouteBuilder.content()
                }
            }
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
            MainNavigationSheet.AddAccount -> AddAccountSheet(sheetController = sheetController, viewModel = viewModel())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(navigationBarItem: NavigationBarItem?, scrollBehavior: TopAppBarScrollBehavior) {
    LargeTopAppBar(
        title = {
            Text(text = navigationBarItem?.title?.composeResource() ?: "")
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun BottomNavigation(
    currentDestination: MainNavigationScreen,
    onChangeDestination: (MainNavigationScreen) -> Unit
) {

    NavigationBar {
        MainNavigationScreensMap.forEach { (screen, item) ->
            NavigationBarItem(
                selected = currentDestination == screen,
                label = { Text(item.title.composeResource()) },
                icon = { Icon(item.icon, contentDescription = null) },
                onClick = {
                    if (currentDestination != screen) {
                        onChangeDestination(screen)
                    }
                }
            )
        }
    }
}

sealed interface MainNavigationScreen : Parcelable {

    @Parcelize
    object Accounts : MainNavigationScreen
}

private val MainNavigationScreensMap = mapOf(
    MainNavigationScreen.Accounts to NavigationBarItem(SharedRes.strings.accounts_page_title, Icons.Outlined.AccountCircle)
)

sealed class MainNavigationSheet : Parcelable {

    @Parcelize
    object AddAccount : MainNavigationSheet()
}