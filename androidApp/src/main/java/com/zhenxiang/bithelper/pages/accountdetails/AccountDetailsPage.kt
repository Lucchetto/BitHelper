package com.zhenxiang.bithelper.pages.accountdetails

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zhenxiang.bithelper.shared.model.Asset
import com.zhenxiang.bithelper.viewmodel.AccountDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun AccountDetailsPage(viewModel: AccountDetailsViewModel) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val accountBalanceState = viewModel.accountBalance.collectAsStateWithLifecycle(emptyList())

    Scaffold(
        topBar = { TopBar(scrollBehavior) }
    ) {
        Content(Modifier.padding(it), accountBalanceState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(scrollBehavior: TopAppBarScrollBehavior) {
    LargeTopAppBar(
        title = { Text("aaaaa") },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun Content(modifier: Modifier, state: State<List<Asset>>) {
    val list: List<Asset> by state

    LazyColumn(
        modifier = modifier
    ) {
        items(list) {
            Text(it.ticker)
        }
    }
}
