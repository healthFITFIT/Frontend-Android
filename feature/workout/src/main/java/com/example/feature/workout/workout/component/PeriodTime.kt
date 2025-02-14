package com.example.feature.workout.workout.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.utils.itemMaxWidthSmall
import com.example.core.utils.secondToHourMinSec

@Composable
internal fun PeriodTime(
    periodTime: Int
){
    val (hour, minute, timeSecond) = secondToHourMinSec(periodTime)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(itemMaxWidthSmall)
            .padding(16.dp, 8.dp)
    ) {
        //hour
        PeriodTimeOneNumber(hour / 10)
        PeriodTimeOneNumber(hour % 10)

        PeriodTimeDivider()

        //minute
        PeriodTimeOneNumber(minute / 10)
        PeriodTimeOneNumber(minute % 10)

        PeriodTimeDivider()

        //second
        PeriodTimeOneNumber(timeSecond / 10)
        PeriodTimeOneNumber(timeSecond % 10)
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