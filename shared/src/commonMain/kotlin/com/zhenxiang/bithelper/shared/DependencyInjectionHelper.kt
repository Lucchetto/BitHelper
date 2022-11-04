package com.zhenxiang.bithelper.shared

import com.zhenxiang.bithelper.shared.db.databaseModule
import com.zhenxiang.bithelper.shared.repository.repositoryModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

object DependencyInjectionHelper {

    fun initKoin(): KoinApplication = startKoin {
        modules(databaseModule + repositoryModule)
    }
}
