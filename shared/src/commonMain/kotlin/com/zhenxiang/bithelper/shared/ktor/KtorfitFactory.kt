package com.zhenxiang.bithelper.shared.ktor

import com.zhenxiang.bithelper.shared.db.ApiKey
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.create
import io.ktor.client.*

interface KtorfitFactory {

    fun createKtorfitInstance(apiKey: ApiKey, extraHttpClientConfig: HttpClientConfig<*>.() -> Unit): Ktorfit
}

inline fun <reified T> KtorfitFactory.createApiInstance(
    apiKey: ApiKey,
    noinline extraHttpClientConfig: HttpClientConfig<*>.() -> Unit
): T = createKtorfitInstance(apiKey, extraHttpClientConfig).create()
