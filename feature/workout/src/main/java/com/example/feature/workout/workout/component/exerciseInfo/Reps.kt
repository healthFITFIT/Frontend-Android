package com.example.feature.workout.workout.component.exerciseInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.core.ui.designsystem.components.utils.ClickableBox
import com.example.core.ui.designsystem.components.utils.MySpacerColumn
import com.example.core.ui.designsystem.components.utils.MySpacerRow
import com.example.feature.workout.R

@Composable
internal fun Reps(
    reps: Int,
    goalReps: Int,
    valueBigTextStyle: TextStyle,
    valueTextStyle: TextStyle,
    unitTextStyle: TextStyle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    ClickableBox(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 8.dp)
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
}