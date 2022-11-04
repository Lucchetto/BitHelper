package com.zhenxiang.bithelper.pages.apikeyslist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.res.SharedRes

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ApiKeysListPage(navController: NavController, viewModel: ApiKeysListViewModel) {

    val apiKeysListState = viewModel.apiKeysListFlow.collectAsStateWithLifecycle(
        emptyList()
    )

    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = {
            Fab {
                navController.navigate("add")
            }
        },
        content = { ApiKeysList(it, apiKeysListState) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    return TopAppBar(
        title = { Text(text = stringResource(id = SharedRes.strings.api_keys_list_page_title.resourceId)) },
    )
}

@Composable
private fun Fab(onClick: () -> Unit) {
    return FloatingActionButton(
        onClick = onClick
    ) {
        Icon(imageVector = Icons.Outlined.Add, "Add")
    }
}

@Composable
private fun ApiKeysList(paddingValues: PaddingValues, state: State<List<ApiKey>>) {

    val list: List<ApiKey> by state

    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        items(list.size) {
            Text(list[it].toString())
        }
    }
}
