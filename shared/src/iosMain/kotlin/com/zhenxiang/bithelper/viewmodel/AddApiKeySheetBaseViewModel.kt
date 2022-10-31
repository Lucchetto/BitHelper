package com.zhenxiang.bithelper.viewmodel

import com.zhenxiang.bithelper.db.ApiKey
import com.zhenxiang.bithelper.repository.ApiKeysRepository

open class AddApiKeySheetBaseViewModel {

    private val apiKeysRepository = ApiKeysRepository()

    fun addApiKey(apiKey: ApiKey) {
        apiKeysRepository.addApiKey(apiKey)
    }
}