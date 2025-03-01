package com.example.core.model.data

data class Offset3D(
    val x: Float,
    val y: Float,
    val z: Float,
){
    operator fun plus(other: Offset3D): Offset3D {
        return Offset3D(x + other.x, y + other.y, z + other.z)
    }

    operator fun minus(other: Offset3D): Offset3D {
        return Offset3D(x - other.x, y - other.y, z - other.z)
    }

    operator fun times(scalar: Float): Offset3D {
        return Offset3D(x * scalar, y * scalar, z * scalar)
    }

    operator fun div(scalar: Float): Offset3D {
        return Offset3D(x / scalar, y / scalar, z / scalar)
    }
}