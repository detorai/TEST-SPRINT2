package com.example.sprint_2.presentation.main_screen.home

import com.example.sprint_2.domain.category.Category
import com.example.sprint_2.domain.sales.Sales

data class HomeScreenState(
    val category: List<Category> = emptyList(),
    val sales: List<Sales> = emptyList()
)