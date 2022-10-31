package com.zhenxiang.bithelper.viewmodel

import com.zhenxiang.bithelper.db.ApiKey
import com.zhenxiang.bithelper.flow.FlowWrapper
import com.zhenxiang.bithelper.flow.wrap
import com.zhenxiang.bithelper.repository.ApiKeysRepository

abstract class ApiKeysListPageBaseViewModel {

    private val apiKeysRepository = ApiKeysRepository()

    val apiKeysListFlow: FlowWrapper<List<ApiKey>>
        get() = apiKeysRepository.apiKeysListFlow.wrap()
}