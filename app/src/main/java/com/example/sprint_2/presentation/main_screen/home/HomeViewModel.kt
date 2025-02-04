package com.example.sprint_2.presentation.main_screen.home

import androidx.compose.runtime.mutableStateListOf
import androidx.room.RoomDatabase
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.sprint_2.data.local_data_source.shoes.AppDatabase
import com.example.sprint_2.domain.category.Category
import com.example.sprint_2.domain.category.CategoryUseCase
import com.example.sprint_2.domain.common.ResponseState
import com.example.sprint_2.domain.sales.Sales
import com.example.sprint_2.domain.sales.SalesUseCase
import com.example.sprint_2.domain.shoes.Shoes
import com.example.sprint_2.domain.shoes.ShoesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(db: AppDatabase): ScreenModel {
    val state = MutableStateFlow(HomeScreenState())
    val shoesUseCase = ShoesUseCase(db)
    val categoryUseCase = CategoryUseCase()
    val salesUseCase = SalesUseCase()
    val shoesList = mutableStateListOf<Shoes>()

    init{
        updateData()
    }

    fun updateData() {
        getAllCategory()
        getAllShoes()
        getAllSales()
    }



   fun inFavourite(shoes: Shoes){
       screenModelScope.launch(Dispatchers.IO) {
           val changeRecord = shoesList.indexOf(shoes)
           if(shoes.isFavourite){
               shoesUseCase.outFavourite(shoes)
           }
           else{
               shoesUseCase.inFavourite(shoes)
           }
           shoesList[changeRecord] = shoesList[changeRecord].copy(isFavourite = !shoes.isFavourite)
       }
    }

    fun inBucket(shoes: Shoes){
        screenModelScope.launch(Dispatchers.IO) {
            val changeRecord = shoesList.indexOf(shoes)
            if(shoes.inBucket){
                shoesUseCase.outBucket(shoes)
            }
            else{
                shoesUseCase.inBucket(shoes)
            }
            shoesList[changeRecord] = shoesList[changeRecord].copy(inBucket = !shoes.inBucket)
        }
    }

    fun getAllCategory() {
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
    fun getAllShoes() {
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