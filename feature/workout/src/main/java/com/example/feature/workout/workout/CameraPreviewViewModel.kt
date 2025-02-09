package com.example.feature.workout.workout

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA
import androidx.camera.core.CameraSelector.DEFAULT_FRONT_CAMERA
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class CameraPreviewUiState(
    val cameraSelector: CameraSelector = DEFAULT_BACK_CAMERA
)

@HiltViewModel
class CameraPreviewViewModel @Inject constructor(

) : ViewModel() {
    private val _cameraPreviewUiState = MutableStateFlow(CameraPreviewUiState())
    val cameraPreviewUiState = _cameraPreviewUiState.asStateFlow()


    // used to set up a link between the Camera and your UI.
    private val _surfaceRequest = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequest: StateFlow<SurfaceRequest?> = _surfaceRequest

    private var processCameraProvider: ProcessCameraProvider? = null

    private val cameraPreviewUseCase = Preview.Builder().build().apply {
        setSurfaceProvider { newSurfaceRequest ->
            _surfaceRequest.update { newSurfaceRequest }
        }
    }

    suspend fun bindToCamera(
        appContext: Context,
        lifecycleOwner: LifecycleOwner
    ) {
        processCameraProvider = ProcessCameraProvider.awaitInstance(appContext)
        processCameraProvider!!.bindToLifecycle(
            lifecycleOwner, cameraPreviewUiState.value.cameraSelector, cameraPreviewUseCase
        )

        // Cancellation signals we're done with the camera
        try { awaitCancellation() } finally { processCameraProvider!!.unbindAll() }
    }

    fun flipCamera(
        lifecycleOwner: LifecycleOwner
    ){
        val cameraSelector = if (cameraPreviewUiState.value.cameraSelector == DEFAULT_BACK_CAMERA){
            DEFAULT_FRONT_CAMERA
        }
        else {
            DEFAULT_BACK_CAMERA
        }

        setCameraSelector(
            cameraSelector = cameraSelector,
            lifecycleOwner = lifecycleOwner
        )
    }

    fun setCameraSelector(
        cameraSelector: CameraSelector,
        lifecycleOwner: LifecycleOwner
    ){
        _cameraPreviewUiState.update {
            it.copy(
                cameraSelector = cameraSelector
            )
        }

        processCameraProvider?.let {
            processCameraProvider?.let { cameraProvider ->
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, cameraPreviewUseCase)
            }
        }
    }
}