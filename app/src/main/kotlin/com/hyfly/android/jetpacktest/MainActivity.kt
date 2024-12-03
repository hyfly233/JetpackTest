package com.hyfly.android.jetpacktest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hyfly.android.jetpacktest.ui.TestApp
import dagger.hilt.android.AndroidEntryPoint

// 边框
val borderModifier = Modifier.border(1.dp, Color.Red)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestApp()
        }
    }
}
