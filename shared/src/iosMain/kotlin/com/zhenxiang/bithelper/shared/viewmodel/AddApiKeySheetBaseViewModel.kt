package com.zhenxiang.bithelper.shared.viewmodel

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.repository.ApiKeysRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class AddApiKeySheetBaseViewModel : KoinComponent {

    private val apiKeysRepository: ApiKeysRepository by inject()

    fun addApiKey(apiKey: ApiKey) {
        apiKeysRepository.addApiKey(apiKey)
    }
}