package com.example.sprint_2.domain.shoes

import androidx.room.RoomDatabase
import com.example.sprint_2.data.local_data_source.shoes.AppDatabase
import com.example.sprint_2.data.local_data_source.shoes.ShoesInBucket
import com.example.sprint_2.data.local_data_source.shoes.ShoesInFavourite
import com.example.sprint_2.data.remote_data_source.shoes.ShoesRepository
import com.example.sprint_2.domain.common.ResponseState
import com.example.sprint_2.domain.common.toShoes
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class ShoesUseCase(private val db: AppDatabase) {
    val repository = ShoesRepository()


    suspend fun getAllShoes() = flow<ResponseState>{
        return@flow try{
            emit(ResponseState.Loading())
            val result = repository.getShoes().map{
                it.Shoes.toShoes()
            }
            db.ShoesInFavouriteDao().getAll().forEach{favourite ->
                result.find { it.id == favourite }?.let { it.isFavourite = true }
            }
            db.ShoesInBucketDao().getAll().forEach{bucket ->
                result.find { it.id == bucket.shoes_id }?.let {
                    it.inBucket = true
                    it.count = bucket.count
                }
            }
            emit(ResponseState.Success(data = result))
        } catch (e:Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }


    suspend fun inFavourite(shoes: Shoes) {
        val result = db.ShoesInFavouriteDao().insertAll(
            ShoesInFavourite(
                shoes_id = shoes.id
            )
        )
    }
    suspend fun outFavourite(shoes: Shoes){
        val result = db.ShoesInFavouriteDao().delete(
           shoesId =  shoes.id
        )
    }
    suspend fun inBucket(shoes: Shoes) {
        val result = db.ShoesInBucketDao().insertAll(
            ShoesInBucket(
                shoes_id = shoes.id,
                count = 1
            )
        )
    }
    suspend fun outBucket(shoes: Shoes){
        val result = db.ShoesInBucketDao().delete(
            shoes.id
        )
    }
    suspend fun changeCountPlus(shoes: Shoes){
        val result = db.ShoesInBucketDao().changeCount(
            shoes.id, shoes.count + 1
        )
    }
    suspend fun changeCountMinus(shoes: Shoes){
        val result = db.ShoesInBucketDao().changeCount(
            shoes.id, shoes.count - 1
        )
    }
}