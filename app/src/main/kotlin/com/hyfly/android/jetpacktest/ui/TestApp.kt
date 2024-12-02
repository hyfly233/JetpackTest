package com.hyfly.android.jetpacktest.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hyfly.android.jetpacktest.navigation.OwnNavHost

@Composable
fun TestApp() {
    OwnNavHost()
}

@Preview
@Composable
fun TestAppPreview() {
    TestApp()
}
