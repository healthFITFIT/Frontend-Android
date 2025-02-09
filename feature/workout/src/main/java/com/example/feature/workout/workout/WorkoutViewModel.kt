package com.example.feature.workout.workout

import androidx.lifecycle.ViewModel
import com.example.core.model.workout.Exercise
import com.example.core.model.workout.WorkoutData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class CurrentExerciseUiState(
    val exercise: Exercise = Exercise.SQUAT,
    val periodTime: Int = 3473, //Hmmmmmmmmm.......

    val sets: Int = 3,
    val goalSets: Int = 10,

    val reps: Int = 16,
    val goalReps: Int = 25,

    val weight: Float = 104.5f,
)


data class WorkoutUiState(
    val currentExerciseUiState: CurrentExerciseUiState = CurrentExerciseUiState(),
    val workoutData: WorkoutData = WorkoutData()
)

@HiltViewModel
class WorkoutViewModel @Inject constructor(

) : ViewModel() {
    private val _workoutUiState = MutableStateFlow(WorkoutUiState())
    val workoutUiState = _workoutUiState.asStateFlow()


    fun setExercise(exercise: Exercise) {
        _workoutUiState.update {
            it.copy(
                currentExerciseUiState = it.currentExerciseUiState.copy(
                    exercise = exercise
                )
            )
        }
    }

    //TODO: create functions
//    fun setPeriodTime(duration: Int) {
//
//    }

    fun updateWorkoutData(workoutData: WorkoutData) {

    }
}
