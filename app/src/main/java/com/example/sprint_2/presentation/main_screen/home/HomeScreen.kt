package com.example.sprint_2.presentation.main_screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.sprint_2.data.local_data_source.shoes.AppDatabase
import com.example.sprint_2.presentation.common.CategoryRow
import com.example.sprint_2.presentation.common.CommonBottomBar
import com.example.sprint_2.presentation.common.CommonScaffold
import com.example.sprint_2.presentation.common.HomeTopBar
import com.example.sprint_2.presentation.common.PopularRow
import com.example.sprint_2.presentation.common.SalesRow
import com.example.sprint_2.presentation.common.SearchRow
import com.example.sprint_2.presentation.main_screen.bucket.BucketScreen
import com.example.sprint_2.presentation.secondary_screen.ScreenType
import com.example.sprint_2.presentation.secondary_screen.SecondaryScreen
import com.example.sprint_2.presentation.ui.theme.Background

data class HomeScreen(private val db: AppDatabase): Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { HomeViewModel(db) }
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(Unit) {
            viewModel.updateData()
        }
        CommonScaffold(
            content = {
                Home(
                    navigator,
                    viewModel,
                    db,
                    Modifier.padding(PaddingValues(top = 111.dp))
                )
            },
            bottomBar = {
                CommonBottomBar(
                    onClickFavour = {
                        navigator.push(
                            SecondaryScreen(
                                ScreenType.FAVOURITE,
                                db = db
                            )
                        )
                    },
                    onClickBucket = {
                        navigator.push(BucketScreen(db))
                    }
                )
            },
            topBar = {
                HomeTopBar()
            }
        )
    }

    @Composable
    fun Home(
        navigator: Navigator,
        viewModel: HomeViewModel,
        db: AppDatabase,
        modifier: Modifier
    ) {
        val state = viewModel.state.collectAsState().value
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = modifier
                .fillMaxSize()
                .background(Background)
                .padding(horizontal = 20.dp)
        ) {
            SearchRow(Modifier.padding(top = 21.dp))
            CategoryRow(
                modifier = Modifier.padding(top = 22.dp),
                categories = state.category,
                onClick = {
                    navigator.push(
                        SecondaryScreen(
                            screen = ScreenType.CATEGORY,
                            categoryScreen = it,
                            db = db
                        )
                    )
                }
            )
            PopularRow(
                modifier = Modifier.padding(top = 24.dp),
                shoesList = viewModel.shoesList,
                onTextClick = {
                    navigator.push(SecondaryScreen(screen = ScreenType.POPULAR, db = db))
                },
                onAdd = {
                    viewModel.inBucket(shoes = it)
                },
                onFavourite = {
                    viewModel.inFavourite(shoes = it)
                }
            )
            SalesRow(
                modifier = Modifier.padding(top = 29.dp),
                onTextClick = {},
                listSales = state.sales
            )
        }
    }
}