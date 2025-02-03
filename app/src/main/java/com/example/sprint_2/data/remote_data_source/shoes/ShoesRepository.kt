package com.example.sprint_2.data.remote_data_source.shoes

import com.example.sprint_2.data.remote_data_source.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class ShoesRepository {
    val columns = Columns.raw(
        """
        shoes_id,
        cat_id,
        Shoes(
        id,
        shoes_name,
        shoes_cost,
        shoes_url,
        shoes_description
        ),
        Category(
        id,
        category_name
        )
        """.trimIndent()
    )

    suspend fun getShoes() = SupabaseClient.client.from("ShoesAndCat").select(columns = columns).decodeList<ShoesAndCat>()

}