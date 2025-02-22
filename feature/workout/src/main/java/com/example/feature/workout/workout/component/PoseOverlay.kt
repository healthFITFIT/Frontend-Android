package com.example.feature.workout.workout.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.core.model.data.Offset3D

@Composable
fun PoseOverlay(
    poseLines: List<Triple<Offset3D, Offset3D, Color>>,
    isFlipped: Boolean,

    imageWidth: Int, //scan? image width
    imageHeight: Int,

    canvasWidth: Int, //ui width
    canvasHeight: Int
) {
//    Log.d("pose overlay", "image w: $imageWidth, h: $imageHeight")
//    Log.d("pose overlay", "canvas w: $canvasWidth, h: $canvasHeight")

    //if flip (use front camera)
    val flippedPoseLines =
        if (isFlipped) {
            poseLines.map {
                Triple(
                    Offset3D(480 - it.first.x, it.first.y, it.first.z),
                    Offset3D(480 - it.second.x, it.second.y, it.second.z),
                    it.third
                )
            }
        }
    else poseLines


    val minDepth = -1000f
    val maxDepth = 300f

    val scale = canvasWidth.toFloat() / 480 // image width .... hmmmmmmm...
    //FIXME: if phone is horizontal -> change 480 -> 640?

    //scale to canvas size / z (-1 ~ 1)
    val newPoseLines = flippedPoseLines.map {
        Triple(
            Offset3D(
                it.first.x * scale,
                it.first.y * scale,
                (it.first.z / maxDepth).coerceIn(-1f, 1f)
            ),
            Offset3D(
                it.second.x * scale,
                it.second.y * scale,
                (it.second.z / maxDepth).coerceIn(-1f, 1f)
            ),
            it.third
        )
    }

    //draw lines, points
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        newPoseLines.forEach { (start, end, color) ->

            //close to camera (z < 0) -> red
            //far from camera (z > 0) -> blue

            val startColor =
                if (start.z < 0){
                    Color(
                        red = 255,
                        green = (255 * (1 + start.z)).toInt(),
                        blue = (255 * (1 + start.z)).toInt(),
                    )
                }
                else{
                    Color(
                        red = (255 * (1 - start.z)).toInt(),
                        green = (255 * (1 - start.z)).toInt(),
                        blue = 255,
                    )
                }


            val endColor =
                if (end.z < 0){
                    Color(
                        red = 255,
                        green = (255 * (1 + end.z)).toInt(),
                        blue = (255 * (1 + end.z)).toInt(),
                    )
                }
                else{
                    Color(
                        red = (255 * (1 - end.z)).toInt(),
                        green = (255 * (1 -end.z)).toInt(),
                        blue = 255,
                    )
                }

            val gradientBrush = Brush.linearGradient(
                colors = listOf(startColor, endColor),
                start = Offset(start.x, start.y),
                end = Offset(end.x, end.y)
            )



            //face, body, arms, legs
            drawLine(
                brush = gradientBrush,
                start = Offset(start.x, start.y),
                end = Offset(end.x, end.y),
                strokeWidth = 20f
            )

            //joint
            drawCircle(
                color = color,
                radius = 20f,
                center = Offset(start.x, start.y)
            )

            //joint
            drawCircle(
                color = color,
                radius = 20f,
                center = Offset(end.x, end.y)
            )
        }
    }
}