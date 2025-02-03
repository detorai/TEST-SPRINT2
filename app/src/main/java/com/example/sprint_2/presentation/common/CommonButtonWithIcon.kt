package com.example.sprint_2.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import com.example.sprint_2.presentation.ui.theme.Block

@Composable
fun CommonButtonWithIcon(
    modifier: Modifier,
    onClick: ()-> Unit,
    @DrawableRes icon: Int
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(44.dp)
            .background(color = Block, shape = RoundedCornerShape(40.dp))
            .clickable { onClick() }
    ){
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = "",
            tint = Color.Unspecified
        )
    }
}