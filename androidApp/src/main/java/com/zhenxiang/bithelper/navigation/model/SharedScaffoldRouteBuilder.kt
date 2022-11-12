package com.zhenxiang.bithelper.navigation.model

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
class SharedScaffoldRouteBuilder internal constructor(
    val scrollBehavior: TopAppBarScrollBehavior,
    val fab: @Composable () -> Unit,
    val content: @Composable () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createSharedScaffoldRouteBuilder(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
    fab: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) = SharedScaffoldRouteBuilder(scrollBehavior, fab, content)
