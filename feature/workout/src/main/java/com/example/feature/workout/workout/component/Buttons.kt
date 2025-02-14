package com.example.feature.workout.workout.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.ui.designsystem.components.button.NextExerciseButton
import com.example.core.ui.designsystem.components.button.PauseButton
import com.example.core.ui.designsystem.components.button.StopButton
import com.example.core.ui.designsystem.components.utils.MySpacerRow
import com.example.core.utils.itemMaxWidthSmall

@Composable
internal fun WorkoutButtons(
    onClickStop: () -> Unit,
    onClickPause: () -> Unit,
    onClickNextWorkout: () -> Unit
){
    Row(
        modifier = Modifier.width(itemMaxWidthSmall),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StopButton(
            onClick = onClickStop
        )

        MySpacerRow(16.dp)

        PauseButton(
            onClick = onClickPause
        )

        MySpacerRow(16.dp)

        NextExerciseButton(
            onClick = onClickNextWorkout
        )
    }
}