package com.zhenxiang.bithelper.ui.foundation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ButtonBar(
    modifier: Modifier = Modifier,
    horizontalArrangement: Alignment.Horizontal = Alignment.End,
    content: @Composable RowScope.() -> Unit
) = Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(Spacing.level5, horizontalArrangement),
    verticalAlignment = Alignment.CenterVertically,
    content = content
)
