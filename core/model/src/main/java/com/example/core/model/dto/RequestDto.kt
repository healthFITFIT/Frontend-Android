package com.example.core.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IdTokenRequest(
    @Json(name = "id_token")val idToken: String,
)