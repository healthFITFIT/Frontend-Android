package com.example.core.data.remote_db

import com.example.core.model.data.UserData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitApiService {

    @POST("auth/google/login")
    fun requestUserInfo(
        userGoogleIdToken: String
    ): UserData


    @POST("")
    fun getJwt(
        userGoogleIdToken: String
    ): String


    @POST("")
    fun signUp(
        @Header("jwt") jwt: String,
    ): UserData


    @GET("")
    fun getUserInfo(
        @Header("jwt") jwt: String,
    ): UserData


    @GET("something")
    fun someApi(
        @Header("jwt") jwt: String,
    ): UserData

}