package com.example.sprint_2.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sprint_2.R
import com.example.sprint_2.domain.shoes.Shoes
import com.example.sprint_2.presentation.ui.theme.Accent
import com.example.sprint_2.presentation.ui.theme.Background
import com.example.sprint_2.presentation.ui.theme.Block
import com.example.sprint_2.presentation.ui.theme.Red
import com.example.sprint_2.presentation.ui.theme.TextColor

@Composable
fun CommonShoesBucketCard(
    shoes: Shoes,
    onDelete: ()-> Unit,
    onUp: ()-> Unit,
    onDown: ()-> Unit,
    modifier: Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .height(104.dp)
            .background(Background)
    ) {
        Box(
            modifier = Modifier
                .width(58.dp)
                .height(104.dp)
                .background(color = Accent, shape = RoundedCornerShape(8.dp))
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 14.dp)
                    .size(15.dp),
                onClick = onUp
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.plus_count),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(14.dp)
                )
            }
            Text(
                "${shoes.count}",
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 14.33.sp,
                modifier = Modifier.align(Alignment.Center),
                color = Block
            )
            IconButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 14.dp)
                    .size(15.dp),
                onClick = onDown
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.minus),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(104.dp)
                .background(color = Block, shape = RoundedCornerShape(8.dp))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 10.dp)
                    .size(87.dp, 85.dp)
                    .background(color = Background, shape = RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = shoes.image,
                    contentDescription = "",
                    modifier = Modifier.size(86.dp, 55.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 127.dp)
                    .height(39.dp)
            ) {
                Text(
                    shoes.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 16.38.sp,
                    color = TextColor
                )
                Text(
                    "₽${shoes.cost}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 14.33.sp,
                    color = TextColor
                )
            }
        }
        Box(
            modifier = Modifier
                .width(58.dp)
                .height(104.dp)
                .background(color = Red, shape = RoundedCornerShape(8.dp))
                .clickable(onClick = { onDelete() }),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.trash),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}
