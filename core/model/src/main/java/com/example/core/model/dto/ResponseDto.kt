package com.example.core.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)

//for oauth/validate
data class ApiResponseDto(
    @Json(name = "success")     val success: Boolean,
    @Json(name = "jwt")         val jwt: String?,
    @Json(name = "user_data")   val userData: UserDataDto?,
    @Json(name = "error")       val error: ErrorDto?
)

@JsonClass(generateAdapter = true)
data class UserDataDto(
    @Json(name = "name")                val name: String,
    @Json(name = "email")               val email: String,
    @Json(name = "profile_image_url")   val profileImagePath: String,
)

@JsonClass(generateAdapter = true)
data class ErrorDto(
    //40100	승인되지 않은 접근입니다
    //50000	서버 내부 오류입니다
    @Json(name = "code")    val code: String,
    @Json(name = "message") val message: String,
)
