package com.example.core.model.workout

import java.time.ZonedDateTime

data class WorkoutData(
    val startDateTime: ZonedDateTime = ZonedDateTime.now(),
    val duration: Int = 0,

    val exercises: List<WorkoutExercise> = emptyList()
)

data class WorkoutExercise(
    val exercise: Exercise = Exercise.SQUAT,
    val sets: List<ExerciseSet> = emptyList()
)

data class ExerciseSet(
    val reps: Int = 0,
    val weight: Float? = 0.0f
)


private val exampleWorkout = WorkoutData(
    startDateTime = ZonedDateTime.now(),
    duration = 3600,

    exercises = listOf(
        WorkoutExercise(
            exercise = Exercise.SQUAT,
            sets = listOf(
                ExerciseSet(reps = 15, weight = 30.5f),
                ExerciseSet(reps = 10, weight = 20.5f),
                ExerciseSet(reps = 7, weight = 10.0f),
                ExerciseSet(reps = 7, weight = 10.0f),
            )
        ),
        WorkoutExercise(
            exercise = Exercise.PULL_UP,
            sets = listOf(
                ExerciseSet(reps = 15, weight = 30.0f),
                ExerciseSet(reps = 10, weight = 20.0f),
                ExerciseSet(reps = 7, weight = 10.0f),
            )
        ),
        WorkoutExercise(
            exercise = Exercise.SIDE_LATERAL_RAISE,
            sets = listOf(
                ExerciseSet(reps = 30, weight = 10.0f),
                ExerciseSet(reps = 30, weight = 10.0f),
                ExerciseSet(reps = 20, weight = 10.0f),
                ExerciseSet(reps = 20, weight = 10.0f),
            )
        ),
    )
)