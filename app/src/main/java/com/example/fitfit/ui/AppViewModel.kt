package com.example.fitfit.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.data.repository.PreferencesRepository
import com.example.core.data.data.repository.signIn.SignInRepository
import com.example.core.model.data.DateTimeFormat
import com.example.core.model.data.Theme
import com.example.core.model.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val APP_VIEWMODEL_TAG = "App-ViewModel"

data class AppPreferencesState(
    val theme: Theme = Theme(),
    val dateTimeFormat: DateTimeFormat = DateTimeFormat()
)

data class DestinationState(
    val startScreenDestination: ScreenDestination? = null, //if not null, splash screen will be finish
    val moreDetailStartScreenDestination: ScreenDestination = ScreenDestination.SET_DATE_TIME_FORMAT,
    val currentTopLevelDestination: TopLevelDestination = TopLevelDestination.WORKOUT,
    val currentScreenDestination: ScreenDestination = ScreenDestination.SIGN_IN
)

data class AppUiState(
    val appUserData: UserData? = null,
    val appPreferences: AppPreferencesState = AppPreferencesState(),
    val screenDestination: DestinationState = DestinationState(),

    val showSplashScreen: Boolean = true,
    val firstLaunch: Boolean = true,
)



@HiltViewModel
class AppViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val signInRepository: SignInRepository,
): ViewModel() {
    private val _appUiState = MutableStateFlow(AppUiState())
    val appUiState = _appUiState.asStateFlow()

    //==============================================================================================
    //update app setting values - at app start =====================================================
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAppPreferencesValue()
        }
    }

    suspend fun getAppPreferencesValue(){
        preferencesRepository.getAppPreferencesValue { theme, dateTimeFormat ->
            _appUiState.update {
                it.copy(
                    appPreferences = it.appPreferences.copy(
                        theme = theme,
                        dateTimeFormat = dateTimeFormat
                    )
                )
            }
        }
    }









    //==============================================================================================
    //set uiState value ============================================================================
    fun firstLaunchToFalse() {
        _appUiState.update {
            it.copy(firstLaunch = false)
        }
    }






    //==============================================================================================
    //at sign in screen ============================================================================
    fun initAppUiState(

    ){
        _appUiState.update {
            it.copy(
                appUserData = null,
                firstLaunch = true
            )
        }
    }









    //==============================================================================================
    //at app start splash screen ===================================================================
    fun intiUserAndUpdateStartDestination (

    ){
        Log.d("MainActivity1", "[1] intiUserAndUpdateStartDestination start")

        initSignedInUser(
            onDone = { userDataIsNull ->
                updateCurrentScreenDestination(userDataIsNull)
            }
        )
        Log.d("MainActivity1", "                              [1]intiUserAndUpdateStartDestination done")
    }

    private fun initSignedInUser(
        onDone: (userDataIsNull: Boolean) -> Unit
    ){
        Log.d("MainActivity1", "[2] initSignedInUser start")

        viewModelScope.launch {
//            val time = measureNanoTime {
//            val userData = userRepository.getSignedInUser()
            var userData: UserData? = UserData("test", "", "", "", emptyList()) //TODO: delete this and use upper code
            userData = null

            _appUiState.update {
                it.copy(appUserData = userData)
            }

            onDone(userData == null || userData.userId == "")
            Log.d("MainActivity1", "[2] initSignedInUser - user: ${userData?.userId}")
//            }
//            Log.d("MainActivity1", "[2] ${time*0.000000001} - initSignedInUser")
        }
    }










    //==============================================================================================
    //update screen destination ====================================================================
    fun updateCurrentScreenDestination(
        userDataIsNull: Boolean
    ){
        val startScreenDestination =
            if (userDataIsNull) ScreenDestination.SIGN_IN
            else ScreenDestination.MAIN_WORKOUT

        _appUiState.update {
            it.copy(
                screenDestination = it.screenDestination.copy(
                    startScreenDestination = startScreenDestination
                )
            )
        }
        Log.d("MainActivity1", "[3] update screen destination: $startScreenDestination")
    }

    fun updateMoreDetailCurrentScreenDestination(
        screenDestination: ScreenDestination
    ){
        _appUiState.update {
            it.copy(
                screenDestination = it.screenDestination.copy(
                    moreDetailStartScreenDestination = screenDestination
                )
            )
        }
    }

    fun updateCurrentTopLevelDestination(topLevelDestination: TopLevelDestination){
        _appUiState.update {
            it.copy(
                screenDestination = it.screenDestination.copy(
                    currentTopLevelDestination = topLevelDestination
                )
            )
        }
    }

    fun updateCurrentScreenDestination(
        screenDestination: ScreenDestination
    ) {

        when (screenDestination) {
            ScreenDestination.MAIN_WORKOUT -> {
                _appUiState.update {
                    it.copy(
                        screenDestination = it.screenDestination.copy(
                            currentTopLevelDestination = TopLevelDestination.WORKOUT,
                            currentScreenDestination = screenDestination
                        )
                    )
                }
            }
            ScreenDestination.MAIN_LOGS -> {
                _appUiState.update {
                    it.copy(
                        screenDestination = it.screenDestination.copy(
                            currentTopLevelDestination = TopLevelDestination.LOGS,
                            currentScreenDestination = screenDestination
                        )
                    )
                }
            }
            else -> {
                _appUiState.update {
                    it.copy(
                        screenDestination = it.screenDestination.copy(
                            currentScreenDestination = screenDestination
                        )
                    )
                }
            }
        }
    }




    //==============================================================================================
    //update user data =============================================================================
    fun updateUserData(
        userData: UserData?
    ) {
        _appUiState.update {
            it.copy(
                appUserData = userData
            )
        }
    }
}