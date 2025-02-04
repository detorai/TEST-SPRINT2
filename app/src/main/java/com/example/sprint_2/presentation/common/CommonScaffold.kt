package com.example.sprint_2.presentation.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun CommonScaffold(
    content:  @Composable (PaddingValues) -> Unit = {},
    topBar:  @Composable () -> Unit = {},
    bottomBar:  @Composable () -> Unit = {}
){
    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar,
        content = {padding ->
        content(padding)
        }
    )
}