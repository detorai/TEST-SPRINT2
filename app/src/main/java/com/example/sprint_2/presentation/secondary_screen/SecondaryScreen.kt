package com.example.sprint_2.presentation.secondary_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.sprint_2.domain.category.Category
import com.example.sprint_2.presentation.common.CategoryRow
import com.example.sprint_2.presentation.common.CommonShoesCard
import com.example.sprint_2.presentation.common.CommonTopBar

data class SecondaryScreen(
    var screen: ScreenType,
    val categoryScreen: Category? = null,
): Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { SecondaryViewModel(screen, categoryScreen) }
        Secondary(viewModel)
    }

    @Composable
    fun Secondary(viewModel: SecondaryViewModel){
        val state = viewModel.screenState.collectAsState().value
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                CommonTopBar(
                    onBack = {navigator.pop()},
                    label = state.label,
                    modifier = Modifier.padding(top = 48.dp),
                    onFavourite = {
                        navigator.push(
                            SecondaryScreen(screen = ScreenType.FAVOURITE)
                        )
                    },
                    screenType = screen
                )
                if (screen == ScreenType.CATEGORY) {
                    CategoryRow(
                        modifier = Modifier.padding(top = 16.dp),
                        categories = state.category,
                        onClick = {}
                    )
                }
            },
        ) { values ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(paddingValues = values)
                    .padding(top = 16.dp)
                    .fillMaxSize()
                    .height(182.dp),
                contentPadding = PaddingValues(vertical = 15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                itemsIndexed(viewModel.shoesList) { index, shoes ->
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CommonShoesCard(
                            shoes = shoes,
                            onAdd = {
                            },
                            onFavourite = {
                            },
                        )
                        if (index < viewModel.shoesList.size - 1) {
                            Spacer(Modifier.width(15.dp))
                        }
                    }
                }
            }
        }
    }
}
enum class ScreenType {
    POPULAR,
    CATEGORY,
    FAVOURITE
}