package com.example.core.data.ai_pose_detection

import com.example.core.model.data.Offset3D

interface AiPoseDetectionDataSource {

    fun pushUpAutoCount(
        poseLandmarks: Map<Int, Offset3D>,
        onPlusReps: () -> Unit
    )
}