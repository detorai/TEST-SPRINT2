package com.example.sprint_2.data.local_data_source.shoes

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShoesInBucket::class, ShoesInFavourite::class, Onboarding::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun ShoesInBucketDao(): ShoesInBucketDao
    abstract fun ShoesInFavouriteDao(): ShoesInFavouriteDao
    abstract fun OnBoardingDao(): OnBoardingDao
}