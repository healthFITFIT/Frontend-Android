package com.example.feature.workout.workout

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA
import androidx.camera.core.CameraSelector.DEFAULT_FRONT_CAMERA
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.core.model.data.Offset3D
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

): ViewModel() {
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

    private val imageAnalysisUseCase = ImageAnalysis.Builder()
    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
    .build()
    .apply {
        setAnalyzer(
            Runnable::run,
            ImageAnalyzer(this@CameraPreviewViewModel)
        )
    }


    //image resolution
    private val _imageWidth = MutableStateFlow(0)
    val imageWidth = _imageWidth.asStateFlow()

    private val _imageHeight = MutableStateFlow(0)
    val imageHeight = _imageHeight.asStateFlow()

    //pose lines
    private val _poseLines = MutableStateFlow<List<Triple<Offset3D, Offset3D, Color>>>(emptyList())
    val poseLines = _poseLines.asStateFlow()











    suspend fun bindToCamera(
        appContext: Context,
        lifecycleOwner: LifecycleOwner
    ) {
        processCameraProvider = ProcessCameraProvider.awaitInstance(appContext)
        processCameraProvider!!.bindToLifecycle(
            lifecycleOwner, cameraPreviewUiState.value.cameraSelector,
            cameraPreviewUseCase, imageAnalysisUseCase
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

        processCameraProvider?.let { cameraProvider ->
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner, cameraSelector,
                cameraPreviewUseCase, imageAnalysisUseCase
            )
        }
    }



    fun updateImageResolution(
        width: Int,
        height: Int
    ) {
        _imageWidth.value = width
        _imageHeight.value = height
    }

    fun updatePoseLines(
        lines: List<Triple<Offset3D, Offset3D, Color>>
    ) {
        _poseLines.value = lines
    }
}