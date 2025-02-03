package com.example.sprint_2.domain.shoes

import com.example.sprint_2.domain.category.Category

data class Shoes(
    val id: Long,
    val name: String,
    val cost: Double,
    val category: List<Category> = emptyList(),
    val description: String,
    val image: String,
    val count: Int = 0,
    var inBucket: Boolean = false,
    var isFavourite: Boolean = false
)