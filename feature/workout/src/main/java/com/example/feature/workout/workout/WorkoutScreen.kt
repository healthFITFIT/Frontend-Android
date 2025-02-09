package com.example.feature.workout.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.ui.designsystem.components.MyScaffold
import com.example.feature.workout.workout.component.CameraCard
import com.example.feature.workout.workout.component.ExerciseNameAndPeriodTime
import com.example.feature.workout.workout.component.SetsRepsWeight
import com.example.feature.workout.workout.component.WorkoutButtons

@Composable
fun WorkoutRoute(
    modifier: Modifier = Modifier,
    workoutViewModel: WorkoutViewModel = hiltViewModel(),
    cameraPreviewViewModel: CameraPreviewViewModel = hiltViewModel()
) {
    val workoutUiState by workoutViewModel.workoutUiState.collectAsState()

    WorkoutScreen(
        currentExerciseUiState = workoutUiState.currentExerciseUiState,
        cameraPreviewViewModel = cameraPreviewViewModel
    )
}

@Composable
private fun WorkoutScreen(
    currentExerciseUiState: CurrentExerciseUiState,
    cameraPreviewViewModel: CameraPreviewViewModel
){
    MyScaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .displayCutoutPadding()
            .imePadding(),
    ){ paddingValues ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            //camera
            CameraCard(
                cameraPreviewViewModel = cameraPreviewViewModel,
                modifier = Modifier.weight(1f)
            )

            //exercise name / period time
            ExerciseNameAndPeriodTime(
                exercise = currentExerciseUiState.exercise,
                periodTime = currentExerciseUiState.periodTime
            )

            //set / reps / weight
            SetsRepsWeight(
                sets = currentExerciseUiState.sets,
                goalSets = currentExerciseUiState.goalSets,
                reps = currentExerciseUiState.reps,
                goalReps = currentExerciseUiState.goalReps,
                weight = currentExerciseUiState.weight
            )

            //buttons
            WorkoutButtons(
                onClickStop = { },
                onClickPause = { },
                onClickNextWorkout = { }
            )
        }
    }
}