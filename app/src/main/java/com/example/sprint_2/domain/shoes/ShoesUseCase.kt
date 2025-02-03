package com.example.sprint_2.domain.shoes

import com.example.sprint_2.data.remote_data_source.shoes.ShoesRepository
import com.example.sprint_2.domain.common.ResponseState
import com.example.sprint_2.domain.common.toShoes
import kotlinx.coroutines.flow.flow

class ShoesUseCase {
    val repository = ShoesRepository()

    suspend fun getAllShoes() = flow<ResponseState>{
        return@flow try{
            emit(ResponseState.Loading())
            val result = repository.getShoes().map{
                it.Shoes.toShoes()
            }
            emit(ResponseState.Success(data = result))
        } catch (e:Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}