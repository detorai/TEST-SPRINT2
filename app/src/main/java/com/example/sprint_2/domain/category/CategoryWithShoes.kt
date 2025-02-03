package com.example.sprint_2.domain.category

import com.example.sprint_2.domain.shoes.Shoes

data class CategoryWithShoes (
    val cat_id: Long,
    val shoes: List<Shoes>
)