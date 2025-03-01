package com.example.core.data.ai_pose_detection

import com.example.core.model.data.MyPointF3D

interface AiPoseDetectionDataSource {

    fun pushUpAutoCount(
        poseLandmarks: Map<Int, MyPointF3D>,
        onPlusReps: () -> Unit
    )
}