package com.zhenxiang.bithelper.shared.ktor

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val ktorModule = module {
    singleOf<KtorfitFactory>(::KtorfitFactoryImpl)
}
