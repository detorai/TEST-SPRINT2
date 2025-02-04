package com.example.sprint_2.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sprint_2.R
import com.example.sprint_2.presentation.ui.theme.Accent
import com.example.sprint_2.presentation.ui.theme.Block

@Composable
fun CommonBottomBar(
    onClickFavour:()-> Unit ,
    onClickBucket:()-> Unit ,
){
    val navigationList = listOf(R.drawable.home_navigation, R.drawable.heart,R.drawable.bag_splash , R.drawable.notif, R.drawable.profile)
    NavigationBar(
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .paint(
                painter = painterResource(R.drawable.navigation_bar),
                contentScale = ContentScale.FillWidth
            ),
        windowInsets = WindowInsets(top = 40.dp)
    ){
        navigationList.forEachIndexed{index, i ->
            if(index == 2){
                NavigationBarItem(
                    selected = false,
                    onClick = {onClickBucket()},
                    icon = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(56.dp).background(Accent, RoundedCornerShape(60.dp)
                            )
                        ) {
                            Icon(
                                painter = painterResource(i),
                                contentDescription = "",
                                tint = Block,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    modifier =  Modifier.offset(y = (-45).dp).wrapContentSize()
                )
                return@forEachIndexed
            }
            NavigationBarItem(
                selected = false,
                onClick = {
                    when (index) {
                        0 -> {}
                        1 -> {onClickFavour()}
                        3 -> {}
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(i),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)

                    )
                }
            )
        }
    }
}