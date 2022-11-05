package com.zhenxiang.bithelper.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun ColumnScope.BottomSheetDragHandle() {
    Box(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.onSurfaceVariant,
                RoundedCornerShape(CornerSize(100.dp))
            )
            .width(64.dp)
            .height(4.dp)
            .align(Alignment.CenterHorizontally)
    )
}

object ModalBottomSheetDefaults {

    val elevation = MaterialTheme.elevation.level1

    val contentPadding = MaterialTheme.spacing.level5
}
