package com.zhenxiang.bithelper.android.pages.apikeyslist

import androidx.lifecycle.ViewModel
import com.zhenxiang.bithelper.db.ApiKey
import com.zhenxiang.bithelper.repository.ApiKeysRepository
import kotlinx.coroutines.flow.Flow

class ApiKeysListViewModel : ViewModel() {

    private val apiKeysRepository = ApiKeysRepository()

    val apiKeysListFlow: Flow<List<ApiKey>> = apiKeysRepository.apiKeysListFlow
}