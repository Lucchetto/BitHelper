package com.zhenxiang.bithelper.shared.repository

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.db.StorageDb
import com.zhenxiang.bithelper.shared.db.mapToOneOrNullOnIO
import com.zhenxiang.bithelper.shared.model.ResultWrapper
import com.zhenxiang.bithelper.shared.provider.ExchangeAccountDataProviderFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountDataRepository(
    val apiKeyId: Long,
    private val storageDb: StorageDb,
    private val providerFactory: ExchangeAccountDataProviderFactory
) {

    val apiKeyFlow: Flow<ResultWrapper<ApiKey, Throwable>>
        get() = storageDb.apiKeyQueries.selectById(apiKeyId).asFlow().mapToOneOrNullOnIO().map {
            if (it == null) {
                ResultWrapper.Error(IllegalArgumentException("apiKeyId $apiKeyId is not valid in database !"))
            } else {
                ResultWrapper.Success(it)
            }
        }

    suspend fun getBalances(apiKey: ApiKey) = providerFactory.createInstance(apiKey).getBalances()
}