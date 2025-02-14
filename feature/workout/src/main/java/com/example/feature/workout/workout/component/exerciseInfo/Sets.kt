package com.example.feature.workout.workout.component.exerciseInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.core.ui.designsystem.components.utils.MySpacerColumn
import com.example.core.ui.designsystem.components.utils.MySpacerRow
import com.example.feature.workout.R

@Composable
internal fun Sets(
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