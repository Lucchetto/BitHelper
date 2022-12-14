package com.zhenxiang.bithelper.moko

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import dev.icerock.moko.resources.StringResource

@Composable
@ReadOnlyComposable
fun StringResource.composeResource(): String {
    return stringResource(id = resourceId)
}
