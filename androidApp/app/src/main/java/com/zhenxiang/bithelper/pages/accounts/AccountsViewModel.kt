package com.zhenxiang.bithelper.pages.accounts

import androidx.lifecycle.ViewModel
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.repository.ApiKeysRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AccountsViewModel : ViewModel(), KoinComponent {

    private val apiKeysRepository: ApiKeysRepository by inject()

    val accountListFlow: Flow<List<ApiKey>> = apiKeysRepository.apiKeysListFlow
}