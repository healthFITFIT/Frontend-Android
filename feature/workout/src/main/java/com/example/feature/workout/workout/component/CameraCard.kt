package com.example.feature.workout.workout.component

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.camera.compose.CameraXViewfinder
import androidx.camera.core.CameraSelector
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.ui.designsystem.components.button.FlipCameraButton
import com.example.core.ui.designsystem.components.button.SettingsButton
import com.example.core.ui.designsystem.components.utils.MyCard
import com.example.core.ui.designsystem.components.utils.MySpacerColumn
import com.example.core.ui.designsystem.icon.DisplayIcon
import com.example.core.ui.designsystem.icon.MyIcons
import com.example.feature.workout.R
import com.example.feature.workout.workout.CameraPreviewViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraCard(
    cameraPreviewViewModel: CameraPreviewViewModel,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted){
            cameraPermissionState.launchPermissionRequest()
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {

        //check permission text
        AnimatedVisibility(
            visible = !cameraPermissionState.status.isGranted,
            enter = fadeIn(tween(500)),
            exit = fadeOut(tween(500))
        ) {
            CameraPermissionNotGrantedCard(
                onClickSettingsButton = {
                    //go to app settings
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)
                    )
                    context.startActivity(intent)
                }
            )
        }

        //camera
        AnimatedVisibility(
            visible = cameraPermissionState.status.isGranted,
            enter = fadeIn(tween(500)),
            exit = fadeOut(tween(500))
        ) {
            CameraPreviewContent(
                cameraPreviewViewModel = cameraPreviewViewModel
            )
        }
    }
}


//just camera view ui
@Composable
fun CameraPreviewContent(
    cameraPreviewViewModel: CameraPreviewViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val surfaceRequest by cameraPreviewViewModel.surfaceRequest.collectAsStateWithLifecycle()
    val poseLines by cameraPreviewViewModel.poseLines.collectAsStateWithLifecycle()

    val cameraPreviewUiState by cameraPreviewViewModel.cameraPreviewUiState.collectAsStateWithLifecycle()
    val cameraSelector = cameraPreviewUiState.cameraSelector

    val imageWidth by cameraPreviewViewModel.imageWidth.collectAsStateWithLifecycle()
    val imageHeight by cameraPreviewViewModel.imageHeight.collectAsStateWithLifecycle()

    var boxWidth by remember { mutableStateOf(0) }
    var boxHeight by remember { mutableStateOf(0) }

    val context = LocalContext.current

    LaunchedEffect(lifecycleOwner) {
        cameraPreviewViewModel.bindToCamera(context.applicationContext, lifecycleOwner)
    }

    Box(
        modifier = Modifier
//            .fillMaxSize()
            //ratio to camera preview size / FIXME: if camera ratio different? like other device
            .aspectRatio(3/4f),
        contentAlignment = Alignment.BottomStart
    ) {
        //camera preview
        surfaceRequest?.let { request ->
            CameraXViewfinder(
                surfaceRequest = request,
                modifier = Modifier
                    //get size(pixel not dp) of camera preview ui
                    .onGloballyPositioned {
                        boxWidth = it.size.width
                        boxHeight = it.size.height
                    }
            )
        }

        //pose overlay lines
        PoseOverlay(
            poseLines = poseLines,
            isFlipped = cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA,

            imageWidth = imageWidth,
            imageHeight = imageHeight,

            canvasWidth = boxWidth,
            canvasHeight = boxHeight
        )

        //flip camera button
        FlipCameraButton(
            onClick = {
                cameraPreviewViewModel.flipCamera(lifecycleOwner)
            },
            modifier = Modifier.padding(16.dp)
        )


        //stop/resume recognition button

    }
}

@Composable
private fun CameraPermissionNotGrantedCard(
    onClickSettingsButton: () -> Unit
){

    MyCard(

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DisplayIcon(icon = MyIcons.noCamera)

                MySpacerColumn(height = 12.dp)

                Text(
                    text = stringResource(id = R.string.camera_permission_denied),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )

                MySpacerColumn(height = 8.dp)

                Text(
                    text = stringResource(id = R.string.please_allow_camera_permissions),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )

                MySpacerColumn(height = 16.dp)

                SettingsButton(
                    onClick = onClickSettingsButton
                )
            }
        }
    }
}