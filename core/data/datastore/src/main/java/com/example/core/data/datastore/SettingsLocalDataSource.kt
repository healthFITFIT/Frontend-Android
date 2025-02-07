package com.example.core.data.datastore

import com.example.core.model.data.DateTimeFormat
import com.example.core.model.data.Theme
import com.example.core.model.enums.AppTheme
import com.example.core.model.enums.DateFormat
import com.example.core.model.enums.TimeFormat

interface SettingsLocalDataSource {

    /**
     * update app setting values - at app start
     */
    suspend fun getAppPreferencesValue(
        onGet: (Theme, DateTimeFormat) -> Unit
    )




    suspend fun saveAppThemePreference(appTheme: AppTheme)



    suspend fun saveDateFormatPreference(dateFormat: DateFormat)

    suspend fun saveDateUseMonthNamePreference(useMonthName: Boolean)

    suspend fun saveDateIncludeDayOfWeekPreference(includeDayOfWeek: Boolean)

    suspend fun saveTimeFormatPreference(timeFormat: TimeFormat)
}