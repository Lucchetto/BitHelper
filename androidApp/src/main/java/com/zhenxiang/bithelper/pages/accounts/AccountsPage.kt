package com.zhenxiang.bithelper.pages.accounts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zhenxiang.bithelper.moko.composeResource
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.res.SharedRes

object AccountsPage {

    @OptIn(ExperimentalLifecycleComposeApi::class)
    @Composable
    fun RouteContent(viewModel: AccountsViewModel) {

        val accountListState = viewModel.accountListFlow.collectAsStateWithLifecycle(emptyList())

        AccountList(accountListState)
    }
    @Composable
    fun Fab(onClick: () -> Unit) {
        FloatingActionButton(
            onClick = onClick
        ) {
            Icon(imageVector = Icons.Outlined.Add, SharedRes.strings.add.composeResource())
        }
    }

    @Composable
    private fun AccountList(state: State<List<ApiKey>>) {
        val list: List<ApiKey> by state

        LazyColumn(
            Modifier.fillMaxWidth()
        ) {
            items(list.size) {
                Text(list[it].toString())
            }
        }
    }
}
