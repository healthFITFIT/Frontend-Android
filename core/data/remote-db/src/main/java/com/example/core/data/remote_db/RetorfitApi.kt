package com.example.core.data.remote_db

import com.example.core.model.data.UserData
import javax.inject.Inject

private const val RETROFIT_TAG = "Retrofit"

class RetrofitApi @Inject constructor(
    private val retrofitApiService: RetrofitApiService
): DbRemoteDataSource {

    override fun requestUserInfo(
        userGoogleIdToken: String,
        onResult: (UserData) -> Unit,
        onError: () -> Unit
    ) {
        try {
            val userData = retrofitApiService.requestUserInfo("")
            onResult(userData)

        } catch (e: Exception) {
            onError()
        }

    }


}