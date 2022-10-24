package com.zhenxiang.dcaboy.android.pages.apikeyslist

import androidx.lifecycle.ViewModel
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.zhenxiang.dcaboy.android.flow.mapToListOnIO
import com.zhenxiang.dcaboy.db.ApiKey
import com.zhenxiang.dcaboy.db.StorageDb
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ApiKeysListViewModel : ViewModel(), KoinComponent {

    private val storageDb: StorageDb by inject()

    val apiKeysListFlow: Flow<List<ApiKey>>
        get() = storageDb.apiKeyQueries.selectAll().asFlow().mapToListOnIO()

    fun addApiKey(apiKey: ApiKey) {
        storageDb.apiKeyQueries.insert(apiKey)
    }
}