package com.example.sprint_2.data.local_data_source.shoes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.sprint_2.domain.category.Category
import kotlinx.coroutines.flow.Flow

@Entity
data class ShoesInFavourite (
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val shoes_id: Long
)

@Dao
interface ShoesInFavouriteDao{
    @Query("SELECT shoes_id FROM ShoesInFavourite")
    suspend fun getAll(): List<Long>

    @Insert
    suspend fun insertAll(vararg shoes: ShoesInFavourite)

    @Query("DELETE FROM ShoesInFavourite WHERE shoes_id =:shoesId")
    suspend fun delete(shoesId: Long)
}

