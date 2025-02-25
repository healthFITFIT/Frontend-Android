package com.example.core.data.data.repository

import com.example.core.data.remote_db.DbRemoteDataSource
import com.example.core.model.data.UserData
import javax.inject.Inject

private const val SPLASH_REPOSITORY_TAG = "Splash-Repository"

class SplashRepository @Inject constructor(
    private val dbRemoteDataSource: DbRemoteDataSource, //retrofit
) {
    suspend fun getUserData(
        jwt: String
    ): Pair<String, UserData>? {

        return dbRemoteDataSource.requestUserDataWithJwt(jwt = jwt)
    }
}