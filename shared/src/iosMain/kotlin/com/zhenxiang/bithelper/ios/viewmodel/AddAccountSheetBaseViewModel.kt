package com.zhenxiang.bithelper.ios.viewmodel

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.repository.ApiKeysRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class AddAccountSheetBaseViewModel : KoinComponent {

    private val apiKeysRepository: ApiKeysRepository by inject()

    fun addAccount(apiKey: ApiKey) {
        apiKeysRepository.addApiKey(apiKey)
    }
}