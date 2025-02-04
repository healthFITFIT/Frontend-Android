package com.example.core.model.data

import com.example.core.model.enums.ProviderId

data class UserData(
    val userId: String,
    val userName: String?,
    val email: String?,
    val profileImagePath: String?,

    val providerIds: List<ProviderId>,
)