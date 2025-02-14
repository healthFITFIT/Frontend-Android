package com.example.core.data.remote_db

import com.example.core.model.data.UserData
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApiService {

    /** send: user google idToken
     *
     *  get: UserData
     * */

    @POST("auth/google/login")
    fun requestUserInfo(
        userGoogleIdToken: String
    ): UserData

}