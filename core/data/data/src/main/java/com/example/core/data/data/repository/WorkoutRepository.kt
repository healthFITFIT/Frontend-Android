package com.example.core.data.data.repository

import com.example.core.data.ai_pose_detection.AiPoseDetectionDataSource
import com.example.core.model.data.Offset3D
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutRepository @Inject constructor(
    private val aiPoseDetectionDataSource: AiPoseDetectionDataSource
) {

    private val _poseLandmarks = MutableStateFlow<Map<Int, Offset3D>>(emptyMap())
    val poseLandmarks = _poseLandmarks.asStateFlow()


    fun updatePoseLandmarks(poseLandmarks: Map<Int, Offset3D>) {
        _poseLandmarks.update { poseLandmarks }
    }




    fun pushUpAutoCount(
        poseLandmarks: Map<Int, Offset3D>,
        onPlusReps: () -> Unit
    ){
        aiPoseDetectionDataSource.pushUpAutoCount(
            poseLandmarks = poseLandmarks,
            onPlusReps = onPlusReps
        )
    }
}