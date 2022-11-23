package com.zhenxiang.bithelper.shared.model

sealed class ResultWrapper<T, E> {

    data class Success<T, E>(val data: T): ResultWrapper<T, E>()

    class Loading<T, E> : ResultWrapper<T, E>()

    data class Error<T, E>(val error: E) : ResultWrapper<T, E>()
}
