package com.zhenxiang.bithelper.shared.model


sealed class ApiResponse<T> {

    data class Success<T>(val data: T): ApiResponse<T>()

    class Error<T> : ApiResponse<T>()
}
