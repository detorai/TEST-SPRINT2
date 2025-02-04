package com.example.sprint_2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import cafe.adriel.voyager.navigator.Navigator
import com.example.sprint_2.data.local_data_source.shoes.AppDatabase
import com.example.sprint_2.presentation.main_screen.home.HomeScreen
import com.example.sprint_2.presentation.spalsh.SplashScreen
import com.example.sprint_2.presentation.ui.theme.Sprint2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "ShoesDb2"
        ).build()
        setContent {
            Sprint2Theme {
                Navigator(SplashScreen(db))
            }
        }
    }
}

