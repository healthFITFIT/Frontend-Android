package com.example.core.ui.designsystem.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.core.ui.designsystem.R

@Composable
fun TryAgainButton(
    onClick: () -> Unit,
    enabled: Boolean
){
    MyTextButton(
        text = stringResource(id = R.string.try_again),
        onClick = onClick,
        enabled = enabled
    )
}