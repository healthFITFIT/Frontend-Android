package com.example.core.ui.designsystem.icon

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.automirrored.rounded.Login
import androidx.compose.material.icons.automirrored.rounded.NavigateNext
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material.icons.outlined.Luggage
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.CloudOff
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.DragHandle
import androidx.compose.material.icons.rounded.East
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.FileDownload
import androidx.compose.material.icons.rounded.FitnessCenter
import androidx.compose.material.icons.rounded.FlashOff
import androidx.compose.material.icons.rounded.FlashOn
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.LocationOff
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Luggage
import androidx.compose.material.icons.rounded.ManageAccounts
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.MoreTime
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.OpenInNew
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.material.icons.rounded.Route
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.ui.designsystem.R
import com.example.core.ui.designsystem.theme.CustomColor

data class MyIcon(
    val imageVector: ImageVector,
    val size: Dp = 22.dp,
    val isGray: Boolean = false,
    val color: Color? = null,  /**if null, set [color] to Material.colors.onSurface, onBackground...*/
    @StringRes val descriptionTextId: Int? = null
)





object NavigationBarIcon {
    val workoutFilled = MyIcon(Icons.Rounded.FitnessCenter,       24.dp, false, null, R.string.workout)
    val workoutOutlined = MyIcon(Icons.Rounded.FitnessCenter,    24.dp, false, null, R.string.workout)
    val logsFilled = MyIcon(Icons.AutoMirrored.Rounded.List,      24.dp, false, null, R.string.logs)
    val logsOutlined = MyIcon(Icons.AutoMirrored.Rounded.List,   24.dp, false, null, R.string.logs)
    val moreFilled = MyIcon(Icons.Rounded.MoreHoriz,      24.dp, false, null, R.string.more)
    val moreOutlined = MyIcon(Icons.Outlined.MoreHoriz,   24.dp, false, null, R.string.more)
}

object TopAppBarIcon {
    val back = MyIcon(Icons.AutoMirrored.Rounded.ArrowBack,     22.dp, false, null, R.string.back)
    val edit = MyIcon(Icons.Rounded.Edit,                       22.dp, false, null, R.string.edit)
    val close = MyIcon(Icons.Rounded.Close,                     22.dp, false, null, R.string.close)
    val more = MyIcon(Icons.Rounded.MoreVert,                   22.dp, false, null, R.string.more_options)
    val closeImageScreen = MyIcon(Icons.Rounded.Close,          22.dp, false, CustomColor.white, R.string.close)
}

object IconTextButtonIcon {
    val qrCode = MyIcon(Icons.Rounded.QrCode,                 24.dp, false, null, null)
    val add = MyIcon(Icons.Rounded.Add,                       24.dp, false, null, R.string.example)
    val delete = MyIcon(Icons.Rounded.Delete,                 24.dp, false, null, R.string.example)
    val leftArrow = MyIcon(Icons.Rounded.KeyboardArrowLeft,   30.dp, false, null, R.string.example)
    val rightArrow = MyIcon(Icons.Rounded.KeyboardArrowRight, 30.dp, false, null, R.string.example)
}

object FabIcon {
    val add = MyIcon(Icons.Rounded.Add, 24.dp, false, null, R.string.example)
    val map = MyIcon(Icons.Rounded.Map, 22.dp, false, null, R.string.example)
}

object MapButtonIcon {
    val focusOnToTarget = MyIcon(Icons.Rounded.LocationOn,          24.dp, false, null, R.string.example)
    val disabledFocusOnToTarget = MyIcon(Icons.Rounded.LocationOn,  24.dp, true, null, R.string.example)
}





object MyIcons {

    //error
    val error = MyIcon(Icons.Rounded.ErrorOutline,              40.dp, false, null, R.string.example)

    //sign in screen
    val signIn = MyIcon(Icons.AutoMirrored.Rounded.Login,       36.dp, true, null, R.string.example)
    val internetUnavailable = MyIcon(Icons.Rounded.CloudOff,    40.dp, true, null, R.string.example)
    val internetUnavailableWhite = MyIcon(Icons.Rounded.CloudOff, 40.dp, false, Color.White, R.string.example)

    //no item
    val noTrips = MyIcon(Icons.Rounded.Luggage,       40.dp, true, null, R.string.example)
    val noPlan = MyIcon(Icons.Rounded.EditNote,       40.dp, true, null, R.string.example)
    val noSpot = MyIcon(Icons.Rounded.LocationOff,    40.dp, true, null, R.string.example)

    //edit profile
    val changeProfileImage = MyIcon(Icons.Rounded.Image,    24.dp, false, null, null)
    val deleteProfileImage = MyIcon(Icons.Rounded.Delete,   24.dp, false, null, null)

