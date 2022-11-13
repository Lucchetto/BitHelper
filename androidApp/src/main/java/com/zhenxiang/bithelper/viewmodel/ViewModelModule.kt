package com.zhenxiang.bithelper.viewmodel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { parameters ->
        val apiKeyId = parameters.get<Long>()
        AccountDetailsViewModel(get(parameters = { parametersOf(apiKeyId) }))
    }
}
