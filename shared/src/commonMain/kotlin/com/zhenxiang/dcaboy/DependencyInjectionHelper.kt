package com.zhenxiang.dcaboy

import com.zhenxiang.dcaboy.db.databaseModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

object DependencyInjectionHelper {

    fun initKoin(): KoinApplication = startKoin {
        modules(platformModule + databaseModule)
    }
}
