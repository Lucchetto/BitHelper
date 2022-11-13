package com.zhenxiang.bithelper.shared.provider.binance

import com.zhenxiang.bithelper.shared.model.ApiResponse
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.SuspendResponseConverter
import de.jensklingenberg.ktorfit.internal.TypeData
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.util.reflect.*

class BinanceResponseConverter : SuspendResponseConverter {

    override fun supportedType(typeData: TypeData, isSuspend: Boolean): Boolean =
        typeData.qualifiedName == ApiResponse::class.qualifiedName

    override suspend fun <RequestType> wrapSuspendResponse(
        typeData: TypeData,
        requestFunction: suspend () -> Pair<TypeInfo, HttpResponse>,
        ktorfit: Ktorfit
    ): Any {
        return try {
            val (info, response) = requestFunction()
            ApiResponse.Success<Any>(response.body(info))
        } catch (ex: Throwable) {
            ApiResponse.Error()
        }
    }
}
