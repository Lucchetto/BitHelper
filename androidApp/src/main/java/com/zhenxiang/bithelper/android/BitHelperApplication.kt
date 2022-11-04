package com.zhenxiang.bithelper.android

import android.app.Application
import com.zhenxiang.bithelper.shared.DependencyInjectionHelper
import org.koin.android.ext.koin.androidContext

class BitHelperApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DependencyInjectionHelper.initKoin().androidContext(applicationContext)
    }
}