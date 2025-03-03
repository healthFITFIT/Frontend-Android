package com.example.core.data.remote_db

import android.util.Log
import com.example.core.model.data.UserData
import com.example.core.model.dto.IdTokenRequest
import javax.inject.Inject

private const val RETROFIT_TAG = "Retrofit"

class RetrofitApi @Inject constructor(
    private val retrofitApiService: RetrofitApiService
): DbRemoteDataSource {

    override suspend fun requestUserDataWithIdToken(
        userGoogleIdToken: String,
    ): Pair<String, UserData>? {
        try {
            val result = retrofitApiService.requestUserDataWithIdToken(
                idTokenRequest = IdTokenRequest(idToken = userGoogleIdToken)
            )

            //result
            val code = result.code()
            val success = result.body()?.success
            val error = result.body()?.error

            //data
            val jwt = result.body()?.jwt
            val userData = result.body()?.userData?.toUserData()

            if (
                code == 200
                && success == true
                && error == null
                && jwt != null
                && userData != null
            ) {
                return Pair(jwt, userData)
            }
            else {
                Log.e(RETROFIT_TAG, "result: $result")
                Log.e(RETROFIT_TAG, "headers: ${result.headers()}")
                Log.e(RETROFIT_TAG, "body: ${result.body()}")
                return null
            }

        } catch (e: Exception) {
            Log.e(RETROFIT_TAG, e.toString())
            return null
        }
    }

    override suspend fun requestUserDataWithJwt(
        jwt: String
    ): Pair<String, UserData>? {
        try {
            val result = retrofitApiService.requestUserDataWithJwt(jwt = jwt)
            Log.d(RETROFIT_TAG, "result = $result")
            Log.d(RETROFIT_TAG, "headers = ${result.headers()}")
            Log.d(RETROFIT_TAG, "body = ${result.body()}")

            //TODO: get jwt, userData
            return null

        } catch (e: Exception) {
            Log.e(RETROFIT_TAG, e.toString())
            return null
        }
    }
}