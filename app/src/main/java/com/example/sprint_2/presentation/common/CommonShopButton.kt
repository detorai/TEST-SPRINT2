package com.example.sprint_2.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.sprint_2.R
import com.example.sprint_2.presentation.ui.theme.Block
import com.example.sprint_2.presentation.ui.theme.TextColor

@Composable
fun CommonShopButton(
    state: Boolean
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(44.dp)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(44.dp).background(color = Block, shape = RoundedCornerShape(40.dp))
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.bag_splash),
                contentDescription = "icon",
                modifier = Modifier.size(24.dp),
                tint = TextColor
            )
        }
        if (state){
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.buscket_state),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.padding(bottom = 32.dp, start = 31.dp)
            )
        }
    }
}