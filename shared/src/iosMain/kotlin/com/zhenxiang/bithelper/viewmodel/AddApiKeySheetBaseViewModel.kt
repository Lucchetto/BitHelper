package com.zhenxiang.bithelper.viewmodel

import com.zhenxiang.bithelper.db.ApiKey
import com.zhenxiang.bithelper.db.StorageDb
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class AddApiKeySheetBaseViewModel: KoinComponent {

    private val storageDb: StorageDb by inject()

    fun addApiKey(apiKey: ApiKey) {
        storageDb.apiKeyQueries.insert(apiKey)
    }
}