package com.zhenxiang.bithelper.shared.ktor

import com.zhenxiang.bithelper.shared.db.ApiKey
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.request.CoreResponseConverter
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KtorfitFactoryImpl : KtorfitFactory, KoinComponent {

    private val json: Json by inject()

    override fun createKtorfitInstance(
        apiKey: ApiKey,
        responseConverter: CoreResponseConverter?,
        extraHttpClientConfig: HttpClientConfig<*>.() -> Unit
    ) = Ktorfit.Builder().build {
        baseUrl(apiKey.exchange.apiUrl)
        responseConverter?.let {
            responseConverter(it)
        }
        httpClient {
            install(ContentNegotiation) {
                json(json)
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            extraHttpClientConfig()
        }
    }
}
