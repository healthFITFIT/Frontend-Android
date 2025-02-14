package com.example.feature.workout.workout.component.exerciseInfo

import androidx.compose.foundation.layout.Column
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
import com.example.feature.workout.R
import java.text.DecimalFormat

@Composable
internal fun Weight(
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