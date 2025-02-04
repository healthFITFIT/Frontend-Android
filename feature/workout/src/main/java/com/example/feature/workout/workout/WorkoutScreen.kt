package com.example.feature.workout.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.core.ui.designsystem.components.MyScaffold
import com.example.core.ui.designsystem.components.topAppBar.FitfitTopAppBar
import com.example.feature.workout.R

@Composable
fun WorkoutRoute(
    modifier: Modifier = Modifier,
) {


    WorkoutScreen()
}

@Composable
private fun WorkoutScreen(

){
    MyScaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .displayCutoutPadding()
            .imePadding(),
    ){ paddingValues ->

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 200.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            item {
                Column {
                    Text("workout")
                }
            }
        }
    }
}