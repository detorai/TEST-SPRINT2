package com.example.sprint_2.data.remote_data_source.category

import com.example.sprint_2.data.remote_data_source.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class CategoryRepository {
    val columns = Columns.raw(
        """
        cat_id,
        Shoes(
        id,
        shoes_name,
        shoes_cost,
        shoes_url,
        shoes_description
        )
        """.trimIndent()
    )

    suspend fun getAllCategory() = SupabaseClient.client.from("Category").select().decodeList<CategoryDto>()
    suspend fun getCategoryById(id: Long) =  SupabaseClient.client.from("ShoesAndCat").select(columns){
        filter {
            eq("cat_id", id)
        }
    }.decodeList<CategoryByIdDto>()
}