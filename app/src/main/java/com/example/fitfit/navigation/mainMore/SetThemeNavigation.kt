package com.example.fitfit.navigation.mainMore

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.core.model.enums.ScreenDestination
import com.example.core.ui.designsystem.components.NAVIGATION_DRAWER_BAR_WIDTH
import com.example.core.ui.designsystem.components.NAVIGATION_RAIL_BAR_WIDTH
import com.example.core.ui.designsystem.components.utils.MySpacerRow
import com.example.feature.more.setTheme.SetThemeRoute
import com.example.fitfit.navigation.TopLevelDestination
import com.example.fitfit.navigation.enterTransition
import com.example.fitfit.navigation.exitTransition
import com.example.fitfit.navigation.popEnterTransition
import com.example.fitfit.navigation.popExitTransition
import com.example.fitfit.ui.AppViewModel
import com.example.fitfit.ui.ExternalState
import com.example.fitfit.utils.WindowHeightSizeClass
import com.example.fitfit.utils.WindowWidthSizeClass
import kotlinx.coroutines.launch

private val topLevelScreenDestination = TopLevelDestination.MORE
private val screenDestination = ScreenDestination.SET_THEME

fun NavController.navigateToSetTheme(navOptions: NavOptions? = null) =
    navigate(screenDestination.route, navOptions)

fun NavGraphBuilder.setThemeScreen(
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

        SetThemeRoute(
            use2Panes = externalState.windowSizeClass.use2Panes,
            spacerValue = externalState.windowSizeClass.spacerValue,
            theme = appUiState.appPreferences.theme,
            updatePreferencesValue = {
                coroutineScope.launch{
                    appViewModel.getAppPreferencesValue()
                }
            },
            navigateUp = navigateUp
        )
    }
}