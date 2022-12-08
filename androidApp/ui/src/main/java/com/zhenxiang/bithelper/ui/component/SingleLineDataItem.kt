package com.zhenxiang.bithelper.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.zhenxiang.bithelper.ui.foundation.spacing

@Composable
fun SingleLineListDataItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    skeleton: Boolean = false,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(MaterialTheme.spacing.level5),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val skeletonModifier = Modifier.placeholder(
            visible = skeleton,
            highlight = PlaceholderHighlight.shimmer()
        )

        Text(
            modifier = skeletonModifier,
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            modifier = skeletonModifier,
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
