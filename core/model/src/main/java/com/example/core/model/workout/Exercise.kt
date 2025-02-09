package com.example.core.model.workout

import androidx.annotation.StringRes
import com.example.core.model.R

enum class Exercise(
    @StringRes val textId: Int
) {
    SQUAT(R.string.squat),
    PULL_UP(R.string.pull_up),
    PUSH_UP(R.string.push_up),
    SIDE_LATERAL_RAISE(R.string.side_lateral_raise),
}