package com.zhenxiang.bithelper.shared.model

import com.zhenxiang.bithelper.shared.api.model.ExchangeApiError

sealed class ExchangeApiResponse<T> {

    data class Success<T>(val data: T): ExchangeApiResponse<T>()

    class Error<T>(val error: ExchangeApiError) : ExchangeApiResponse<T>()
}

fun <T, R> ExchangeApiResponse<T>.mapToResult(
    transform: (T) -> ResultWrapper<R, ExchangeApiError>
): ResultWrapper<R, ExchangeApiError> = when (this) {
    is ExchangeApiResponse.Error -> ResultWrapper.Error(error)
    is ExchangeApiResponse.Success -> transform(data)
}
