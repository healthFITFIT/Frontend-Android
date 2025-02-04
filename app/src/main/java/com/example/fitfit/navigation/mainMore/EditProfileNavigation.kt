package com.example.fitfit.navigation.mainMore

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
import com.example.feature.more.editProfile.EditProfileRoute
import com.example.fitfit.navigation.TopEnterTransition
import com.example.fitfit.navigation.TopExitTransition
import com.example.fitfit.navigation.TopLevelDestination
import com.example.fitfit.navigation.TopPopEnterTransition
import com.example.fitfit.navigation.TopPopExitTransition
import com.example.fitfit.navigation.enterTransition
import com.example.fitfit.navigation.exitTransition
import com.example.fitfit.navigation.popEnterTransition
import com.example.fitfit.navigation.popExitTransition
import com.example.fitfit.ui.AppViewModel
import com.example.fitfit.ui.ExternalState
import com.example.fitfit.utils.WindowHeightSizeClass
import com.example.fitfit.utils.WindowWidthSizeClass
import kotlinx.coroutines.delay

private val topLevelScreenDestination = TopLevelDestination.MORE
private val screenDestination = ScreenDestination.EDIT_PROFILE

fun NavController.navigateToEditProfile(navOptions: NavOptions? = null) =
    navigate(screenDestination.route, navOptions)

fun NavGraphBuilder.editProfileScreen(
    appViewModel: AppViewModel,
    externalState: ExternalState,

    navigateUp: () -> Unit,
    navigateToSomeScreen: () -> Unit,
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

            EditProfileRoute(
                navigateUp = navigateUp
            )
        }
    }
}