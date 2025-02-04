package com.example.core.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class SettingsLocalModule {
    @Binds
    internal abstract fun bindSettingDataSource(
        settingDataStoreApi: DataStoreApi
    ): SettingsLocalDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

    @Provides
    @Singleton
    fun provideDatastorePreferences(
        @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> {
        return applicationContext.dataStore
    }
}