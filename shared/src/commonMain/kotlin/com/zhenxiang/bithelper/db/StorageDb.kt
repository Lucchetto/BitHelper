package com.zhenxiang.bithelper.db

import com.zhenxiang.bithelper.model.Exchange

fun createStorageDb(driverFactory: DriverFactory): StorageDb =
    StorageDb(
        driver = driverFactory.createStorageDbDriver(),
        apiKeyAdapter = ApiKey.Adapter(
            exchangeAdapter = Exchange.ColumnAdapter(),
        ),
    )
