package com.hyfly.android.jetpacktest.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.hyfly.android.jetpacktest.R

enum class HomeSections(
    @StringRes val title: Int, val icon: ImageVector, val route: String
) {
    HOME(R.string.home_screen, Icons.Outlined.Home, "home"),

    LOGIN(R.string.login, Icons.Outlined.Star, "login"),

    VIEW01(R.string.home_view01, Icons.Outlined.Delete, "view01"),

    VIEW02(R.string.home_view02, Icons.Outlined.Star, "view02"),

    VIEW03(R.string.home_view03, Icons.Outlined.Settings, "view03"),

    VIEW04(R.string.home_view04, Icons.Outlined.Close, "view04"),
}