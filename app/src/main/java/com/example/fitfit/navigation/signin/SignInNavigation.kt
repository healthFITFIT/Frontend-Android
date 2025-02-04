package com.example.fitfit.navigation.signin

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.core.model.enums.ScreenDestination
import com.example.feature.signin.signin.SignInRoute
import com.example.fitfit.BuildConfig
import com.example.fitfit.navigation.enterTransition
import com.example.fitfit.navigation.exitTransition
import com.example.fitfit.navigation.popEnterTransition
import com.example.fitfit.navigation.popExitTransition
import com.example.fitfit.ui.AppViewModel
import com.example.fitfit.ui.ExternalState

private val screenDestination = ScreenDestination.SIGN_IN

fun NavController.navigateToSignIn(navOptions: NavOptions? = null) =
    navigate(screenDestination.route, navOptions)

fun NavGraphBuilder.signInScreen(
    appViewModel: AppViewModel,
    externalState: ExternalState,
    isDarkAppTheme: Boolean,

    navigateToMain: () -> Unit
){
    composable(
        route = screenDestination.route,
        enterTransition = { enterTransition },
        exitTransition = { exitTransition },
        popEnterTransition = { popEnterTransition },
        popExitTransition = { popExitTransition }
    ) {
        LaunchedEffect(Unit) {
            appViewModel.updateCurrentScreenDestination(screenDestination)
            appViewModel.initAppUiState()
        }

        SignInRoute(
            isDarkAppTheme = isDarkAppTheme,
            internetEnabled = externalState.internetEnabled,
            appVersionName = BuildConfig.VERSION_NAME,
            updateUserData = {userData ->
                appViewModel.updateUserData(userData = userData)
            },
            navigateToMain = navigateToMain
        )
    }
}