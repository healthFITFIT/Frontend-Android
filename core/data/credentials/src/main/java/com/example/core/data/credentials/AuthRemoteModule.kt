package com.example.core.data.credentials

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthRemoteModule {
    @Binds
    internal abstract fun bindAuthRemoteDataSource(
        credentialsApi: CredentialsApi
    ): AuthRemoteDataSource
}