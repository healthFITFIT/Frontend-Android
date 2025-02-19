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
    val exercise: Exercise = Exercise.SIDE_LATERAL_RAISE,
    val periodTime: Int = 3473, //Hmmmmmmmmm.......

    val sets: Int = 1,
    val goalSets: Int = 4,

    val reps: Int = 0,
    val goalReps: Int = 15,

    val weight: Float? = 0.0f,
)


data class WorkoutUiState(
    val currentExerciseUiState: CurrentExerciseUiState = CurrentExerciseUiState(),
    val workoutData: WorkoutData = WorkoutData(),

    val showSelectExerciseDialog: Boolean = false,
    val showSetWeightDialog: Boolean = false
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

    fun setWeight(weight: Float?) {
        _workoutUiState.update {
            it.copy(
                currentExerciseUiState = it.currentExerciseUiState.copy(
                    weight = weight
                )
            )
        }
    }

    //temp----------------------------------
    fun setPrevSet() {
        _workoutUiState.update {
            it.copy(
                currentExerciseUiState = it.currentExerciseUiState.copy(
                    sets = it.currentExerciseUiState.sets - 1
                )
            )
        }
    }

    fun setNextSet() {
        _workoutUiState.update {
            it.copy(
                currentExerciseUiState = it.currentExerciseUiState.copy(
                    sets = it.currentExerciseUiState.sets + 1,
                    reps = 0
                )
            )
        }
    }

    fun setMinusReps() {
        _workoutUiState.update {
            it.copy(
                currentExerciseUiState = it.currentExerciseUiState.copy(
                    reps = it.currentExerciseUiState.reps - 1
                )
            )
        }
    }

    fun setPlusReps() {
        _workoutUiState.update {
            it.copy(
                currentExerciseUiState = it.currentExerciseUiState.copy(
                    reps = it.currentExerciseUiState.reps + 1
                )
            )
        }
    }

    fun onNextExercise(){
        _workoutUiState.update {
            it.copy(
                currentExerciseUiState = it.currentExerciseUiState.copy(
                    sets = 0,
                    reps = 0,
                    exercise = Exercise.PUSH_UP
                )
            )
        }
    }

    fun setShowSelectExerciseDialog(show: Boolean) {
        _workoutUiState.update {
            it.copy(
                showSelectExerciseDialog = show
            )
        }
    }

    fun setShowSetWeightDialog(show: Boolean) {
        _workoutUiState.update {
            it.copy(
                showSetWeightDialog = show
            )
        }
    }



    fun updateWorkoutData(workoutData: WorkoutData) {

    }
}
