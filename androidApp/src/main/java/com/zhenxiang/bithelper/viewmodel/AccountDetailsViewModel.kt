package com.zhenxiang.bithelper.viewmodel

import androidx.lifecycle.ViewModel
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import kotlinx.coroutines.flow.Flow

abstract class AccountDetailsViewModel: ViewModel() {

    abstract val accountApiKeyFlow: Flow<ResultWrapper<ApiKey, Throwable>>
}
