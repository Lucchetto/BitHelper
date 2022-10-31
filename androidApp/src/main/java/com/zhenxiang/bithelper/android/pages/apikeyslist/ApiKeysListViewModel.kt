package com.zhenxiang.bithelper.android.pages.apikeyslist

import androidx.lifecycle.ViewModel
import com.zhenxiang.bithelper.db.ApiKey
import com.zhenxiang.bithelper.repository.ApiKeysRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ApiKeysListViewModel : ViewModel(), KoinComponent {

    private val apiKeysRepository: ApiKeysRepository by inject()

    val apiKeysListFlow: Flow<List<ApiKey>> = apiKeysRepository.apiKeysListFlow
}