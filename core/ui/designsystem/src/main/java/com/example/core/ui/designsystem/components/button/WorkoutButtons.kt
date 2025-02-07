package com.example.core.ui.designsystem.components.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.core.ui.designsystem.R
import com.example.core.ui.designsystem.icon.IconButtonIcon
import com.example.core.ui.designsystem.theme.FitfitTheme

@Composable
fun StopButton(
    onClick: () -> Unit
){
    MyIconButton(
        icon = IconButtonIcon.stop,
        onClick = onClick,
        modifier = Modifier.size(56.dp),
        containerColor = MaterialTheme.colorScheme.errorContainer
    )
}

@Composable
fun PauseButton(
    onClick: () -> Unit
){
    MyIconButton(
        icon = IconButtonIcon.pause,
        onClick = onClick,
        modifier = Modifier.size(56.dp),
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    )
}

@Composable
fun NextWorkoutButton(
    onClick: () -> Unit
){
    MyTextButton(
        modifier = Modifier.widthIn(min = 200.dp).height(56.dp),
        text = stringResource(id = R.string.next_workout),
        onClick = onClick,
        textStyle = MaterialTheme.typography.titleSmall
    )
}





@PreviewLightDark
@Composable
private fun StopButtonPreview(

){
    FitfitTheme {
        StopButton(
            onClick = {}
        )
    }
}
