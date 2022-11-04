package com.zhenxiang.bithelper.shared.repository

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.db.StorageDb
import com.zhenxiang.bithelper.shared.db.mapToListOnIO
import kotlinx.coroutines.flow.Flow

class ApiKeysRepository(private val storageDb: StorageDb) {

    val apiKeysListFlow: Flow<List<ApiKey>>
        get() = storageDb.apiKeyQueries.selectAll().asFlow().mapToListOnIO()

    fun addApiKey(apiKey: ApiKey) {
        storageDb.apiKeyQueries.insert(apiKey)
    }
}