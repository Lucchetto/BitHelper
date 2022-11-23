package com.zhenxiang.bithelper.viewmodel.di

import com.zhenxiang.bithelper.viewmodel.AccountDetailsViewModel
import com.zhenxiang.bithelper.viewmodel.impl.AccountDetailsViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<AccountDetailsViewModel> { parameters ->
        val apiKeyId = parameters.get<Long>()
        AccountDetailsViewModelImpl(get(parameters = { parametersOf(apiKeyId) }))
    }
}
