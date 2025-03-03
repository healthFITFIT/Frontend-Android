package com.example.core.data.remote_db

import com.example.core.model.data.UserData
import com.example.core.model.dto.ApiResponseDto
import com.example.core.model.dto.IdTokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitApiService {

    @POST("oauth/validate")
    suspend fun requestUserDataWithIdToken(
        @Body idTokenRequest: IdTokenRequest
    ): Response<ApiResponseDto>




    @POST("")
    fun requestUserDataWithJwt(
        @Header("Authorization") jwt: String,
    ): Response<ApiResponseDto>





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