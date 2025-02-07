package com.example.feature.more.mainMore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.core.model.data.UserData
import com.example.core.model.enums.ScreenDestination
import com.example.core.ui.designsystem.components.MyScaffold
import com.example.core.ui.designsystem.components.topAppBar.FitfitTopAppBar
import com.example.core.ui.ui.item.ItemDivider
import com.example.core.ui.ui.item.ItemWithText
import com.example.core.ui.ui.item.ListGroupCard
import com.example.core.utils.itemMaxWidthSmall
import com.example.feature.more.R

@Composable
fun MainMoreRoute(
    isDebugMode: Boolean,
    appUserData: UserData?,

    navigateTo: (ScreenDestination) -> Unit,

    modifier: Modifier = Modifier,
) {


    MainMoreScreen(
        isDebugMode = isDebugMode,
        appUserData = appUserData,

        navigateTo = navigateTo
    )
}

@Composable
private fun MainMoreScreen(
    isDebugMode: Boolean,
    appUserData: UserData?,

    navigateTo: (ScreenDestination) -> Unit,

    modifier: Modifier = Modifier,
){
    val itemModifier = Modifier.widthIn(max = itemMaxWidthSmall)

    MyScaffold(
        modifier =modifier
            .fillMaxSize()
            .navigationBarsPadding(),

        topBar = {
            FitfitTopAppBar(
                title = stringResource(R.string.more)
            )
        }
    ){ paddingValues ->

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 200.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            //setting
            item {
                ListGroupCard(
                    title = stringResource(id = R.string.settings),
                    modifier = itemModifier
                ) {
                    //date time format
                    ItemWithText(
                        text = stringResource(id = R.string.date_time_format),
                        onItemClick = { navigateTo(ScreenDestination.SET_DATE_TIME_FORMAT) }
                    )

                    ItemDivider()

                    //app theme
                    ItemWithText(
                        text = stringResource(id = R.string.theme),
                        onItemClick = { navigateTo(ScreenDestination.SET_THEME) }
                    )
                }
            }

            //account
            item {
                ListGroupCard(
                    modifier = itemModifier
                ) {
                    ItemWithText(
                        text = stringResource(id = R.string.account),
                        onItemClick = {
                            if (appUserData != null)
                                navigateTo(ScreenDestination.ACCOUNT)
                            else
                                navigateTo(ScreenDestination.SIGN_IN)
                        }
                    )
                }
            }

            //about
            item {
                ListGroupCard(
                    modifier = itemModifier
                ) {
                    ItemWithText(
                        text = stringResource(id = R.string.about),
                        onItemClick = {
                            navigateTo(ScreenDestination.ABOUT)
                        }
                    )
                }
            }

            if (isDebugMode)
                item{
                    Text(
                        text = "Debug Mode",
                        style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.primary)
                    )
                }
        }
    }
}