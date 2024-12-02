package com.example.test.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyfly.android.jetpacktest.R
import com.hyfly.android.jetpacktest.model.HomeImgItem
import com.hyfly.android.jetpacktest.ui.theme.JetpackTestTheme

@Composable
fun HomeVerticalGrid(items: List<HomeImgItem>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(items.size) { index ->
            HomeImgElement(text = items[index].text,
                drawable = items[index].drawable,
                modifier = Modifier.clickable { items[index].onClickAction() })
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeVerticalGridPreview() {

    val list = List(5) {
        HomeImgItem(text = R.string.home_view01,
            drawable = R.drawable.ic_launcher_background,
            onClickAction = {})
    }

    JetpackTestTheme {
        HomeVerticalGrid(list)
    }
}