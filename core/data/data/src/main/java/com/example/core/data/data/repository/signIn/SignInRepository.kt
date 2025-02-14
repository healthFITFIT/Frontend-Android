package com.example.core.data.data.repository.signIn

import android.content.Context
import android.util.Log
import com.example.core.data.credentials.AuthRemoteDataSource
import com.example.core.data.remote_db.DbRemoteDataSource
import com.example.core.model.data.UserData
import javax.inject.Inject

private const val USER_REPOSITORY_TAG = "User-Repository"

class SignInRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource, //credentials
    private val dbRemoteDataSource: DbRemoteDataSource, //retrofit
) {

    //sign in ======================================================================================
    suspend fun signInWithGoogle(
        context: Context,
        onError: () -> Unit
    ){
        //sign in -> get user idToken
        authRemoteDataSource.signinWithGoogle(
            context = context,
            onResult = { userGoogleIdToken ->
                Log.d(USER_REPOSITORY_TAG, "user idToken: $userGoogleIdToken")

                dbRemoteDataSource.requestUserInfo(
                    userGoogleIdToken = userGoogleIdToken,
                    onResult = { userData: UserData ->

                    },
                    onError = onError
                )
            },
            onError = onError
        )

        //send user idToken to backend -> get user info

    }


//    suspend fun signInLaunchGoogleLauncher(
//        launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>,
//        signInError : () -> Unit
//    ){
//        val signInIntentSender = authRemoteDataSource.getGoogleSignInIntentSender()
//
//        if (signInIntentSender == null) {
//            signInError()
//        }
//
//        launcher.launch(
//            IntentSenderRequest.Builder(
//                signInIntentSender ?: return
//            ).build()
//        )
//    }
//
//    suspend fun signInWithGoogleIntent(
//        intent: Intent
//    ): UserData? {
//        return authRemoteDataSource.signInWithGoogleIntent(intent)
//    }




    //re authenticate ==============================================================================
//    suspend fun reAuthLaunchGoogleLauncher(
//        launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>,
//        setIsAuthing: (Boolean) -> Unit,
//        showErrorSnackbar: () -> Unit
//    ){
//        setIsAuthing(true)
//
//        val signInIntentSender = userRemoteDatasource.getGoogleSignInIntentSender()
//
//        if (signInIntentSender == null) {
//            showErrorSnackbar()
//            setIsAuthing(false)
//        }
//
//        launcher.launch(
//            IntentSenderRequest.Builder(
//                signInIntentSender ?: return
//            ).build()
//        )
//    }

//    fun reAuthGoogleResult(
//        result: ActivityResult,
//        setIsAuthing: (Boolean) -> Unit,
//        setIsAuthDone: (Boolean) -> Unit,
//        showReAuthErrorSnackbar: () -> Unit,
//        showReAuthErrorUserNotMatchSnackbar: () -> Unit
//    ){
//        if (result.resultCode == Activity.RESULT_OK) {
//            if (result.data == null){
//                setIsAuthing(false)
//                showReAuthErrorSnackbar()
//                return
//            }
//
//            userRemoteDatasource.reAuthenticateGoogleUser(
//                intent = result.data!!,
//                reAuthResult = { reAuthResult, exception ->
//                    setIsAuthing(false)
//
//                    if (reAuthResult){
//                        setIsAuthDone(true)
//                    }
//                    else if (exception is FirebaseAuthInvalidCredentialsException){
//                        showReAuthErrorUserNotMatchSnackbar()
//                    }
//                    else {
//                        showReAuthErrorSnackbar()
//                    }
//                }
//            )
//
//        }
//        else {
//            setIsAuthing(false)
//        }
//    }





    //==============================================================================================
    suspend fun updateUserDataFromRemote(
        userData: UserData,
        setIsSigningIn: (Boolean) -> Unit,
        onDone: (userData: UserData) -> Unit,
        showErrorSnackbar: () -> Unit
    ) {
//        Log.d(USER_REPOSITORY_TAG, "updateUserDataFromSignInResult - ${userData.userId} ${userData.userName} ${userData.email}")
//
//        //is userData exit in remote(firestore)?
//        val userExitInRemote = commonRemoteDataSource.checkUserExist(userData.userId)
//
//        if (userExitInRemote == null){
//            setIsSigningIn(false)
//            showErrorSnackbar()
//        }
//        else {
//            var newUserData: UserData? = null
//
//            //if exit user in firestore -> get user data from firestore
//            if (userExitInRemote == true){
//                newUserData = commonRemoteDataSource.getUserInfo(
//                    userId = userData.userId,
//                    providerIds = userData.providerIds
//                )
//            }
//            //if not exit -> register user to firestore
//            else if (userExitInRemote == false){
//                signInRemoteDataSource.registerUser(userData)
//                newUserData = userData
//            }
//
//            //update userViewModel
//            if (newUserData != null) {
//                onDone(newUserData)
//            }
//            else{
//                showErrorSnackbar()
//                setIsSigningIn(false)
//            }
//        }
    }






//    suspend fun getSignedInUser(
//
//    ): UserData? {
//        val firebaseUser = userRemoteDatasource.getCurrentUser()
//
//        //if null user return null
//        if (firebaseUser == null){
//            Log.d(USER_REPOSITORY_TAG, "getSignedInUser - user null")
//            return null
//        }
//        else {
//            Log.d(USER_REPOSITORY_TAG, "getSignedInUser - userId: ${firebaseUser.uid}")
//            return commonRemoteDataSource.getUserInfo(
//                userId = firebaseUser.uid,
//                providerIds = firebaseUser.providerData.mapNotNull { getProviderIdFromString(it.providerId)},
//            )
//        }
//    }
//
//
//    suspend fun checkUserExist(
//        userId: String
//    ): Boolean? {
//        return commonRemoteDataSource.checkUserExist(
//            userId = userId
//        )
//    }
//
//    suspend fun getUserInfo(
//        userId: String,
//        providerIds: List<ProviderId>
//    ): UserData? {
//        return commonRemoteDataSource.getUserInfo(
//            userId = userId,
//            providerIds = providerIds,
//        )
//    }
//
//    suspend fun registerUser(
//        userData: UserData
//    ): Boolean {
//        return signInRemoteDataSource.registerUser(userData)
//    }
//
//
//
//
//
//
//
//    fun deleteAuthUser(
//        deleteSuccess: (Boolean) -> Unit
//    ){
//        userRemoteDatasource.deleteAuthUser(
//            deleteSuccess = deleteSuccess
//        )
//    }
//
//
//
//
//
//
//
//
//    suspend fun signOut(
//        providerIdList: List<ProviderId>,
//        signOutResult: (isSignOutSuccess: Boolean) -> Unit
//    ){
//        userRemoteDatasource.signOut(
//            providerIdList = providerIdList,
//            signOutResult = signOutResult
//
//        )
//    }
}