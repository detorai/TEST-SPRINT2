package com.example.sprint_2.presentation.secondary_screen

import androidx.compose.runtime.mutableStateListOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.sprint_2.domain.category.Category
import com.example.sprint_2.domain.category.CategoryUseCase
import com.example.sprint_2.domain.category.CategoryWithShoes
import com.example.sprint_2.domain.common.ResponseState
import com.example.sprint_2.domain.shoes.Shoes
import com.example.sprint_2.domain.shoes.ShoesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SecondaryViewModel(type: ScreenType, category: Category? = null): ScreenModel {
    val categoryUseCase = CategoryUseCase()
    val shoesUseCase = ShoesUseCase()
    val shoesList = mutableStateListOf<Shoes>()
    val screenState = MutableStateFlow(CatPopScreenState(currentScreen = type))

    init {
        when (type){
            ScreenType.POPULAR -> {
                screenState.update {
                    it.copy(label = "Популярное", currentScreen = ScreenType.POPULAR)
                }
                getCategoryById(6)
            }
            ScreenType.CATEGORY -> {
                getAllCategory()
            }
            ScreenType.FAVOURITE -> {
                screenState.update {
                    it.copy(label = "Избранное", currentScreen = ScreenType.FAVOURITE)
                }
            }
        }
        category?.let { category ->
            selectedCategory(category)
            screenState.update {
                it.copy(
                    label = category.name,
                    selectedCategory = category,
                    categoryVisState = true
                )
            }
        }
    }

    fun updateScreen(newScreen: ScreenType){
        if (screenState.value.currentScreen != newScreen){
            when (newScreen) {
                ScreenType.POPULAR -> {
                    screenState.update {
                        it.copy(label = "Популярное", currentScreen = ScreenType.POPULAR)
                    }
                    getCategoryById(6)
                }
                ScreenType.CATEGORY -> {
                    getAllCategory()
                }
                ScreenType.FAVOURITE -> {
                    screenState.update {
                        it.copy(label = "Избранное", currentScreen = ScreenType.FAVOURITE)
                    }
                }
            }
        }
    }


    private fun selectedCategory(category: Category){
        screenState.update {
            it.copy(
                selectedCategory = category,
                label = category.name,
            )
        }
        getCategoryById(
            category.id
        )
    }
    private fun getAllCategory() {
        screenModelScope.launch {
            val result = categoryUseCase.getAllCategory()
            result.collect { response ->
                when (response) {
                    is ResponseState.Error -> {}
                    is ResponseState.Success<*> -> {
                        screenState.update {
                            it.copy(category = response.data as List<Category>)
                        }
                    }
                    is ResponseState.Loading -> {}
                }
            }
        }
    }
    private fun getCategoryById(id: Long){
        screenModelScope.launch {
            val result = categoryUseCase.getCategoryById(id)
            result.collect { response ->
                when (response) {
                    is ResponseState.Error -> {}
                    is ResponseState.Success<*> -> {
                        shoesList.apply {
                            clear()
                            addAll(
                                (response.data as CategoryWithShoes).shoes
                            )
                        }
                    }
                    is ResponseState.Loading -> {}
                }
            }
        }

    }
}