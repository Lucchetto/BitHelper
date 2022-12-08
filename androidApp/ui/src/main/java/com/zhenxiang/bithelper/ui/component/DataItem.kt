package com.zhenxiang.bithelper.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.zhenxiang.bithelper.ui.foundation.spacing

@Composable
fun DataItem(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    title: String,
    value: String,
) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(MaterialTheme.spacing.level5),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier.padding(start = MaterialTheme.spacing.level4)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun DataItemPreview() = DataItem(
    icon = Icons.Outlined.Email,
    title = "E-mail",
    value = "preview@example.com"
)
