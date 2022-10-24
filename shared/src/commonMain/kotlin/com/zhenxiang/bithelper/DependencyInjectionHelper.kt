package com.zhenxiang.bithelper

import com.zhenxiang.bithelper.db.databaseModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

object DependencyInjectionHelper {

    fun initKoin(): KoinApplication = startKoin {
        modules(platformModule + databaseModule)
    }
}
