package com.example.core.ui.designsystem.components.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.core.ui.designsystem.R

@Composable
fun StartWorkoutButton(
    onClick: () -> Unit
){
    MyTextButton(
        modifier = Modifier.widthIn(min = 220.dp).height(50.dp),
        text = stringResource(id = R.string.start_workout),
        onClick = onClick,
        textStyle = MaterialTheme.typography.titleSmall
    )
}