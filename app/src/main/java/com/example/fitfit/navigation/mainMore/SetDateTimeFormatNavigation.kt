package com.example.fitfit.navigation.mainMore

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.core.model.enums.ScreenDestination
import com.example.feature.more.setDateTimeFormat.SetDateTimeFormatRoute
import com.example.fitfit.navigation.TopLevelDestination
import com.example.fitfit.navigation.enterTransition
import com.example.fitfit.navigation.exitTransition
import com.example.fitfit.navigation.popEnterTransition
import com.example.fitfit.navigation.popExitTransition
import com.example.fitfit.ui.AppViewModel
import com.example.fitfit.ui.ExternalState
import kotlinx.coroutines.launch

private val topLevelScreenDestination = TopLevelDestination.MORE
private val screenDestination = ScreenDestination.SET_DATE_TIME_FORMAT

fun NavController.navigateToSetDateTimeFormat(navOptions: NavOptions? = null) =
    navigate(screenDestination.route, navOptions)

fun NavGraphBuilder.setDateTimeFormatScreen(
    appViewModel: AppViewModel,
    externalState: ExternalState,

    navigateUp: () -> Unit,
) {
    composable(
        route = screenDestination.route,
        enterTransition = { enterTransition },
        exitTransition = { exitTransition },
        popEnterTransition = { popEnterTransition },
        popExitTransition = { popExitTransition }
    ) {
        LaunchedEffect(Unit) {
            appViewModel.updateCurrentScreenDestination(screenDestination)
        }

        val coroutineScope = rememberCoroutineScope()

        val appUiState by appViewModel.appUiState.collectAsState()

        SetDateTimeFormatRoute(
            use2Panes = externalState.windowSizeClass.use2Panes,
            spacerValue = externalState.windowSizeClass.spacerValue,
            dateTimeFormat = appUiState.appPreferences.dateTimeFormat,
            updatePreferencesValue = {
                coroutineScope.launch{
                    appViewModel.getAppPreferencesValue()
                }
            },
            navigateUp = navigateUp,
        )

    }
}