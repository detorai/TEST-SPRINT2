package com.example.sprint_2.presentation.spalsh

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.sprint_2.R
import com.example.sprint_2.presentation.onboard.OnBoardingScreen
import com.example.sprint_2.presentation.ui.theme.Accent
import com.example.sprint_2.presentation.ui.theme.Block
import kotlinx.coroutines.delay

class SplashScreen: Screen {
    @Composable
    override fun Content() {
        Splash()
    }
    @Composable
    fun Splash(){
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(Unit){
            delay(1000)
            navigator.push(OnBoardingScreen())
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().background(Accent)
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(129.dp).background(color = Block, shape = RoundedCornerShape(120.dp))
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.bag_splash),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
        }
    }
}