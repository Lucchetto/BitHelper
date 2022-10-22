package com.zhenxiang.bithelper.android

import android.app.Application
import com.zhenxiang.bithelper.DependencyInjectionHelper

class BitHelperApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DependencyInjectionHelper.initKoin()
    }
}