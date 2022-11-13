package com.zhenxiang.bithelper.shared.repository

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.zhenxiang.bithelper.shared.db.StorageDb
import com.zhenxiang.bithelper.shared.db.mapToOneOrNullOnIO
import com.zhenxiang.bithelper.shared.provider.ExchangeAccountDataProviderFactory
import kotlinx.coroutines.flow.map

class AccountDataRepository(
    val apiKeyId: Long,
    private val storageDb: StorageDb,
    private val providerFactory: ExchangeAccountDataProviderFactory
) {

    fun getBalances() = storageDb.apiKeyQueries.selectById(apiKeyId).asFlow().mapToOneOrNullOnIO().map {
        if (it != null) {
            providerFactory.createInstance(it).getBalances()
        } else {
            emptyList()
        }
    }
}