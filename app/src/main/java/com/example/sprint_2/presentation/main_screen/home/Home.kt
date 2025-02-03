package com.example.sprint_2.presentation.main_screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import com.example.sprint_2.R
import com.example.sprint_2.presentation.common.CategoryRow
import com.example.sprint_2.presentation.common.CommonShopButton
import com.example.sprint_2.presentation.common.PopularRow
import com.example.sprint_2.presentation.common.SalesRow
import com.example.sprint_2.presentation.common.SearchRow
import com.example.sprint_2.presentation.secondary_screen.ScreenType
import com.example.sprint_2.presentation.secondary_screen.SecondaryScreen
import com.example.sprint_2.presentation.ui.theme.TextColor

@Composable
fun Home(
    modifier: Modifier,
    navigator: Navigator,
    viewModel: HomeViewModel
){
    val state = viewModel.state.collectAsState().value
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp)
    ){
        SearchRow(Modifier.padding(top = 21.dp))
        CategoryRow(
            modifier = Modifier.padding(top = 22.dp),
            categories = state.category,
            onClick = {
                navigator.push(SecondaryScreen(screen = ScreenType.CATEGORY))
            }
        )
        PopularRow(
            modifier = Modifier.padding(top = 24.dp),
            shoesList = viewModel.shoesList,
            onTextClick = {},
            onAdd = {},
            onFavourite = {}
        )
        SalesRow(
            modifier = Modifier.padding(top = 29.dp),
            onTextClick = {},
            listSales = state.sales
        )
    }
}

@Composable
fun HomeTopBar(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 48.dp, start = 20.dp, end = 20.dp)
    ) {
        IconButton(
            onClick = { }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.hamburger),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.width(142.dp).height(44.dp)
        ){
            Text(
                "Главная",
                fontWeight = FontWeight.W400,
                fontSize = 32.sp,
                lineHeight = 32.75.sp,
                color = TextColor
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.highlight_05),
                contentDescription = "",
                modifier = Modifier.padding(bottom = 27.dp, end = 128.dp)
            )
        }
        CommonShopButton(
            state = false
        )
    }
}