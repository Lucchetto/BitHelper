package com.zhenxiang.bithelper.shared.ktor

import com.zhenxiang.bithelper.shared.db.ApiKey
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KtorfitFactoryImpl : KtorfitFactory, KoinComponent {

    private val json: Json by inject()

    override fun createKtorfitInstance(
        apiKey: ApiKey,
        extraHttpClientConfig: HttpClientConfig<*>.() -> Unit
    ) = Ktorfit.Builder().build {
        baseUrl(apiKey.exchange.apiUrl)
        httpClient {
            install(ContentNegotiation) {
                json(json)
            }

            extraHttpClientConfig()
        }
    }
}