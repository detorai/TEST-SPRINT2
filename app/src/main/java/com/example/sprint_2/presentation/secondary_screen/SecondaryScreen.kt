package com.example.sprint_2.presentation.secondary_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.sprint_2.data.local_data_source.shoes.AppDatabase
import com.example.sprint_2.domain.category.Category
import com.example.sprint_2.presentation.common.CategoryRow
import com.example.sprint_2.presentation.common.CommonBottomBar
import com.example.sprint_2.presentation.common.CommonShoesCard
import com.example.sprint_2.presentation.common.CommonTopBar
import com.example.sprint_2.presentation.main_screen.bucket.BucketScreen


data class SecondaryScreen(
    var screen: ScreenType,
    val categoryScreen: Category? = null,
    val db: AppDatabase
): Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { SecondaryViewModel(screen, categoryScreen, db) }
        LaunchedEffect(screen) {
            viewModel.updateScreen(screen)
        }
        key(screen) {
            Secondary(viewModel)
        }
    }

    @Composable
    fun Secondary(viewModel: SecondaryViewModel){
        val state = viewModel.screenState.collectAsState().value
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current
        Scaffold(
            topBar = {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxWidth()
                ){
                    CommonTopBar(
                        onBack = { navigator.pop() },
                        label = state.label,
                        modifier = Modifier.padding(top = 48.dp),
                        onFavourite = {
                            viewModel.updateScreen(screen)
                            navigator.push(
                                SecondaryScreen(screen = ScreenType.FAVOURITE, db = db)
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
                }
            },
            bottomBar = {
                if (screen == ScreenType.FAVOURITE)
                    CommonBottomBar(
                        onClickFavour = { navigator.push(SecondaryScreen(ScreenType.FAVOURITE, db = db)) },
                        onClickBucket = {navigator.push(BucketScreen(db))}
                    )
            }
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