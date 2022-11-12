package com.zhenxiang.bithelper.navigation

import androidx.compose.animation.*
import androidx.compose.material.ExperimentalMaterialApi
import dev.olshevski.navigation.reimagined.material.BottomSheetProperties
import dev.olshevski.navigation.reimagined.material.BottomSheetPropertiesSpec

@OptIn(ExperimentalMaterialApi::class)
object NavigationConstants {

    val bottomSheetSpec = BottomSheetPropertiesSpec<Any?> {
        BottomSheetProperties(isSkipHalfExpanded = true)
    }
}
