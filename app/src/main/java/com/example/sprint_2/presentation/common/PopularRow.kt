package com.example.sprint_2.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.sprint_2.domain.shoes.Shoes
import com.example.sprint_2.presentation.ui.theme.Accent
import com.example.sprint_2.presentation.ui.theme.TextColor

@Composable
fun PopularRow(
    modifier: Modifier,
    shoesList: List<Shoes>,
    onTextClick: ()-> Unit,
    onAdd: ()-> Unit,
    onFavourite: ()-> Unit
){
    val annotatedText = buildAnnotatedString {
        pushStringAnnotation("click", "click")
        withStyle(
            SpanStyle(
                color = Accent
            )
        ){
            append("Все")
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth().height(236.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Популярное",
                color = TextColor
            )
            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(offset, offset).firstOrNull()
                        ?.let { annotation ->
                            when (annotation.item) {
                                "click" -> onTextClick()
                            }
                        }
                }
            )
        }
        LazyRow(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .height(182.dp)
        ) {
            itemsIndexed(shoesList) { index, shoes ->
                Row(
                    modifier = Modifier.fillMaxWidth().height(182.dp)
                ) {
                    CommonShoesCard(
                        shoes = shoes,
                        onAdd = {
                            onAdd()
                        },
                        onFavourite = {
                            onFavourite()
                        },
                    )
                    if (index < shoesList.size - 1) {
                        Spacer(Modifier.width(15.dp))
                    }
                }
            }
        }
    }
}