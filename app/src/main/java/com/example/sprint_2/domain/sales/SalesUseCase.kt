package com.example.sprint_2.domain.sales


import com.example.sprint_2.data.remote_data_source.sales.SalesRepository
import com.example.sprint_2.domain.common.ResponseState
import kotlinx.coroutines.flow.flow

class SalesUseCase {
    val repository = SalesRepository()

    suspend fun getSales() = flow<ResponseState>{
        return@flow try {
            emit(ResponseState.Loading())
            val result = repository.getSales().map { sales ->
                Sales(
                    id = sales.id,
                    sales_url = sales.sales_url,
                    sales_name = sales.sales_name
                )
            }
            emit(ResponseState.Success(data = result))
        } catch (e:Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}