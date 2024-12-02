package com.hyfly.android.jetpacktest.model

import androidx.compose.runtime.Stable

@Stable
class HomeImgItem(
    val text: Int,
    val drawable: Int,
    val onClickAction: () -> Unit
)


