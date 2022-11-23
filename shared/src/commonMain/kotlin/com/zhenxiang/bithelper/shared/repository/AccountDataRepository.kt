package com.zhenxiang.bithelper.shared.repository

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.db.StorageDb
import com.zhenxiang.bithelper.shared.db.mapToOneOrNullOnIO
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.api.ExchangeApiClientProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountDataRepository(
    private val storageDb: StorageDb,
    private val apiClientProvider: ExchangeApiClientProvider
) {

    fun getApiKeyFlow(apiKeyId: Long): Flow<ResultWrapper<ApiKey, Throwable>> =
        storageDb.apiKeyQueries.selectById(apiKeyId).asFlow().mapToOneOrNullOnIO().map {
            if (it == null) {
                ResultWrapper.Error(IllegalArgumentException("apiKeyId $apiKeyId is not valid in database !"))
            } else {
                ResultWrapper.Success(it)
            }
        }

    suspend fun getBalances(apiKey: ApiKey) = apiClientProvider.getInstance(apiKey).getBalances()
}