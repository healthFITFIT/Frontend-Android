package com.example.feature.more.setTheme

import androidx.lifecycle.ViewModel
import com.example.core.data.data.repository.PreferencesRepository
import com.example.core.model.enums.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SetThemeViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
): ViewModel() {

    suspend fun saveThemePreferences(
        appTheme: AppTheme?,
    ){
        if (appTheme != null) {
            preferencesRepository.saveAppThemePreference(appTheme)
        }
    }
}