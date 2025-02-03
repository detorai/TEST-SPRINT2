package com.example.sprint_2.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.sprint_2.presentation.common.CommonBottomBar
import com.example.sprint_2.presentation.main_screen.home.Home
import com.example.sprint_2.presentation.main_screen.home.HomeTopBar
import com.example.sprint_2.presentation.main_screen.home.HomeViewModel
import com.example.sprint_2.presentation.secondary_screen.ScreenType
import com.example.sprint_2.presentation.secondary_screen.SecondaryScreen
import com.example.sprint_2.presentation.ui.theme.Background

data class MainScreen(
    private var screen: MainScreens
): Screen {
    @Composable
    override fun Content() {
        MainContent()

    }
    @Composable
    fun MainContent(){
        val homeViewModel = rememberScreenModel { HomeViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                when (screen) {
                    MainScreens.HOME -> {
                        HomeTopBar()
                    }
                    MainScreens.FAVOURITE -> {}
                    MainScreens.BUCKET -> {}
                    MainScreens.NOTIFICATION -> {}
                    MainScreens.PROFILE -> {}
                }
            },
            bottomBar = {
                CommonBottomBar()
            },
            modifier = Modifier.fillMaxSize().background(Background),
            content = {padding ->
                when (screen) {
                    MainScreens.HOME -> {
                        Home(
                            modifier = Modifier.padding(padding),
                            viewModel = homeViewModel,
                            navigator = navigator
                        )
                    }
                    MainScreens.FAVOURITE -> {
                        SecondaryScreen(ScreenType.FAVOURITE)
                    }
                    MainScreens.BUCKET -> {}
                    MainScreens.NOTIFICATION -> {}
                    MainScreens.PROFILE -> {}
                }
            }
        )
    }
}
enum class MainScreens {
    HOME,
    FAVOURITE,
    BUCKET,
    NOTIFICATION,
    PROFILE
}