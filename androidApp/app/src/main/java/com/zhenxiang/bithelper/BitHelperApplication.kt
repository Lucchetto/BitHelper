package com.zhenxiang.bithelper

import android.app.Application
import com.zhenxiang.bithelper.shared.DependencyInjectionHelper
import com.zhenxiang.bithelper.viewmodel.di.viewModelModule
import org.koin.android.ext.koin.androidContext

class BitHelperApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DependencyInjectionHelper.initKoin().modules(viewModelModule).androidContext(applicationContext)
    }
}