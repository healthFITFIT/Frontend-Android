package com.example.fitfit.navigation

import androidx.annotation.StringRes
import com.example.core.model.enums.ScreenDestination
import com.example.core.ui.designsystem.icon.MyIcon
import com.example.core.ui.designsystem.icon.NavigationBarIcon
import com.example.fitfit.R

enum class TopLevelDestination(
    val selectedIcon: MyIcon,
    val unselectedIcon: MyIcon,
    @StringRes val labelTextId: Int,
    val route: String
) {
    WORKOUT(
        selectedIcon = NavigationBarIcon.workoutFilled,
        unselectedIcon = NavigationBarIcon.workoutOutlined,
        labelTextId = R.string.workout,
        route = ScreenDestination.MAIN_WORKOUT.route
    ),
    LOGS(
        selectedIcon = NavigationBarIcon.logsFilled,
        unselectedIcon = NavigationBarIcon.logsOutlined,
        labelTextId = R.string.logs,
        route = ScreenDestination.MAIN_LOGS.route
    ),
    MORE(
        selectedIcon = NavigationBarIcon.moreFilled,
        unselectedIcon = NavigationBarIcon.moreOutlined,
        labelTextId = R.string.more,
        route = ScreenDestination.MAIN_MORE.route
    ),
}