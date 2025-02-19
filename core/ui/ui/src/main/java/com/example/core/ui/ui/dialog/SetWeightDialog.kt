package com.example.core.ui.ui.dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core.ui.designsystem.components.button.MinusButton
import com.example.core.ui.designsystem.components.button.PlusButton
import com.example.core.ui.designsystem.components.utils.MyCard
import com.example.core.ui.designsystem.components.utils.MySpacerColumn
import com.example.core.ui.designsystem.components.utils.MySpacerRow
import com.example.core.ui.designsystem.theme.CustomColor
import com.example.core.ui.ui.MyTextField
import com.example.core.ui.ui.R
import com.example.core.ui.ui.dialog.component.CancelDialogButton
import com.example.core.ui.ui.dialog.component.DialogButtonLayout
import com.example.core.ui.ui.dialog.component.DialogButtons
import com.example.core.ui.ui.dialog.component.MyDialog
import com.example.core.ui.ui.dialog.component.OkDialogButton

@Composable
fun SetWeightDialog(
    initialWeight: Float?,

    onOkClick: (weight: Float?) -> Unit,
    onDismissRequest: () -> Unit
){
    var currentWeight by rememberSaveable { mutableStateOf(initialWeight) }

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    val newValueText: String by rememberSaveable {
        mutableStateOf(
            if (initialWeight == 0.0f) ""
            else if (initialWeight == initialWeight?.toInt()?.toFloat())
                initialWeight?.toInt().toString()
            else
                initialWeight.toString().trimEnd('0').trimEnd('.')
        )
    }

    val textFieldValue = remember {
        mutableStateOf(
            TextFieldValue(newValueText, TextRange(newValueText.length))
        )
    }

    var isInvalidText by rememberSaveable { mutableStateOf(false) }

    val borderColor = if (isInvalidText) CustomColor.outlineError
        else Color.Transparent



    MyDialog(
        onDismissRequest = onDismissRequest,

        titleText = stringResource(R.string.set_weight),

        bodyContent = {
            WeightField(
                focusManager = focusManager,
                focusRequester = focusRequester,
                borderColor = borderColor,
                textFieldValue = textFieldValue,
                onValueChange = {
                    textFieldValue.value = TextFieldValue(it.text, TextRange(it.text.length))
                    isInvalidText = !isValidFloatText(it.text, 1)
                }
            )
        },
        buttonContent = {
            DialogButtons(
                dialogButtonLayout = DialogButtonLayout.HORIZONTAL,
                negativeButtonContent = {
                    //cancel button
                    CancelDialogButton(
                        onClick = onDismissRequest,
                        modifier = it
                    )
                },
                positiveButtonContent = {
                    //ok button
                    OkDialogButton(
                        onClick = {
                            if (!isInvalidText) {
                                val text = if (textFieldValue.value.text == "") "0"
                                    else textFieldValue.value.text

                                onOkClick(
                                    text
                                        //trim 12.34 -> 12.3
                                        .replace(Regex("(\\d+\\.\\d)\\d*"), "$1")
                                        .toFloat()
                                )
                            }
                        },
                        enabled = !isInvalidText,
                        modifier = it
                    )
                }
            )
        }
    )
}



@Composable
private fun WeightField(
    focusManager: FocusManager,
    focusRequester: FocusRequester,
    borderColor: Color,
    textFieldValue: MutableState<TextFieldValue>,
    onValueChange: (TextFieldValue) -> Unit,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            //minus button
            MinusButton(
                onClick = {
                    onValueChange(minusPlusTextFieldValue(textFieldValue, false))
                }
            )

            MySpacerRow(16.dp)

            //text field
            MyCard(
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceDim),
                modifier = Modifier
                    .width(200.dp)
                    .border(1.dp, borderColor, RoundedCornerShape(16.dp)),
            ) {
                MyTextField(
                    inputText = textFieldValue.value,
                    inputTextStyle = MaterialTheme.typography.displayLarge.copy(textAlign = TextAlign.Center),
                    placeholderText = "0.0",
                    placeholderTextStyle = MaterialTheme.typography.displayLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        .copy(textAlign = TextAlign.Center),
                    onValueChange = { onValueChange(it) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    modifier = Modifier.padding(vertical = 16.dp),
                    textFieldModifier = Modifier.focusRequester(focusRequester)
                )
            }

            MySpacerRow(16.dp)

            //plus button
            PlusButton(
                onClick = {
                    onValueChange(minusPlusTextFieldValue(textFieldValue, true))
                }
            )
        }

        MySpacerColumn(8.dp)

        //kg
        Text(
            text = stringResource(id = R.string.kg),
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )
    }
}


private fun isValidFloatText(
    numberText: String,
    numberOfDecimalPlaces: Int
): Boolean{

    val dotLimitCnt = if (numberOfDecimalPlaces == 0) 0
    else 1

    var dotCnt = 0

    for (char in numberText){
        if (char == '.')                 dotCnt++
        else if (char in '0'..'9')  continue
        else                             return false

        if (dotCnt > dotLimitCnt)
            return false
    }
    return true
}

private fun minusPlusTextFieldValue(
    textFieldValue: MutableState<TextFieldValue>,
    isPlus1: Boolean
): TextFieldValue {
    //TextFieldValue -> String
    val string = if (textFieldValue.value.text == "") "0"
                    else textFieldValue.value.text

    //String -> Float
    val float = string.toFloat()

    // -1 or +1
    val floatCalc =
        if (isPlus1){
            float + 1
        }
        else {
            if (float - 1 > 0) float - 1
            else 0.0f
        }


    //Float -> TextFieldValue
    val new = floatCalc.toString()
        .trimEnd('0').trimEnd('.')
        .replace(Regex("(\\d+\\.\\d)\\d*"), "$1")
    return TextFieldValue(new, TextRange(new.length))
}