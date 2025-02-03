package com.example.sprint_2.presentation.common

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sprint_2.domain.category.Category
import com.example.sprint_2.presentation.ui.theme.Block
import com.example.sprint_2.presentation.ui.theme.TextColor

@Composable
fun CategoryRow(
    modifier: Modifier,
    onClick:(Category)-> Unit,
    categories: List<Category>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth().height(75.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Категории",
                color = TextColor
            )
        }
        LazyRow(
            modifier = Modifier.padding(top = 19.dp).fillMaxWidth().height(40.dp),
        ) {
            itemsIndexed(categories) { index, category ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(108.dp, 40.dp)
                            .background(
                                color = Block,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                onClick(category)
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            category.name,
                            fontWeight = FontWeight.W400,
                            fontSize = 12.sp,
                            lineHeight = 12.28.sp,
                            color = TextColor
                        )
                    }
                    if (index < categories.size - 1) {
                        Spacer(Modifier.width(16.dp))
                    }
                }
            }
        }
    }
}