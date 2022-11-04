package com.zhenxiang.bithelper.pages.apikeyslist

import androidx.lifecycle.ViewModel
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.repository.ApiKeysRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ApiKeysListViewModel : ViewModel(), KoinComponent {

    private val apiKeysRepository: ApiKeysRepository by inject()

    val apiKeysListFlow: Flow<List<ApiKey>> = apiKeysRepository.apiKeysListFlow
}