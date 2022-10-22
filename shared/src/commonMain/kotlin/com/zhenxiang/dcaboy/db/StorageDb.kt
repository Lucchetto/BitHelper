package com.zhenxiang.dcaboy.db

import com.zhenxiang.dcaboy.model.Exchange

fun createStorageDb(driverFactory: DriverFactory): StorageDb =
    StorageDb(
        driver = driverFactory.createStorageDbDriver(),
        apiKeyAdapter = ApiKey.Adapter(
            exchangeAdapter = Exchange.ColumnAdapter(),
        ),
    )
