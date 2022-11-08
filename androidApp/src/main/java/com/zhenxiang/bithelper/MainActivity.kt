package com.zhenxiang.bithelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.zhenxiang.bithelper.navigation.RootNavigationComponent
import com.zhenxiang.bithelper.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                RootNavigationComponent()
            }
        }
    }
}
