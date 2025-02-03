package com.example.sprint_2.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.sprint_2.presentation.ui.theme.Hint
import com.example.sprint_2.presentation.ui.theme.TextColor

@Composable
fun CommonShoesCard(
    onAdd: ()-> Unit,
    onFavourite: ()-> Unit,
    shoes: Shoes,
    ){
        Box(
            modifier = Modifier
                .size(160.dp, 182.dp)
                .background(color = Block, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ){
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(34.dp)
                    .background(color = Accent, shape = RoundedCornerShape(16.dp, 0.dp, 16.dp, 0.dp))
                    .clickable {
                        onAdd()
                    },
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(if (!shoes.inBucket) R.drawable.plus else R.drawable.shop_cart ),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(9.dp)
                    .size(28.dp)
                    .background(color = Background, shape = RoundedCornerShape(40.dp))
                    .clickable {
                        onFavourite()
                    }
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(if (!shoes.isFavourite) R.drawable.heart else R.drawable.heart_fill),
                    contentDescription = "icon",
                    tint = Color.Unspecified
                )
            }
            AsyncImage(
                model = shoes.image,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 18.dp)
                    .size(118.dp, 70.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter)
                    .padding(start = 9.dp, end = 9.dp, bottom = 38.dp)
            ) {
                Text(
                    "BEST SELLER",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 16.sp,
                    color = Accent
                )
                Text(
                    shoes.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 20.sp,
                    color = Hint,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Text(
                shoes.cost.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 16.sp,
                color = TextColor,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 9.dp, bottom = 8.dp)
            )
        }
    }
