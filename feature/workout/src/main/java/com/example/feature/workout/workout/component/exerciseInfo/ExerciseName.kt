package com.example.feature.workout.workout.component.exerciseInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.core.model.workout.Exercise
import com.example.core.ui.designsystem.components.utils.ClickableBox
import com.example.core.ui.designsystem.components.utils.MySpacerRow
import com.example.core.ui.designsystem.icon.DisplayIcon
import com.example.core.ui.designsystem.icon.MyIcons

@Composable
internal fun ExerciseName(
    exercise: Exercise,
    onClick: () -> Unit
){
    ClickableBox(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)
        ) {
            Text(
                text = stringResource(exercise.textId),
                style = MaterialTheme.typography.titleMedium
            )

            MySpacerRow(8.dp)

            //expand icon
            DisplayIcon(MyIcons.expand)
        }
    }
}

