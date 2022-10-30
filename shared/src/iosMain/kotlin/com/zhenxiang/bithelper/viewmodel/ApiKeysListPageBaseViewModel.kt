package com.zhenxiang.bithelper.viewmodel

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.zhenxiang.bithelper.db.ApiKey
import com.zhenxiang.bithelper.db.StorageDb
import com.zhenxiang.bithelper.flow.CFlow
import com.zhenxiang.bithelper.flow.NSLooperDispatcher
import com.zhenxiang.bithelper.flow.wrap
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class ApiKeysListPageBaseViewModel: KoinComponent {

    private val storageDb: StorageDb by inject()

    val apiKeysListFlow: CFlow<List<ApiKey>>
        get() = storageDb.apiKeyQueries.selectAll().asFlow().mapToList(NSLooperDispatcher).wrap()
}