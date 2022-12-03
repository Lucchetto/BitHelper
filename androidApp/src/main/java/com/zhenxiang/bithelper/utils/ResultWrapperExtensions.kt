package com.zhenxiang.bithelper.utils

import com.zhenxiang.bithelper.shared.model.ResultWrapper

fun <T, E> ResultWrapper<T, E>.successDataOrNull(): T? = if (this is ResultWrapper.Success) data else null
