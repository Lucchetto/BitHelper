package com.zhenxiang.bithelper.shared.ktor

import com.zhenxiang.bithelper.shared.model.Exchange
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.request.CoreResponseConverter
import de.jensklingenberg.ktorfit.create
import io.ktor.client.request.*

interface KtorfitFactory {

    fun createKtorfitInstance(
        exchange: Exchange,
        responseConverter: CoreResponseConverter?,
        requestModifier: HttpRequestBuilder.() -> Unit
    ): Ktorfit
}

inline fun <reified T> KtorfitFactory.createApiInstance(
    exchange: Exchange,
    responseConverter: CoreResponseConverter? = null,
    noinline requestModifier: HttpRequestBuilder.() -> Unit
): T = createKtorfitInstance(exchange, responseConverter, requestModifier).create()
