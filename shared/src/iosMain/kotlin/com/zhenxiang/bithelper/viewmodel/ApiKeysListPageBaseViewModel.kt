package com.zhenxiang.bithelper.viewmodel

import com.zhenxiang.bithelper.db.ApiKey
import com.zhenxiang.bithelper.flow.FlowWrapper
import com.zhenxiang.bithelper.flow.wrap
import com.zhenxiang.bithelper.repository.ApiKeysRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class ApiKeysListPageBaseViewModel : KoinComponent {

    private val apiKeysRepository: ApiKeysRepository by inject()

    val apiKeysListFlow: FlowWrapper<List<ApiKey>>
        get() = apiKeysRepository.apiKeysListFlow.wrap()
}