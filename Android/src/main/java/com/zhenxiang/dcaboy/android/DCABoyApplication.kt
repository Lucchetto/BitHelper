package com.zhenxiang.dcaboy.android

import android.app.Application
import com.zhenxiang.dcaboy.DependencyInjectionHelper
import org.koin.android.ext.koin.androidContext

class DCABoyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DependencyInjectionHelper.initKoin().androidContext(applicationContext)
    }
}