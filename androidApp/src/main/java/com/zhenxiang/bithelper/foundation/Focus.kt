package com.zhenxiang.bithelper.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester

/**
 * Utility that requests focus when the [Composable] appears for the first time
 * Apply the [FocusRequester] to the compose that you desire to autofocus
 */
@Composable
fun AutoFocus(content: @Composable (FocusRequester) -> Unit) {

    val focusRequester = remember { FocusRequester() }

    content(focusRequester)
    LaunchedEffect(key1 = null) {
        focusRequester.requestFocus()
    }
}
