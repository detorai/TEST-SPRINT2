package com.example.sprint_2.domain.common

import com.example.sprint_2.data.remote_data_source.category.CategoryByIdDto
import com.example.sprint_2.data.remote_data_source.shoes.ShoesDto
import com.example.sprint_2.domain.shoes.Shoes

fun ShoesDto.toShoes(): Shoes {
    return Shoes(
        id = this.id,
        name = this.shoes_name,
        cost = this.shoes_cost,
        description = this.shoes_description,
        image = this.shoes_url,
    )
}
