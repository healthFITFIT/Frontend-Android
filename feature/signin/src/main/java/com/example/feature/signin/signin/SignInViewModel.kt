package com.example.feature.signin.signin

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import com.example.core.data.data.repository.CommonUiState
import com.example.core.data.data.repository.CommonUiStateRepository
import com.example.core.data.data.repository.signIn.SignInRepository
import com.example.core.model.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val SIGN_IN_VIEWMODEL_TAG = "SignIn-ViewModel"

data class SignInUiState(
    val isSigningIn: Boolean = false,
    val signInButtonEnabled: Boolean = true
)

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val commonUiStateRepository: CommonUiStateRepository,
    private val signInRepository: SignInRepository,
): ViewModel() {
    private val _signInUiState: MutableStateFlow<SignInUiState> =
        MutableStateFlow(SignInUiState())

    val signInUiState = _signInUiState.asStateFlow()

    init {
        //init commonTripUiState
        commonUiStateRepository._commonUiState.update {
            CommonUiState()
        }
    }


    //==============================================================================================
    //set UiState ==================================================================================
    fun setIsSigningIn(
        isSigningIn: Boolean
    ) {
        _signInUiState.update {
            it.copy(
                isSigningIn = isSigningIn
            )
        }
    }

    fun setSignInButtonEnabled(
        signInButtonEnabled: Boolean
    ) {
        _signInUiState.update {
            it.copy(
                signInButtonEnabled = signInButtonEnabled
            )
        }
    }



    //==============================================================================================
    //sign in ======================================================================================
    suspend fun signInWithGoogle(
        context: Context,
        onError: () -> Unit
    ){
        //FIXME: when user cancel it, not to show error snack bar
        setIsSigningIn(true)
        signInRepository.signInWithGoogle(
            context = context,
            onError = {
                setIsSigningIn(false)
                onError()
            }
        )
    }

    suspend fun signInLaunchGoogleLauncher(
        launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>,
        showErrorSnackbar: () -> Unit
    ) {
        setIsSigningIn(true)
        signInRepository.signInLaunchGoogleLauncher(
            launcher = launcher,
            signInError = {
                setIsSigningIn(false)
                showErrorSnackbar()
            }
        )
    }

    suspend fun signInWithGoogleResult(
        result: ActivityResult,
        onDone: (userData: UserData) -> Unit,
        showErrorSnackbar: () -> Unit
    ){
        if (result.resultCode == Activity.RESULT_OK){
            if (result.data == null){
                setIsSigningIn(false)
                return
            }

            //get signInResult from remote(firebase)
            var userData = signInRepository.signInWithGoogleIntent(
                intent = result.data!!
            )


            Log.d(SIGN_IN_VIEWMODEL_TAG, "signInWithGoogleResult - userData : $userData")

            updateUserDataFromRemote(
                userData = userData,
                onDone = onDone,
                showErrorSnackbar = showErrorSnackbar
            )
        }
        else {
            setIsSigningIn(false)
        }
    }







    //==============================================================================================
    private suspend fun updateUserDataFromRemote(
        userData: UserData?,
        onDone: (userData: UserData) -> Unit,
        showErrorSnackbar: () -> Unit
    ) {
        if (userData == null){
            setIsSigningIn(false)
            showErrorSnackbar()
        }
        else {
            //check user exit and
            //  if exit, get user data from firestore
            //  else, register user data to firestore
            signInRepository.updateUserDataFromRemote(
                userData = userData,
                setIsSigningIn = { setIsSigningIn(it) },
                onDone = onDone,
                showErrorSnackbar = showErrorSnackbar
            )
        }
    }
}