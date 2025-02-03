package com.example.sprint_2.presentation.main_screen.home

import androidx.compose.runtime.mutableStateListOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.sprint_2.domain.category.Category
import com.example.sprint_2.domain.category.CategoryUseCase
import com.example.sprint_2.domain.common.ResponseState
import com.example.sprint_2.domain.sales.Sales
import com.example.sprint_2.domain.sales.SalesUseCase
import com.example.sprint_2.domain.shoes.Shoes
import com.example.sprint_2.domain.shoes.ShoesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ScreenModel {
    val state = MutableStateFlow(HomeScreenState())
    val shoesUseCase = ShoesUseCase()
    val categoryUseCase = CategoryUseCase()
    val salesUseCase = SalesUseCase()
    val shoesList = mutableStateListOf<Shoes>()


    init {
        getAllCategory()
        getAllSales()
        getAllShoes()
    }

    private fun getAllCategory() {
        screenModelScope.launch {
            val result = categoryUseCase.getAllCategory()
            result.collect { response ->
                when (response) {
                    is ResponseState.Error -> {}
                    is ResponseState.Success<*> -> {
                        state.update {
                            it.copy(category = response.data as List<Category>)
                        }
                    }
                    is ResponseState.Loading -> {}
                }
            }
        }
    }
    private fun getAllSales(){
        screenModelScope.launch {
            val result = salesUseCase.getSales()
            result.collect { response ->
                when (response) {
                    is ResponseState.Error -> {}
                    is ResponseState.Success<*> -> {
                        state.update {
                            it.copy(sales = response.data as List<Sales>)
                        }
                    }
                    is ResponseState.Loading -> {}
                }
            }
        }
    }
    private fun getAllShoes() {
        screenModelScope.launch {
            val result = shoesUseCase.getAllShoes()
            result.collect { response ->
                when (response) {
                    is ResponseState.Error -> {}
                    is ResponseState.Success<*> -> {
                        shoesList.clear()
                        shoesList.addAll(response.data as List<Shoes>)
                    }

                    is ResponseState.Loading -> {}
                }
            }
        }
    }
}