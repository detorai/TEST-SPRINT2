package com.example.sprint_2.data.remote_data_source

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


private const val key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRoYWVianJpcWd0aHlnd2p6cHhrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzY4NTI0NDYsImV4cCI6MjA1MjQyODQ0Nn0.xvfhjNmDK1vKxoiV_XlnE9J59BHAapPOcU1fLDxoUvE"
private const val url = "https://thaebjriqgthygwjzpxk.supabase.co"

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = url,
        supabaseKey = key
    ){
        install(Auth)
        install(Postgrest)
    }
}
