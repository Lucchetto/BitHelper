package com.zhenxiang.bithelper.shared.model

sealed class Result<T, E> {

    data class Success<T, E>(val data: T): Result<T, E>()

    class Loading<T, E> : Result<T, E>()

    class Error<T, E>(error: E) : Result<T, E>()
}
