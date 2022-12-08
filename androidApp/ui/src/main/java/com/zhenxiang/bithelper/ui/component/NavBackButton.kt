package com.zhenxiang.bithelper.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import com.zhenxiang.bithelper.shared.res.SharedRes
import com.zhenxiang.bithelper.ui.utils.composeResource

@Composable
fun NavBackButton(
    onClick: () -> Unit
) = IconButton(onClick = onClick) {
    Icon(Icons.Outlined.ArrowBack, contentDescription = SharedRes.strings.go_back_hint.composeResource())
}
