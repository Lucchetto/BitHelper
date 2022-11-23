package com.zhenxiang.bithelper.shared.provider.model

sealed class ExchangeApiError {

    object UnknownError : ExchangeApiError()
}
