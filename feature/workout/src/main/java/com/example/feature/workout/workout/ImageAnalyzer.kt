package com.example.feature.workout.workout

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.compose.ui.graphics.Color
import com.example.core.model.data.Offset3D
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions

private const val IMAGE_ANALYZER_TAG = "Image-Analyzer"

val options = PoseDetectorOptions.Builder()
    .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
    .build()

val poseDetector = PoseDetection.getClient(options)




class ImageAnalyzer(
    private val cameraPreviewViewModel: CameraPreviewViewModel
) : ImageAnalysis.Analyzer {

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            // Pass image to an ML Kit Vision API
            val result: Task<Pose> = poseDetector.process(image)
                .addOnSuccessListener { results ->
                    val poseLines = extractPoseLines(results)

                    cameraPreviewViewModel.updateImageResolution(image.width, image.height)
                    //update pose lines to cameraViewModel
                    cameraPreviewViewModel.updatePoseLines(poseLines)
                }
                .addOnFailureListener { e ->
                    Log.e(IMAGE_ANALYZER_TAG, e.toString())
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
        else {
            imageProxy.close()
        }
    }


    private fun extractPoseLines(
        pose: Pose
    ): List<Triple<Offset3D, Offset3D, Color>> {
        val landmarks = pose.allPoseLandmarks.associateBy { it.landmarkType }

//        val ori = landmarks[PoseLandmark.RIGHT_WRIST]?.toOffset()?.z
//        val z = (ori?.div(1000))?.coerceIn(-1f, 1f)
//        Log.d("pose", "ori: ${ori.toString().take(5)}, z: ${z.toString().take(5)}")

        return listOf(

            //face
            Triple(landmarks[PoseLandmark.LEFT_EYE_INNER]?.toOffset(), landmarks[PoseLandmark.LEFT_EYE]?.toOffset(), Color.Transparent),
            Triple(landmarks[PoseLandmark.LEFT_EYE]?.toOffset(), landmarks[PoseLandmark.LEFT_EYE_OUTER]?.toOffset(), Color.Transparent),
            Triple(landmarks[PoseLandmark.RIGHT_EYE_INNER]?.toOffset(), landmarks[PoseLandmark.RIGHT_EYE]?.toOffset(), Color.Transparent),
            Triple(landmarks[PoseLandmark.RIGHT_EYE]?.toOffset(), landmarks[PoseLandmark.RIGHT_EYE_OUTER]?.toOffset(), Color.Transparent),

//            Triple(landmarks[PoseLandmark.LEFT_EAR]?.toOffset(), landmarks[PoseLandmark.LEFT_EYE_OUTER]?.toOffset(), Color.Cyan),
//            Triple(landmarks[PoseLandmark.RIGHT_EAR]?.toOffset(), landmarks[PoseLandmark.RIGHT_EYE_OUTER]?.toOffset(), Color.Cyan),

            Triple(landmarks[PoseLandmark.LEFT_MOUTH]?.toOffset(), landmarks[PoseLandmark.RIGHT_MOUTH]?.toOffset(), Color.Transparent),

            //body
            Triple(landmarks[PoseLandmark.LEFT_SHOULDER]?.toOffset(), landmarks[PoseLandmark.RIGHT_SHOULDER]?.toOffset(), Color.Cyan),
            Triple(landmarks[PoseLandmark.LEFT_HIP]?.toOffset(), landmarks[PoseLandmark.RIGHT_HIP]?.toOffset(), Color.Cyan),
            Triple(landmarks[PoseLandmark.LEFT_SHOULDER]?.toOffset(), landmarks[PoseLandmark.LEFT_HIP]?.toOffset(), Color.Cyan),
            Triple(landmarks[PoseLandmark.RIGHT_SHOULDER]?.toOffset(), landmarks[PoseLandmark.RIGHT_HIP]?.toOffset(), Color.Cyan),

            //arms
            Triple(landmarks[PoseLandmark.LEFT_SHOULDER]?.toOffset(), landmarks[PoseLandmark.LEFT_ELBOW]?.toOffset(), Color.Red),
            Triple(landmarks[PoseLandmark.LEFT_ELBOW]?.toOffset(), landmarks[PoseLandmark.LEFT_WRIST]?.toOffset(), Color.Red),
            Triple(landmarks[PoseLandmark.RIGHT_SHOULDER]?.toOffset(), landmarks[PoseLandmark.RIGHT_ELBOW]?.toOffset(), Color.Green),
            Triple(landmarks[PoseLandmark.RIGHT_ELBOW]?.toOffset(), landmarks[PoseLandmark.RIGHT_WRIST]?.toOffset(), Color.Green),

            //legs
            Triple(landmarks[PoseLandmark.LEFT_HIP]?.toOffset(), landmarks[PoseLandmark.LEFT_KNEE]?.toOffset(), Color.Red),
            Triple(landmarks[PoseLandmark.LEFT_KNEE]?.toOffset(), landmarks[PoseLandmark.LEFT_ANKLE]?.toOffset(), Color.Red),
            Triple(landmarks[PoseLandmark.RIGHT_HIP]?.toOffset(), landmarks[PoseLandmark.RIGHT_KNEE]?.toOffset(), Color.Green),
            Triple(landmarks[PoseLandmark.RIGHT_KNEE]?.toOffset(), landmarks[PoseLandmark.RIGHT_ANKLE]?.toOffset(), Color.Green),
        )
            .filter { it.first != null && it.second != null }
            .map { Triple(it.first!!, it.second!!, it.third) }
    }

    private fun PoseLandmark.toOffset(): Offset3D {
        val point = this.position3D
        return Offset3D(point.x, point.y, point.z)
    }
}
