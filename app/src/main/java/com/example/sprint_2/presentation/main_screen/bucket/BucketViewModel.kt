package com.example.sprint_2.presentation.main_screen.bucket

import androidx.compose.runtime.mutableStateListOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.sprint_2.data.local_data_source.shoes.AppDatabase
import com.example.sprint_2.domain.common.ResponseState
import com.example.sprint_2.domain.shoes.Shoes
import com.example.sprint_2.domain.shoes.ShoesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BucketViewModel(db: AppDatabase) : ScreenModel{
    val state = MutableStateFlow(BucketScreenState())
    val shoesUseCase = ShoesUseCase(db)
    val shoesList = mutableStateListOf<Shoes>()

    init {
        getAllShoes()
        getCount()
    }


    private fun getCount(){
        state.update {
            it.copy(count = shoesList.size)
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
                        shoesList.addAll((response.data as List<Shoes>).filter { it.inBucket })
                    }
                    is ResponseState.Loading -> {}
                }
            }
        }
    }
    fun deleteFromBucket(shoes: Shoes){
        screenModelScope.launch(Dispatchers.IO) {
            shoesUseCase.outBucket(shoes)
            shoesList.remove(shoes)
        }
    }
    fun changeCountPlus(shoes: Shoes){
        screenModelScope.launch(Dispatchers.IO) {
            val changeRecord = shoesList.indexOf(shoes)
            shoesUseCase.changeCountPlus(shoes)
            shoesList[changeRecord] = shoesList[changeRecord].copy(count = shoes.count+1)
        }
    }
    fun changeCountMinus(shoes: Shoes){
        screenModelScope.launch(Dispatchers.IO) {
            val changeRecord = shoesList.indexOf(shoes)
            shoesUseCase.changeCountMinus(shoes)
            shoesList[changeRecord] = shoesList[changeRecord].copy(count = shoes.count-1)
        }
    }
}