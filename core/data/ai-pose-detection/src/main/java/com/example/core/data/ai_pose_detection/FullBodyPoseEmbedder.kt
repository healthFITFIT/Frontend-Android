package com.example.core.data.ai_pose_detection

import com.example.core.model.data.MyPointF3D

class FullBodyPoseEmbedder(private val torsoSizeMultiplier: Float = 2.5f) {
    private val landmarkNames = listOf(
        "nose",
        "left_eye_inner", "left_eye", "left_eye_outer",
        "right_eye_inner", "right_eye", "right_eye_outer",
        "left_ear", "right_ear",
        "mouth_left", "mouth_right",
        "left_shoulder", "right_shoulder",
        "left_elbow", "right_elbow",
        "left_wrist", "right_wrist",
        "left_pinky_1", "right_pinky_1",
        "left_index_1", "right_index_1",
        "left_thumb_2", "right_thumb_2",
        "left_hip", "right_hip",
        "left_knee", "right_knee",
        "left_ankle", "right_ankle",
        "left_heel", "right_heel",
        "left_foot_index", "right_foot_index"
    )

    fun embedPose(landmarks: List<MyPointF3D>): List<MyPointF3D> {
        require(landmarks.size == landmarkNames.size) { "Unexpected number of landmarks: ${landmarks.size}" }

        // 정규화 (이동 및 크기 조정)
        val normalizedLandmarks = normalizePoseLandmarks(landmarks)

        // 포즈 임베딩 계산
        return getPoseDistanceEmbedding(normalizedLandmarks)
    }

    private fun normalizePoseLandmarks(landmarks: List<MyPointF3D>): List<MyPointF3D> {
        val poseCenter = getPoseCenter(landmarks)
        val translatedLandmarks = landmarks.map { it - poseCenter }

        val poseSize = getPoseSize(translatedLandmarks, torsoSizeMultiplier)
        return translatedLandmarks.map { it * (100f / poseSize) } // 100배 확대 (디버깅 용이)
    }

    private fun getPoseCenter(landmarks: List<MyPointF3D>): MyPointF3D {
        val leftHip = landmarks[landmarkNames.indexOf("left_hip")]
        val rightHip = landmarks[landmarkNames.indexOf("right_hip")]
        return (leftHip + rightHip) * 0.5f
    }

    private fun getPoseSize(landmarks: List<MyPointF3D>, torsoSizeMultiplier: Float): Float {
        val leftHip = landmarks[landmarkNames.indexOf("left_hip")]
        val rightHip = landmarks[landmarkNames.indexOf("right_hip")]
        val hipsCenter = (leftHip + rightHip) * 0.5f

        val leftShoulder = landmarks[landmarkNames.indexOf("left_shoulder")]
        val rightShoulder = landmarks[landmarkNames.indexOf("right_shoulder")]
        val shouldersCenter = (leftShoulder + rightShoulder) * 0.5f

        val torsoSize = shouldersCenter.distanceTo(hipsCenter)
        val maxDist = landmarks.maxOf { it.distanceTo(hipsCenter) }

        return maxOf(torsoSize * torsoSizeMultiplier, maxDist)
    }

    private fun getPoseDistanceEmbedding(landmarks: List<MyPointF3D>): List<MyPointF3D> {
        return listOf(
            getDistance(getAverageByNames(landmarks, "left_hip", "right_hip"),
                getAverageByNames(landmarks, "left_shoulder", "right_shoulder")),

            getDistanceByNames(landmarks, "left_shoulder", "left_elbow"),
            getDistanceByNames(landmarks, "right_shoulder", "right_elbow"),
            getDistanceByNames(landmarks, "left_elbow", "left_wrist"),
            getDistanceByNames(landmarks, "right_elbow", "right_wrist"),
            getDistanceByNames(landmarks, "left_hip", "left_knee"),
            getDistanceByNames(landmarks, "right_hip", "right_knee"),
            getDistanceByNames(landmarks, "left_knee", "left_ankle"),
            getDistanceByNames(landmarks, "right_knee", "right_ankle"),
            getDistanceByNames(landmarks, "left_shoulder", "left_wrist"),
            getDistanceByNames(landmarks, "right_shoulder", "right_wrist"),
            getDistanceByNames(landmarks, "left_hip", "left_ankle"),
            getDistanceByNames(landmarks, "right_hip", "right_ankle"),
            getDistanceByNames(landmarks, "left_hip", "left_wrist"),
            getDistanceByNames(landmarks, "right_hip", "right_wrist"),
            getDistanceByNames(landmarks, "left_shoulder", "left_ankle"),
            getDistanceByNames(landmarks, "right_shoulder", "right_ankle"),
            getDistanceByNames(landmarks, "left_hip", "left_wrist"),
            getDistanceByNames(landmarks, "right_hip", "right_wrist"),
            getDistanceByNames(landmarks, "left_elbow", "right_elbow"),
            getDistanceByNames(landmarks, "left_knee", "right_knee"),
            getDistanceByNames(landmarks, "left_wrist", "right_wrist"),
            getDistanceByNames(landmarks, "left_ankle", "right_ankle")
        )
    }

    private fun getAverageByNames(landmarks: List<MyPointF3D>, name1: String, name2: String): MyPointF3D {
        val p1 = landmarks[landmarkNames.indexOf(name1)]
        val p2 = landmarks[landmarkNames.indexOf(name2)]
        return (p1 + p2) * 0.5f
    }

    private fun getDistanceByNames(landmarks: List<MyPointF3D>, name1: String, name2: String): MyPointF3D {
        val p1 = landmarks[landmarkNames.indexOf(name1)]
        val p2 = landmarks[landmarkNames.indexOf(name2)]
        return getDistance(p1, p2)
    }

    private fun getDistance(p1: MyPointF3D, p2: MyPointF3D): MyPointF3D {
        return p2 - p1
    }
}