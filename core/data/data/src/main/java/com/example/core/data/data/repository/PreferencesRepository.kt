package com.example.core.data.data.repository

import com.example.core.data.datastore.SettingsLocalDataSource
import com.example.core.model.enums.AppTheme
import com.example.core.model.enums.DateFormat
import com.example.core.model.enums.TimeFormat
import com.example.core.model.data.DateTimeFormat
import com.example.core.model.data.Theme
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val settingsLocalDataSource: SettingsLocalDataSource
) {
    suspend fun getAppPreferencesValue(
        onGet: (Theme, DateTimeFormat) -> Unit
    ) {
        settingsLocalDataSource.getAppPreferencesValue(onGet = onGet)
    }





    suspend fun saveAppThemePreference(
        appTheme: AppTheme
    ) {
        settingsLocalDataSource.saveAppThemePreference(appTheme = appTheme)
    }

    suspend fun saveDateFormatPreference(
        dateFormat: DateFormat
    ) {
        settingsLocalDataSource.saveDateFormatPreference(dateFormat = dateFormat)
    }

    suspend fun saveDateUseMonthNamePreference(
        useMonthName: Boolean
    ) {
        settingsLocalDataSource.saveDateUseMonthNamePreference(useMonthName = useMonthName)
    }

    suspend fun saveDateIncludeDayOfWeekPreference(
        includeDayOfWeek: Boolean
    ) {
        settingsLocalDataSource.saveDateIncludeDayOfWeekPreference(includeDayOfWeek = includeDayOfWeek)
    }

    suspend fun saveTimeFormatPreference(
        timeFormat: TimeFormat
    ) {
        settingsLocalDataSource.saveTimeFormatPreference(timeFormat = timeFormat)
    }

}