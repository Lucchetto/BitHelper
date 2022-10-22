package com.zhenxiang.dcaboy.android

import android.app.Application
import com.zhenxiang.dcaboy.DependencyInjectionHelper

class DCABoyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DependencyInjectionHelper.initKoin()
    }
}