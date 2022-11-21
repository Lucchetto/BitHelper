package com.zhenxiang.bithelper.shared

import com.zhenxiang.bithelper.shared.db.databaseModule
import com.zhenxiang.bithelper.shared.ktor.ktorModule
import com.zhenxiang.bithelper.shared.api.exchangeApiModule
import com.zhenxiang.bithelper.shared.repository.repositoryModule
import com.zhenxiang.bithelper.shared.serialization.serializationModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

object DependencyInjectionHelper {

    fun initKoin(): KoinApplication = startKoin {
        modules(databaseModule + repositoryModule + serializationModule + ktorModule + exchangeApiModule)
    }
}
