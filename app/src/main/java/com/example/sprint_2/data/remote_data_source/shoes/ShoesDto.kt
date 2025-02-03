package com.example.sprint_2.data.remote_data_source.shoes

import com.example.sprint_2.data.remote_data_source.category.CategoryDto
import kotlinx.serialization.Serializable

@Serializable
data class ShoesDto(
    val id: Long,
    val shoes_name: String,
    val shoes_cost: Double,
    val shoes_url: String,
    val shoes_description: String
)

@Serializable
data class ShoesAndCat(
    val shoes_id: Long,
    val cat_id: Long,
    val Shoes: ShoesDto,
    val Category: CategoryDto
)