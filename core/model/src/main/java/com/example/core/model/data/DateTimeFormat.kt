package com.example.core.model.data

import com.example.core.model.enums.DateFormat
import com.example.core.model.enums.TimeFormat

data class DateTimeFormat(
    val timeFormat: TimeFormat = TimeFormat.T24H,
    val useMonthName: Boolean = false,
    val includeDayOfWeek: Boolean = false,
    val dateFormat: DateFormat = DateFormat.YMD
)