    //image card
    val deleteImage = MyIcon(Icons.Rounded.Close,       16.dp, false, null, R.string.example)
    val imageLoadingError = MyIcon(Icons.Rounded.Error, 36.dp, false, null, R.string.example)

    //search / text input
    val searchLocation = MyIcon(Icons.Rounded.Search,   24.dp, false, null, R.string.example)
    val searchFriend = MyIcon(Icons.Rounded.Search,     24.dp, false, null, R.string.example)
    val clearInputText = MyIcon(Icons.Rounded.Close,    22.dp, false, null, R.string.example)

    //item expand collapse
    val expand = MyIcon(Icons.Rounded.ExpandMore,   22.dp, true, null, R.string.example)
    val collapse = MyIcon(Icons.Rounded.ExpandLess, 22.dp, true, null, R.string.example)

    //
    val delete = MyIcon(Icons.Rounded.Delete,            22.dp, true, null, R.string.example)
    val deleteSpot = MyIcon(Icons.Rounded.Delete,            22.dp, true, null, R.string.example)
    val deleteStartTime = MyIcon(Icons.Rounded.Delete,   22.dp, true, null, R.string.example)
    val deleteEndTime = MyIcon(Icons.Rounded.Delete,     22.dp, true, null, R.string.example)
    val dragHandle = MyIcon(Icons.Rounded.DragHandle,    22.dp, true, null, R.string.example)
    val clickableItem = MyIcon(Icons.AutoMirrored.Rounded.NavigateNext,    22.dp, true, null, R.string.example)


    //set color
    val setColor = MyIcon(Icons.Outlined.Palette,   24.dp, false, null, R.string.example)
    val selectedColor = MyIcon(Icons.Rounded.Done,  22.dp, false, null, R.string.example)


    //invited friend
    val viewOnly = MyIcon(Icons.Rounded.Visibility,     24.dp, true, null, R.string.example)
    val allowEdit = MyIcon(Icons.Rounded.Edit,          24.dp, true, null, R.string.example)

    //invite friend
    val qrCode = MyIcon(Icons.Rounded.QrCodeScanner,    40.dp, false, null, null)
    val flashOn = MyIcon(Icons.Rounded.FlashOn,         22.dp, false, null, R.string.example)
    val flashOff = MyIcon(Icons.Rounded.FlashOff,       22.dp, false, null, R.string.example)

    //date time
    val date = MyIcon(Icons.Rounded.CalendarMonth,      22.dp, true, null, R.string.example)
    val time = MyIcon(Icons.Rounded.AccessTime,         20.dp, true, null, R.string.example)
    val setTime = MyIcon(Icons.Rounded.MoreTime,        20.dp, true, null, R.string.example)
    val rightArrowTo = MyIcon(Icons.Rounded.East,       22.dp, true, null, R.string.example)
    val rightArrowToSmall = MyIcon(Icons.Rounded.East,  18.dp, true, null, R.string.example)

    //trip creation options dialog
    val manual = MyIcon(Icons.Rounded.Edit,    26.dp, false, null, null)
    val ai = MyIcon(Icons.Rounded.Bolt,         26.dp, false, null, null)

    //set time dialog
    val switchToTextInput = MyIcon(Icons.Outlined.Keyboard,   22.dp, true, null, R.string.example)
    val switchToTouchInput = MyIcon(Icons.Rounded.Schedule,   22.dp, true, null, R.string.example)

    //information card
    val category = MyIcon(Icons.Rounded.Bookmarks,   20.dp, true, null, R.string.example)
    val budget = MyIcon(Icons.Rounded.Payments,      22.dp, true, null, R.string.example)
    val travelDistance = MyIcon(Icons.Rounded.Route, 20.dp, true, null, R.string.example)

    //setting
    val openInNew = MyIcon(Icons.Rounded.OpenInNew,     22.dp, true, null, R.string.example)
    val sendEmail = MyIcon(Icons.AutoMirrored.Rounded.Send,     22.dp, true, null, R.string.example)

    //share trip
    val deleteFriend = MyIcon(Icons.Rounded.Close,      24.dp, true, null, R.string.example)
    val friends = MyIcon(Icons.Rounded.People,          22.dp, true, null, R.string.example)
    val inviteFriend = MyIcon(Icons.Rounded.PersonAdd,  22.dp, false, null, R.string.example)
    val manager = MyIcon(Icons.Rounded.ManageAccounts,  22.dp, true, null, R.string.example)
    val leaveTrip = MyIcon(Icons.Rounded.Logout,           24.dp, true, null, R.string.example)
}