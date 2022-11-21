package com.zhenxiang.bithelper.shared.api

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val exchangeApiModule = module {
    factoryOf<ExchangeApiClientProvider>(::ExchangeApiClientProviderImpl)
}
