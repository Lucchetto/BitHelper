package com.zhenxiang.bithelper.pages.apikeyslist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zhenxiang.bithelper.moko.composeResource
import com.zhenxiang.bithelper.navigation.MainNavigationSheet
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.res.SharedRes
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.navigate

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ApiKeysListPage(sheetController: NavController<MainNavigationSheet>, viewModel: ApiKeysListViewModel) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val apiKeysListState = viewModel.apiKeysListFlow.collectAsStateWithLifecycle(
        emptyList()
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(scrollBehavior) },
        floatingActionButton = {
            Fab {
                sheetController.navigate(MainNavigationSheet.AddAccount)
            }
        },
        content = { ApiKeysList(it, apiKeysListState) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(scrollBehavior: TopAppBarScrollBehavior?) {
    LargeTopAppBar(
        title = { Text(text = SharedRes.strings.api_keys_list_page_title.composeResource()) },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun Fab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(imageVector = Icons.Outlined.Add, SharedRes.strings.add.composeResource())
    }
}

@Composable
private fun ApiKeysList(paddingValues: PaddingValues, state: State<List<ApiKey>>) {

    val list: List<ApiKey> by state

    LazyColumn(
        modifier = Modifier.padding(paddingValues).fillMaxWidth()
    ) {
        items(list.size) {
            Text(list[it].toString())
        }
    }
}
