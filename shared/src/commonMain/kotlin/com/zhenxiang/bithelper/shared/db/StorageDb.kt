package com.zhenxiang.bithelper.shared.db

import com.zhenxiang.bithelper.shared.db.ApiKey
import com.zhenxiang.bithelper.shared.db.StorageDb
import com.zhenxiang.bithelper.shared.model.Exchange

fun createStorageDb(driverFactory: DriverFactory): StorageDb =
    StorageDb(
        driver = driverFactory.createStorageDbDriver(),
        apiKeyAdapter = ApiKey.Adapter(
            exchangeAdapter = Exchange.ColumnAdapter(),
        ),
    )
