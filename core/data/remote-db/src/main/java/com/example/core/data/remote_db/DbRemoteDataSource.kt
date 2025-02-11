package com.example.core.data.remote_db

import com.example.core.model.data.UserData

interface DbRemoteDataSource {
    fun requestUserInfo(
        userGoogleIdToken: String,
        onResult: (UserData) -> Unit,
        onError: () -> Unit
    )
}

