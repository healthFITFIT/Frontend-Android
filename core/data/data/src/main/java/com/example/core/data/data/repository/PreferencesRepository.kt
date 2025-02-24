package com.example.core.data.data.repository

import com.example.core.data.local_db.DbLocalDataSource
import com.example.core.model.data.DateTimeFormat
import com.example.core.model.data.Theme
import com.example.core.model.enums.AppTheme
import com.example.core.model.enums.DateFormat
import com.example.core.model.enums.TimeFormat
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val dbLocalDataSource: DbLocalDataSource
) {
    suspend fun getAppPreferencesValue(
        onGet: (Theme, DateTimeFormat) -> Unit
    ) {
        dbLocalDataSource.getAppPreferencesValue(onGet = onGet)
    }





    suspend fun saveAppThemePreference(
        appTheme: AppTheme
    ) {
        dbLocalDataSource.saveAppThemePreference(appTheme = appTheme)
    }

    suspend fun saveDateFormatPreference(
        dateFormat: DateFormat
    ) {
        dbLocalDataSource.saveDateFormatPreference(dateFormat = dateFormat)
    }

    suspend fun saveDateUseMonthNamePreference(
        useMonthName: Boolean
    ) {
        dbLocalDataSource.saveDateUseMonthNamePreference(useMonthName = useMonthName)
    }

    suspend fun saveDateIncludeDayOfWeekPreference(
        includeDayOfWeek: Boolean
    ) {
        dbLocalDataSource.saveDateIncludeDayOfWeekPreference(includeDayOfWeek = includeDayOfWeek)
    }

    suspend fun saveTimeFormatPreference(
        timeFormat: TimeFormat
    ) {
        dbLocalDataSource.saveTimeFormatPreference(timeFormat = timeFormat)
    }

}