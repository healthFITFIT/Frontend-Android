package com.example.feature.workout.workout.component.exerciseInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.model.workout.Exercise
import com.example.core.ui.designsystem.components.utils.MyCard
import com.example.core.ui.designsystem.components.utils.MySpacerRow
import com.example.core.utils.itemMaxWidthSmall

@Composable
internal fun ExerciseInfo(
    exercise: Exercise,
    onClickExercise: () -> Unit,

    sets: Int,
    goalSets: Int,

    reps: Int,
    goalReps: Int,

    weight: Float?
){
    val valueBigTextStyle = MaterialTheme.typography.displayLarge.copy(fontSize = 50.sp)
    val valueTextStyle = MaterialTheme.typography.displayLarge
    val unitTextStyle = MaterialTheme.typography.titleMedium


    MyCard(
        modifier = Modifier.widthIn(max = itemMaxWidthSmall),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ExerciseName(
                exercise = exercise,
                onClick = onClickExercise
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.widthIn(max = itemMaxWidthSmall).height(92.dp)
            ) {
                Spacer(modifier = Modifier.weight(0.5f))

                Sets(
                    sets = sets,
                    goalSets = goalSets,
                    valueTextStyle = valueTextStyle,
                    unitTextStyle = unitTextStyle
                )

                MySpacerRow(8.dp)
                Spacer(modifier = Modifier.weight(1f))


                Reps(
                    reps = reps,
                    goalReps = goalReps,
                    valueBigTextStyle = valueBigTextStyle,
                    valueTextStyle = valueTextStyle,
                    unitTextStyle = unitTextStyle
                )

                MySpacerRow(8.dp)
                Spacer(modifier = Modifier.weight(1f))


                Weight(
                    weight = weight,
                    valueTextStyle = valueTextStyle,
                    unitTextStyle = unitTextStyle
                )

                Spacer(modifier = Modifier.weight(0.5f))
            }

        }
    }
}