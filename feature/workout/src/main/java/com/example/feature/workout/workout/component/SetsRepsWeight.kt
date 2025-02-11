package com.example.feature.workout.workout.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ui.designsystem.components.utils.MySpacerColumn
import com.example.core.ui.designsystem.components.utils.MySpacerRow
import com.example.core.utils.itemMaxWidthSmall
import com.example.feature.workout.R
import java.text.DecimalFormat

@Composable
internal fun SetsRepsWeight(
    sets: Int,
    goalSets: Int,

    reps: Int,
    goalReps: Int,

    weight: Float?
){
    val valueBigTextStyle = MaterialTheme.typography.displayLarge.copy(fontSize = 50.sp)
    val valueTextStyle = MaterialTheme.typography.displayLarge
    val unitTextStyle = MaterialTheme.typography.titleMedium

    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.widthIn(max = itemMaxWidthSmall).height(92.dp)
        ) {
            MySpacerRow(16.dp)

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

            MySpacerRow(16.dp)
        }

        MySpacerColumn(16.dp)
    }
}

@Composable
private fun Sets(
    sets: Int,
    goalSets: Int,
    valueTextStyle: TextStyle,
    unitTextStyle: TextStyle
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.widthIn(min = 90.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = sets.toString(),
                style = valueTextStyle
            )

            MySpacerRow(2.dp)

            Text(
                text = "/",
                style = valueTextStyle,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            MySpacerRow(2.dp)

            Text(
                text = goalSets.toString(),
                style = valueTextStyle,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        MySpacerColumn(4.dp)

        Text(
            text = stringResource(R.string.sets),
            style = unitTextStyle,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun Reps(
    reps: Int,
    goalReps: Int,
    valueBigTextStyle: TextStyle,
    valueTextStyle: TextStyle,
    unitTextStyle: TextStyle
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.widthIn(min = 120.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = reps.toString(),
                style = valueBigTextStyle,
                modifier = Modifier.offset(y = 8.dp)
            )

            MySpacerRow(2.dp)

            Text(
                text = "/",
                style = valueTextStyle,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            MySpacerRow(2.dp)

            Text(
                text = goalReps.toString(),
                style = valueTextStyle,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        MySpacerColumn(4.dp)

        Text(
            text = stringResource(R.string.reps),
            style = unitTextStyle,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun Weight(
    weight: Float?,
    valueTextStyle: TextStyle,
    unitTextStyle: TextStyle
){
    val df = DecimalFormat("#.#")
    val weightNotNull = weight ?: 0f
    val weightString = df.format(weightNotNull)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.widthIn(min = 90.dp)
    ) {
        Text(
            text = weightString,
            style = valueTextStyle,
            color = if (weight != null) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.onSurfaceVariant
        )

        MySpacerColumn(4.dp)

        Text(
            text = stringResource(R.string.kg),
            style = unitTextStyle,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}