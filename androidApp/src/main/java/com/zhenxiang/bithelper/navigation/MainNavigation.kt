package com.zhenxiang.bithelper.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zhenxiang.bithelper.moko.composeResource
import com.zhenxiang.bithelper.navigation.model.NavigationBarRoute
import com.zhenxiang.bithelper.navigation.model.createSharedScaffoldRouteBuilder
import com.zhenxiang.bithelper.pages.accounts.AccountsPage
import com.zhenxiang.bithelper.shared.res.SharedRes
import dev.icerock.moko.resources.StringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigationComponent(
    rootScreen: RootNavigationScreen,
    rootNavController: NavHostController
) {

    val navController = rememberNavController()

    val accountsRouteBuilder = createSharedScaffoldRouteBuilder(
        fab = {
            AccountsPage.Fab {
                rootNavController.navigate(RootNavigationScreen.ADD_ACCOUNT.route)
            }
        }
    ) {
        AccountsPage.RouteContent(rootNavController, viewModel())
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination?.route?.let {
        MainNavigationScreen.values().firstOrNull { item -> item.route == it }
    }
    val currentRouteBuilder = when (currentScreen) {
        MainNavigationScreen.ACCOUNTS -> accountsRouteBuilder
        else -> null
    }

    Scaffold(
        modifier = currentRouteBuilder?.let {
            Modifier.nestedScroll(it.scrollBehavior.nestedScrollConnection)
        } ?: Modifier,
        topBar = { TopBar(currentScreen, currentRouteBuilder?.scrollBehavior) },
        floatingActionButton = currentRouteBuilder?.fab ?: {},
        bottomBar = {
            BottomNavigation(routes = MainNavigationScreen.values(), currentRoute = currentScreen) {
                navController.navigate(it.route)
            }
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            route = rootScreen.route,
            startDestination = MainNavigationScreen.ACCOUNTS.route
        ) {
            composable(MainNavigationScreen.ACCOUNTS.route) {
                accountsRouteBuilder.content()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(currentRoute: NavigationBarRoute?, scrollBehavior: TopAppBarScrollBehavior?) {
    LargeTopAppBar(
        title = {
            Text(text = currentRoute?.title?.composeResource() ?: "")
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun <T: NavigationBarRoute> BottomNavigation(
    routes: Array<T>,
    currentRoute: T?,
    onChangeRoute: (T) -> Unit
) {

    NavigationBar {
        routes.forEach {
            NavigationBarItem(
                selected = currentRoute == it,
                label = { Text(it.title.composeResource()) },
                icon = it.icon,
                onClick = {
                    if (currentRoute != it) {
                        onChangeRoute(it)
                    }
                }
            )
        }
    }
}

enum class MainNavigationScreen(
    override val route: String,
    override val title: StringResource,
    override val icon: @Composable () -> Unit,
) : NavigationBarRoute {

    ACCOUNTS(
        "accounts",
        SharedRes.strings.accounts_page_title,
        { Icon(Icons.Outlined.AccountCircle, contentDescription = null) },
    )
}
