package com.example.feature.workout.workout.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.core.model.workout.Exercise
import com.example.core.ui.designsystem.components.utils.MySpacerRow
import com.example.core.utils.secondToHourMinSec
import com.example.core.utils.itemMaxWidthSmall

@Composable
internal fun ExerciseNameAndPeriodTime(
    exercise: Exercise,
    periodTime: Int
){
    val (hour, minute, second) = secondToHourMinSec(periodTime)

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .widthIn(max = itemMaxWidthSmall)
            .height(36.dp)
    ) {
        MySpacerRow(16.dp)

        ExerciseName(
            exercise = exercise
        )

        Spacer(modifier = Modifier.weight(1f))

        PeriodTime(
            periodTimeHour = hour,
            periodTimeMinute = minute,
            periodTimeSecond = second
        )

        MySpacerRow(16.dp)
    }
}

@Composable
private fun ExerciseName(
    exercise: Exercise
){
    Text(
        text = stringResource(exercise.textId),
        style = MaterialTheme.typography.displayLarge
    )
}

@Composable
private fun PeriodTime(
    periodTimeHour: Int,
    periodTimeMinute: Int,
    periodTimeSecond: Int
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        //hour
        PeriodTimeOneNumber(periodTimeHour / 10)
        PeriodTimeOneNumber(periodTimeHour % 10)

        PeriodTimeDivider()

        //minute
        PeriodTimeOneNumber(periodTimeMinute / 10)
        PeriodTimeOneNumber(periodTimeMinute % 10)

        PeriodTimeDivider()

        //second
        PeriodTimeOneNumber(periodTimeSecond / 10)
        PeriodTimeOneNumber(periodTimeSecond % 10)
    }

}

@Composable
private fun PeriodTimeOneNumber(
    number: Int
){
    Box(
        modifier = Modifier.width(14.dp).height(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@Composable
private fun PeriodTimeDivider(

){
    Box(
        modifier = Modifier.width(10.dp).height(30.dp).offset(y = (-2).dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = ":",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}