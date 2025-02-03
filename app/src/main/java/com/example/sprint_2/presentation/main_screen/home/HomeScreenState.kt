package com.example.sprint_2.presentation.main_screen.home

import com.example.sprint_2.domain.category.Category
import com.example.sprint_2.domain.sales.Sales
import com.example.sprint_2.domain.shoes.Shoes

data class HomeScreenState(
    var shoes: MutableList<Shoes> = mutableListOf(),
    var isLoading: Boolean = false,
    val category: List<Category> = emptyList(),
    val sales: List<Sales> = emptyList()
)