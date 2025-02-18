package com.example.core.ui.ui.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.core.model.enums.AppTheme
import com.example.core.model.workout.Exercise
import com.example.core.ui.designsystem.components.utils.ClickableBox
import com.example.core.ui.designsystem.components.utils.MyCard
import com.example.core.ui.ui.R
import com.example.core.ui.ui.dialog.component.CancelDialogButton
import com.example.core.ui.ui.dialog.component.DialogButtonLayout
import com.example.core.ui.ui.dialog.component.DialogButtons
import com.example.core.ui.ui.dialog.component.MyDialog
import com.example.core.ui.ui.dialog.component.OkDialogButton

@Composable
fun SelectExerciseDialog(
    initialExercise: Exercise,

    onOkClick: (exercise: Exercise) -> Unit,
    onDismissRequest: () -> Unit
){
    val exercises = enumValues<Exercise>()

    var currentExercise by rememberSaveable { mutableStateOf(initialExercise) }

    MyDialog(
        onDismissRequest = onDismissRequest,

        titleText = stringResource(R.string.select_exercise),
        bodyContent = {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .heightIn(min = 0.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.surfaceBright)
            ) {
                items(exercises) { exercise ->
                    ExerciseCard(
                        exercise = exercise,
                        isSelected = exercise == currentExercise,
                        onClick = { it ->
                            currentExercise = it
                        }
                    )
                }
            }
        },
        buttonContent = {
            DialogButtons(
                dialogButtonLayout = DialogButtonLayout.HORIZONTAL,
                negativeButtonContent = {
                    //cancel button
                    CancelDialogButton(
                        onClick = onDismissRequest,
                        modifier = it
                    )
                },
                positiveButtonContent = {
                    //ok button
                    OkDialogButton(
                        onClick = { onOkClick(currentExercise) },
                        modifier = it
                    )
                }
            )
        }
    )
}


@Composable
private fun ExerciseCard(
    exercise: Exercise,
    isSelected: Boolean,
    onClick: (exercise: Exercise) -> Unit
){
    val borderStroke = if (isSelected) BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                    else null

    val cardColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
        else Color.Transparent

    val cardTextStyle = if (isSelected) MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        else MaterialTheme.typography.bodyMedium

    ClickableBox(
        containerColor = cardColor,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth(),
        onClick = { onClick(exercise) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(exercise.textId),
                style = cardTextStyle
            )
        }
    }
}