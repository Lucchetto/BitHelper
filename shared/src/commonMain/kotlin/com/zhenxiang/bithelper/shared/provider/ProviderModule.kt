package com.zhenxiang.bithelper.shared.provider

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val providerModule = module {
    singleOf<ExchangeAccountDataProviderFactory>(::ExchangeAccountDataProviderFactoryImpl)
}
