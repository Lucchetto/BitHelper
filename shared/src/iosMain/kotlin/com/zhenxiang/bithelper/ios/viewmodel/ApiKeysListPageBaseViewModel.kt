package com.zhenxiang.bithelper.ios.viewmodel

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.flow.FlowWrapper
import com.zhenxiang.bithelper.shared.flow.wrap
import com.zhenxiang.bithelper.shared.repository.ApiKeysRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class ApiKeysListPageBaseViewModel : KoinComponent {

    private val apiKeysRepository: ApiKeysRepository by inject()

    val apiKeysListFlow: FlowWrapper<List<ApiKey>>
        get() = apiKeysRepository.apiKeysListFlow.wrap()
}