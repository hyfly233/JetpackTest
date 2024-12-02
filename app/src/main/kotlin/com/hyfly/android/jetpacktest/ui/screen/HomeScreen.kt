package com.example.test.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.ui.component.HomeVerticalGrid
import com.hyfly.android.jetpacktest.R
import com.hyfly.android.jetpacktest.model.HomeImgItem
import com.hyfly.android.jetpacktest.ui.theme.JetpackTestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(functionList: List<HomeImgItem>) {
    JetpackTestTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            "主页面", maxLines = 1, overflow = TextOverflow.Ellipsis
                        )
                    },
                )
            },
        ) { innerPadding ->
            Box(
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                HomeVerticalGrid(
                    items = functionList,
                    Modifier
                        .padding(top = 20.dp)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeScreenPreview() {
    val functionList = listOf(
        HomeImgItem(
            text = R.string.home_view01,
            drawable = R.drawable.ic_launcher_background,
            onClickAction = { }
        ),
        HomeImgItem(
            text = R.string.home_view02,
            drawable = R.drawable.ic_launcher_background,
            onClickAction = { }
        ),
        HomeImgItem(
            text = R.string.home_view03,
            drawable = R.drawable.ic_launcher_background,
            onClickAction = { }
        ),
    )

    HomeScreen(functionList)
}
