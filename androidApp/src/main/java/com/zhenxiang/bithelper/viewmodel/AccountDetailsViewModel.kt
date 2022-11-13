package com.zhenxiang.bithelper.viewmodel

import androidx.lifecycle.ViewModel
import com.zhenxiang.bithelper.shared.model.Asset
import com.zhenxiang.bithelper.shared.repository.AccountDataRepository
import kotlinx.coroutines.flow.Flow

class AccountDetailsViewModel(
    private val repository: AccountDataRepository
) : ViewModel() {

    val accountBalance: Flow<List<Asset>>
        get() = repository.getBalances()
}