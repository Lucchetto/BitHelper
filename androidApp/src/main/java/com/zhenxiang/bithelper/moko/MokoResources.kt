package com.zhenxiang.bithelper.moko

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.ResourceFormattedStringDesc

@Composable
@ReadOnlyComposable
fun StringResource.composeResource(): String {
    return stringResource(id = resourceId)
}

@Composable
@ReadOnlyComposable
fun ResourceFormattedStringDesc.composeResource(): String {
    return toString(LocalContext.current)
}
