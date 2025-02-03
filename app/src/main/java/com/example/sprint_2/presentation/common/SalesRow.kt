package com.example.sprint_2.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sprint_2.domain.sales.Sales
import com.example.sprint_2.presentation.ui.theme.Accent
import com.example.sprint_2.presentation.ui.theme.TextColor

@Composable
fun SalesRow(
    modifier: Modifier,
    onTextClick: ()-> Unit,
    listSales: List<Sales>
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
        modifier = modifier.fillMaxWidth().height(134.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Акции",
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
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {
            itemsIndexed(listSales) { index, sale ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(95.dp)
                ) {
                    AsyncImage(
                        model = sale.sales_url,
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth
                    )
                }
                if (index < listSales.size - 1) {
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}