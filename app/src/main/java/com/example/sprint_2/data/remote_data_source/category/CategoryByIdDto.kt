package com.example.sprint_2.data.remote_data_source.category

import com.example.sprint_2.data.remote_data_source.shoes.ShoesDto
import kotlinx.serialization.Serializable

@Serializable
data class CategoryByIdDto (
    val cat_id: Long,
    val Shoes: ShoesDto
)