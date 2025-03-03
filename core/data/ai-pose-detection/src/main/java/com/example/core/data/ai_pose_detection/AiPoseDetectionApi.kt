package com.example.core.data.ai_pose_detection

import com.example.core.model.data.MyPointF3D
import com.google.mlkit.vision.pose.PoseLandmark
import javax.inject.Inject
import kotlin.math.atan2

private const val AI_POSE_DETECTION_TAG = "Ai-Pose-Detection"

class AiPoseDetectionApi @Inject constructor(

): AiPoseDetectionDataSource {

    private var isGoingDown = false  //push up

    override fun pushUpAutoCount(
        poseLandmarks: Map<Int, MyPointF3D>,
        onPlusReps: () -> Unit
    ){
        val leftShoulder = poseLandmarks[PoseLandmark.LEFT_SHOULDER]
        val leftElbow = poseLandmarks[PoseLandmark.LEFT_ELBOW]
        val leftWrist = poseLandmarks[PoseLandmark.LEFT_WRIST]

        val rightShoulder = poseLandmarks[PoseLandmark.RIGHT_SHOULDER]
        val rightElbow = poseLandmarks[PoseLandmark.RIGHT_ELBOW]
        val rightWrist = poseLandmarks[PoseLandmark.RIGHT_WRIST]

        if (leftShoulder != null && leftElbow != null && leftWrist != null &&
            rightShoulder != null && rightElbow != null && rightWrist != null
        ) {
            val leftAngle = calculateAngle(leftShoulder, leftElbow, leftWrist)
            val rightAngle = calculateAngle(rightShoulder, rightElbow, rightWrist)

//            Log.d("posee", "leftAngle: $leftAngle, rightAngle: $rightAngle")

            if (leftAngle < 95 && rightAngle < 95) {
                isGoingDown = true  // user down
            }

            if (isGoingDown && leftAngle > 160 && rightAngle > 160) {
                isGoingDown = false  // user up
                onPlusReps()
            }
        }
    }

    private fun calculateAngle(
        a: MyPointF3D,
        b: MyPointF3D,
        c: MyPointF3D
    ): Double {
        val radians = atan2(
            c.y - b.y, c.x - b.x
        ) - atan2(
            a.y - b.y, a.x - b.x
        )

        var angle = Math.toDegrees(radians.toDouble())

        if (angle < 0) angle += 360.0
        if (angle > 180.0) angle = 360.0 - angle

        return angle
    }

}