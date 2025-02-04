package com.example.fitfit.navigationUi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.ui.designsystem.components.ExampleNavigationBottomBar
import com.example.core.ui.designsystem.components.ExampleNavigationBottomBarItem
import com.example.core.ui.designsystem.components.ExampleNavigationDrawer
import com.example.core.ui.designsystem.components.ExampleNavigationDrawerItem
import com.example.core.ui.designsystem.components.ExampleNavigationRailBar
import com.example.core.ui.designsystem.components.ExampleNavigationRailBarItem
import com.example.fitfit.navigation.TopLevelDestination
import com.example.fitfit.utils.WindowHeightSizeClass
import com.example.fitfit.utils.WindowSizeClass
import com.example.fitfit.utils.WindowWidthSizeClass

@Composable
fun ScreenWithNavigationBar(
    windowSizeClass: WindowSizeClass,
    currentTopLevelDestination: TopLevelDestination,
    showNavigationBar: Boolean,

    onClickNavBarItem: (TopLevelDestination) -> Unit,
    onClickNavBarItemAgain: (TopLevelDestination) -> Unit,

    modifier: Modifier = Modifier,
    topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries,
    content: @Composable () -> Unit = {}
) {

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        //phone vertical ===========================================================================
        if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                //content
                content()

                //bottom navigation bar
                AnimatedVisibility(
                    visible = showNavigationBar,
                    enter = slideInVertically(tween(300), initialOffsetY = { it }),
                    exit = slideOutVertically(tween(300), targetOffsetY = { it })
                ) {
                    ExampleNavigationBottomBar{
                        topLevelDestinations.forEach {
                            ExampleNavigationBottomBarItem(
                                selected = it == currentTopLevelDestination,
                                onClick = {
                                    if (it != currentTopLevelDestination)
                                        onClickNavBarItem(it)
                                    else
                                        onClickNavBarItemAgain(it)
                                },
                                selectedIcon = it.selectedIcon,
                                unSelectedIcon = it.unselectedIcon,
                                labelText = stringResource(id = it.labelTextId)
                            )
                        }
                    }
                }
            }
        }

        //phone horizontal (height compact) ========================================================
        //or foldable vertical or tablet vertical (width medium)
        else if (
            windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact
            || windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium
        ) {
            Box(
                modifier = Modifier.displayCutoutPadding().fillMaxSize()
            ) {
                //content
                content()

                //navigation rail bar
                AnimatedVisibility(
                    visible = showNavigationBar,
                    enter = slideInHorizontally(tween(300), initialOffsetX = { -it*2 }),
                    exit = slideOutHorizontally(tween(300), targetOffsetX = { -it*2 })
                ) {
                    ExampleNavigationRailBar {
                        topLevelDestinations.forEach {
                            ExampleNavigationRailBarItem(
                                selected = it == currentTopLevelDestination,
                                onClick = {
                                    if (it != currentTopLevelDestination)
                                        onClickNavBarItem(it)
                                    else
                                        onClickNavBarItemAgain(it)
                                },
                                selectedIcon = it.selectedIcon,
                                unSelectedIcon = it.unselectedIcon,
                                labelText = stringResource(id = it.labelTextId)
                            )
                        }
                    }
                }
            }
        }

        //foldable horizontal or tablet horizontal =================================================
        else if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                //content
                content()

                //navigation drawer bar
                AnimatedVisibility(
                    visible = showNavigationBar,
                    enter = slideInHorizontally(tween(300), initialOffsetX = { -it }),
                    exit = slideOutHorizontally(tween(300), targetOffsetX = { -it })
                ) {
                    ExampleNavigationDrawer {
                        topLevelDestinations.forEach {
                            ExampleNavigationDrawerItem(
                                selected = it == currentTopLevelDestination,
                                onClick = {
                                    if (it != currentTopLevelDestination)
                                        onClickNavBarItem(it)
                                    else
                                        onClickNavBarItemAgain(it)
                                },
                                selectedIcon = it.selectedIcon,
                                unSelectedIcon = it.unselectedIcon,
                                labelText = stringResource(id = it.labelTextId)
                            )
                        }
                    }
                }
            }
        }
    }
}

