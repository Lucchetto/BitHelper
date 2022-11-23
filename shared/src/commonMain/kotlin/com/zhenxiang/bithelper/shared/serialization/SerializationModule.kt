package com.zhenxiang.bithelper.shared.serialization

import kotlinx.serialization.json.Json
import org.koin.dsl.module

val serializationModule = module {
    single {
        Json {
            isLenient = true
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
    }
}
