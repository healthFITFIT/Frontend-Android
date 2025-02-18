package com.example.feature.workout.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.model.workout.Exercise
import com.example.core.ui.designsystem.components.MyScaffold
import com.example.core.ui.designsystem.components.utils.ClickableBox
import com.example.core.ui.designsystem.components.utils.MySpacerColumn
import com.example.core.ui.ui.dialog.SelectExerciseDialog
import com.example.core.ui.ui.dialog.SetWeightDialog
import com.example.feature.workout.workout.component.CameraCard
import com.example.feature.workout.workout.component.PeriodTime
import com.example.feature.workout.workout.component.WorkoutButtons
import com.example.feature.workout.workout.component.exerciseInfo.ExerciseInfo

@Composable
fun WorkoutRoute(
    modifier: Modifier = Modifier,
    workoutViewModel: WorkoutViewModel = hiltViewModel(),
    cameraPreviewViewModel: CameraPreviewViewModel = hiltViewModel()
) {
    val workoutUiState by workoutViewModel.workoutUiState.collectAsState()

    WorkoutScreen(
        currentExerciseUiState = workoutUiState.currentExerciseUiState,
        cameraPreviewViewModel = cameraPreviewViewModel,

        showSelectExerciseDialog = workoutUiState.showSelectExerciseDialog,
        showSetWeightDialog = workoutUiState.showSetWeightDialog,

        setShowSelectExerciseDialog = workoutViewModel::setShowSelectExerciseDialog,
        setShowSetWeightDialog = workoutViewModel::setShowSetWeightDialog,

        setCurrentExercise = workoutViewModel::setExercise,
        setCurrentWeight = workoutViewModel::setWeight,

        onClickPrevSet = workoutViewModel::setPrevSet,
        onClickNextSet = workoutViewModel::setNextSet,
        onClickRepsMinus = workoutViewModel::setMinusReps,
        onClickRepsPlus = workoutViewModel::setPlusReps,
        onClickNextExercise = workoutViewModel::onNextExercise
    )
}

@Composable
private fun WorkoutScreen(
    currentExerciseUiState: CurrentExerciseUiState,
    cameraPreviewViewModel: CameraPreviewViewModel,

    showSelectExerciseDialog: Boolean,
    showSetWeightDialog: Boolean,

    setShowSelectExerciseDialog: (Boolean) -> Unit,
    setShowSetWeightDialog: (Boolean) -> Unit,

    setCurrentExercise: (exercise: Exercise) -> Unit,
    setCurrentWeight: (weight: Float?) -> Unit,

    onClickPrevSet: () -> Unit = { },
    onClickNextSet: () -> Unit = { },
    onClickRepsMinus: () -> Unit = { },
    onClickRepsPlus: () -> Unit = { },
    onClickNextExercise: () -> Unit = { }
){
    MyScaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .displayCutoutPadding(),
    ){ paddingValues ->

        //dialog
        if (showSelectExerciseDialog){
            SelectExerciseDialog(
                initialExercise = currentExerciseUiState.exercise,
                onOkClick = { it ->
                    setCurrentExercise(it)
                    setShowSelectExerciseDialog(false)
                },
                onDismissRequest = { setShowSelectExerciseDialog(false) }
            )
        }

        if (showSetWeightDialog){
            SetWeightDialog(
                initialWeight = currentExerciseUiState.weight,
                onOkClick = {it ->
                    setCurrentWeight(it)
                    setShowSetWeightDialog(false)
                },
                onDismissRequest = { setShowSetWeightDialog(false) }
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
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

            //period time
            PeriodTime(
                periodTime = currentExerciseUiState.periodTime
            )

            //exercise info
            ExerciseInfo(
                exercise = currentExerciseUiState.exercise,
                onClickExercise = { setShowSelectExerciseDialog(true) },
                sets = currentExerciseUiState.sets,
                goalSets = currentExerciseUiState.goalSets,
                reps = currentExerciseUiState.reps,
                goalReps = currentExerciseUiState.goalReps,
                weight = currentExerciseUiState.weight,
                onClickSets = { },
                onClickReps = { },
                onClickWeight = { setShowSetWeightDialog(true) }
            )

            MySpacerColumn(16.dp)

            //temp
            TempButtons(
                onClickPrevSet = onClickPrevSet,
                onClickNextSet = onClickNextSet,
                onClickRepsMinus = onClickRepsMinus,
                onClickRepsPlus = onClickRepsPlus
            )
            MySpacerColumn(8.dp)

            //buttons
            WorkoutButtons(
                onClickStop = { },
                onClickPause = { },
                onClickNextWorkout = onClickNextExercise
            )
        }
    }
}

@Composable
private fun TempButtons(
    onClickPrevSet: () -> Unit,
    onClickNextSet: () -> Unit,
    onClickRepsMinus: () -> Unit,
    onClickRepsPlus: () -> Unit
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        //prev set
        TempButton(
            text = "<",
            onClick = onClickPrevSet
        )

        //next set
        TempButton(
            text = ">",
            onClick = onClickNextSet
        )

        //reps -
        TempButton(
            text = "-",
            onClick = onClickRepsMinus
        )

        //reps +
        TempButton(
            text = "+",
            onClick = onClickRepsPlus
        )
    }
}

@Composable
private fun TempButton(
    text: String,
    onClick: () -> Unit
){
    ClickableBox(
        modifier = Modifier.size(48.dp),
        onClick = onClick,
        shape = CircleShape,
        contentAlignment = Alignment.Center,
        containerColor = MaterialTheme.colorScheme.surfaceBright
    ) {
        Text(text)
    }
}