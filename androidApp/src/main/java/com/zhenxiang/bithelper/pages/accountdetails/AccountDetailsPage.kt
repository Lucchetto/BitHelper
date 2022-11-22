package com.zhenxiang.bithelper.pages.accountdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zhenxiang.bithelper.component.*
import com.zhenxiang.bithelper.foundation.spacing
import com.zhenxiang.bithelper.moko.composeResource
import com.zhenxiang.bithelper.navigation.RootNavigationScreen
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.AssetBalance
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError
import com.zhenxiang.bithelper.shared.res.SharedRes
import com.zhenxiang.bithelper.viewmodel.AccountDetailsViewModel
import com.zhenxiang.bithelper.viewmodel.preview.AccountDetailsViewModelPreviewImpl
import dev.icerock.moko.resources.format
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.pop
import dev.olshevski.navigation.reimagined.rememberNavController
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailsPage(navController: NavController<RootNavigationScreen>, viewModel: AccountDetailsViewModel) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val pages = listOf(
        TabItem(
            title = { Text(SharedRes.strings.overview_title.composeResource()) }
        ) {
            OverviewTab(viewModel.accountApiKeyFlow)
        },
        TabItem(
            title = { Text(SharedRes.strings.assets_title.composeResource()) }
        ) {
            AssetsTab(viewModel.accountBalancesFlow) {
                navController.navigate(RootNavigationScreen.Withdraw(viewModel.apiKeyId, it.ticker))
            }
        }
    )

    Scaffold(
        topBar = { TopBar(navController, scrollBehavior) }
    ) {
        PagerTabView(
            modifier = Modifier
                .padding(it)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            tabs = pages
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(navController: NavController<RootNavigationScreen>, scrollBehavior: TopAppBarScrollBehavior) {
    MediumTopAppBar(
        navigationIcon = {
            NavBackButton {
                navController.pop()
            }
        },
        title = { Text(SharedRes.strings.account_details_page_title.composeResource()) },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun OverviewTab(flow: StateFlow<ResultWrapper<ApiKey, Throwable>>) {
    val accountApiKeyWrapper: ResultWrapper<ApiKey, Throwable> by flow.collectAsStateWithLifecycle()
    val dataItemSpacing = Modifier.padding(MaterialTheme.spacing.level3)
    val value = when (val it = accountApiKeyWrapper) {
        is ResultWrapper.Loading -> null
        is ResultWrapper.Success -> it.data
        is ResultWrapper.Error -> {
            return
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.level3)
            .fillMaxHeight(),
    ) {

        item {
            DataItem(
                modifier = dataItemSpacing,
                icon = Icons.Outlined.LocalOffer,
                title = SharedRes.strings.label_title.composeResource(),
                value = value?.label ?: ""
            )
        }

        item {
            Row(
                modifier = dataItemSpacing,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.level3)
            ) {
                DataItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.AccountBalanceWallet,
                    title = SharedRes.strings.exchange_title.composeResource(),
                    value = value?.exchange?.labelRes?.composeResource() ?: ""
                )

                DataItem(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Outlined.Event,
                    title = SharedRes.strings.added_on_title.composeResource(),
                    value = value?.creationTimestamp?.toString() ?: ""
                )
            }
        }

        item {
            DataItem(
                modifier = dataItemSpacing,
                icon = Icons.Outlined.Key,
                title = SharedRes.strings.api_key_title.composeResource(),
                value = value?.apiKey ?: ""
            )
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun AssetsTab(flow: StateFlow<ResultWrapper<List<AssetBalance>, ExchangeApiError>>, onAssetClicked: (AssetBalance) -> Unit) {
    val result by flow.collectAsStateWithLifecycle()

    when (val it = result) {
        is ResultWrapper.Error -> Box(Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Error"
            )
        }
        else -> LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            if (it is ResultWrapper.Success) {
                items(it.data) { item ->
                    AssetListItem(item) {
                        onAssetClicked(item)
                    }
                }
            } else {
                items(6) {
                    SkeletonAssetListItem()
                }
            }
        }
    }
}

@Composable
private fun SkeletonAssetListItem() {
    SingleLineListDataItem(
        title = "WBTC",
        value = "69.000000 BTC",
        skeleton = true
    )
}

@Composable
private fun AssetListItem(balance: AssetBalance, onClick: () -> Unit) {
    SingleLineListDataItem(
        modifier = Modifier.clickable(onClick = onClick),
        title = balance.ticker,
        value = SharedRes.strings.value_and_unit.format(balance.availableBalance, balance.ticker).composeResource()
    )
}

@Preview(showSystemUi = true)
@Composable
private fun AccountDetailsPagePreview() = AccountDetailsPage(
    rememberNavController(startDestination = RootNavigationScreen.Main),
    AccountDetailsViewModelPreviewImpl()
)
