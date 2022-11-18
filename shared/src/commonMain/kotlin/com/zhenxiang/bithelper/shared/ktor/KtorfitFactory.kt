package com.zhenxiang.bithelper.shared.ktor

import com.zhenxiang.bithelper.shared.db.ApiKey
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.request.CoreResponseConverter
import de.jensklingenberg.ktorfit.create
import io.ktor.client.request.*

interface KtorfitFactory {

    fun createKtorfitInstance(
        apiKey: ApiKey,
        responseConverter: CoreResponseConverter?,
        requestModifier: HttpRequestBuilder.() -> Unit
    ): Ktorfit
}

inline fun <reified T> KtorfitFactory.createApiInstance(
    apiKey: ApiKey,
    responseConverter: CoreResponseConverter? = null,
    noinline requestModifier: HttpRequestBuilder.() -> Unit
): T = createKtorfitInstance(apiKey, responseConverter, requestModifier).create()
