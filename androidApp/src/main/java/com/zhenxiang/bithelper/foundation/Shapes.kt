package com.zhenxiang.bithelper.foundation

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.dp

@Stable
fun CornerSizeZero(): CornerSize = CornerSize(0.dp)

fun CornerBasedShape.top(): CornerBasedShape = copy(
    bottomStart = CornerSizeZero(),
    bottomEnd = CornerSizeZero()
)