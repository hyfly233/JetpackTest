package com.hyfly.android.jetpacktest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.screen.HomeScreen
import com.example.test.ui.screen.Login
import com.example.test.ui.screen.View1Screen
import com.example.test.ui.screen.View2Screen
import com.example.test.ui.screen.View3Screen
import com.example.test.ui.screen.View4Screen
import com.hyfly.android.jetpacktest.R
import com.hyfly.android.jetpacktest.model.HomeImgItem

@Composable
fun OwnNavHost() {
    val navController = rememberNavController()

    // 主页面功能列表
    val functionList = listOf(
        HomeImgItem(text = R.string.home_view01,
            drawable = R.drawable.ic_launcher_background,
            onClickAction = { navController.navigate(HomeSections.VIEW01.route) }),
        HomeImgItem(text = R.string.home_view02,
            drawable = R.drawable.ic_launcher_background,
            onClickAction = { navController.navigate(HomeSections.VIEW02.route) }),
        HomeImgItem(text = R.string.home_view03,
            drawable = R.drawable.ic_launcher_background,
            onClickAction = { navController.navigate(HomeSections.VIEW03.route) }),
        HomeImgItem(text = R.string.home_view04,
            drawable = R.drawable.ic_launcher_background,
            onClickAction = { navController.navigate(HomeSections.VIEW04.route) }),
        HomeImgItem(text = R.string.home_view01,
            drawable = R.drawable.ic_launcher_background,
            onClickAction = {}),
        HomeImgItem(text = R.string.home_view01,
            drawable = R.drawable.ic_launcher_background,
            onClickAction = {}),
        HomeImgItem(text = R.string.login,
            drawable = R.drawable.ic_launcher_background,
            onClickAction = { navController.navigate(HomeSections.LOGIN.route) }),
    )

    NavHost(
        navController = navController, startDestination = HomeSections.HOME.route
    ) {
        composable(HomeSections.HOME.route) { HomeScreen(functionList) }

        composable(HomeSections.LOGIN.route) { Login(navController = navController) }

        composable(HomeSections.VIEW01.route) { View1Screen(navController = navController) }
        composable(HomeSections.VIEW02.route) { View2Screen(navController = navController) }
        composable(HomeSections.VIEW03.route) { View3Screen(navController = navController) }
        composable(HomeSections.VIEW04.route) { View4Screen(navController = navController) }
    }
}