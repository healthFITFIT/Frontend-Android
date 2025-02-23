package com.example.fitfit.navigation.mainWorkout

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.core.model.enums.ScreenDestination
import com.example.core.ui.designsystem.components.NAVIGATION_DRAWER_BAR_WIDTH
import com.example.core.ui.designsystem.components.NAVIGATION_RAIL_BAR_WIDTH
import com.example.core.ui.designsystem.components.utils.MySpacerRow
import com.example.feature.workout.mianWorkout.MainWorkoutRoute
import com.example.fitfit.navigation.TopEnterTransition
import com.example.fitfit.navigation.TopExitTransition
import com.example.fitfit.navigation.TopLevelDestination
import com.example.fitfit.navigation.TopPopEnterTransition
import com.example.fitfit.navigation.TopPopExitTransition
import com.example.fitfit.ui.AppViewModel
import com.example.fitfit.ui.ExternalState
import com.example.fitfit.utils.WindowHeightSizeClass
import com.example.fitfit.utils.WindowWidthSizeClass
import kotlinx.coroutines.delay

private val topLevelScreenDestination = TopLevelDestination.WORKOUT
private val screenDestination = ScreenDestination.MAIN_WORKOUT

fun NavController.navigateToMainWorkout(navOptions: NavOptions? = null) =
    navigate(screenDestination.route, navOptions)

fun NavGraphBuilder.mainWorkoutScreen(
    appViewModel: AppViewModel,
    externalState: ExternalState,

    navigateToWorkout: () -> Unit,
) {
    composable(
        route = screenDestination.route,
        enterTransition = { TopEnterTransition },
        exitTransition = { TopExitTransition },
        popEnterTransition = { TopPopEnterTransition },
        popExitTransition = { TopPopExitTransition }
    ) {
        LaunchedEffect(Unit) {
            appViewModel.updateCurrentTopLevelDestination(topLevelScreenDestination)
            delay(100)
            appViewModel.updateCurrentScreenDestination(screenDestination)
        }

        val appUiState by appViewModel.appUiState.collectAsState()

        val widthSizeClass = externalState.windowSizeClass.widthSizeClass
        val heightSizeClass = externalState.windowSizeClass.heightSizeClass

        Row {
            if (widthSizeClass == WindowWidthSizeClass.Compact) {
                MySpacerRow(width = 0.dp)
            } else if (
                heightSizeClass == WindowHeightSizeClass.Compact
                || widthSizeClass == WindowWidthSizeClass.Medium
            ) {
                MySpacerRow(width = NAVIGATION_RAIL_BAR_WIDTH)
            } else if (widthSizeClass == WindowWidthSizeClass.Expanded) {
                MySpacerRow(width = NAVIGATION_DRAWER_BAR_WIDTH)
            }

            MainWorkoutRoute(
                navigateToWorkout = navigateToWorkout
            )
        }
    }
}