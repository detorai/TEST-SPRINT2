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
data class ShoesInBucket (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val shoes_id: Long,
    var count: Int = 0,
)

@Dao
interface ShoesInBucketDao{
    @Query("SELECT * FROM ShoesInBucket")
    suspend fun getAll(): List<ShoesInBucket>

    @Insert
    suspend fun insertAll(vararg shoes: ShoesInBucket)

    @Query("DELETE FROM ShoesInBucket WHERE shoes_id = :id")
    suspend fun delete(id: Long)

    @Query("UPDATE ShoesInBucket SET count = :count WHERE shoes_id = :id")
    suspend fun changeCount(id: Long, count: Int)
}

