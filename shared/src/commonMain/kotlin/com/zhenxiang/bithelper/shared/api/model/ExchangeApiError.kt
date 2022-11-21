package com.zhenxiang.bithelper.shared.api.model

sealed class ExchangeApiError {

    object UnknownError : ExchangeApiError()
}
