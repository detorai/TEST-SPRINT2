package com.example.sprint_2.presentation.secondary_screen

import com.example.sprint_2.domain.category.Category

data class CatPopScreenState (
    val category: List<Category> = emptyList(),
    var categoryVisState: Boolean = false,
    var selectedCategory: Category? = null,
    val label: String = "",
    var currentScreen: ScreenType
)