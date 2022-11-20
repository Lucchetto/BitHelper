package com.zhenxiang.bithelper.ios.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class CoroutineViewModel {

    protected val viewModelScope: CoroutineScope = MainScope()

    fun onDestroy() {
        viewModelScope.cancel()
    }
}