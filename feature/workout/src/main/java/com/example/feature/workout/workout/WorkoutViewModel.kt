package com.example.feature.workout.workout

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class WorkoutUiState(
    val example: Boolean = false
)

@HiltViewModel
class WorkoutViewModel @Inject constructor(

) : ViewModel() {
    private val _workoutUiState = MutableStateFlow(WorkoutUiState())
    val workoutUiState = _workoutUiState.asStateFlow()


}
