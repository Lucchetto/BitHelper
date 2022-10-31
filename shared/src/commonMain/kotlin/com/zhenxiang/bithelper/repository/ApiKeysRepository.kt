package com.zhenxiang.bithelper.repository

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.zhenxiang.bithelper.db.ApiKey
import com.zhenxiang.bithelper.db.StorageDb
import com.zhenxiang.bithelper.db.mapToListOnIO
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ApiKeysRepository: KoinComponent {

    private val storageDb: StorageDb by inject()

    val apiKeysListFlow: Flow<List<ApiKey>>
        get() = storageDb.apiKeyQueries.selectAll().asFlow().mapToListOnIO()

    fun addApiKey(apiKey: ApiKey) {
        storageDb.apiKeyQueries.insert(apiKey)
    }
}