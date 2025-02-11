package com.example.core.data.credentials

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.core.model.data.UserData
import com.example.core.model.enums.ProviderId

interface AuthRemoteDataSource{
//    fun getCurrentUser(): FirebaseUser?

    suspend fun signinWithGoogle(
        context: Context,
        onResult: (String) -> Unit,
        onError: () -> Unit
    )

    suspend fun signInWithGoogleIntent(
        intent: Intent,
    ): UserData?


    suspend fun getGoogleSignInIntentSender(): IntentSender?


    suspend fun signOut(
        providerIdList: List<ProviderId>,
        signOutResult: (isSignOutSuccess: Boolean) -> Unit
    )

    fun reAuthenticateGoogleUser(
        intent: Intent,
        reAuthResult: (Boolean, Exception?) -> Unit
    )

    fun deleteAuthUser(
        deleteSuccess: (Boolean) -> Unit
    )
}