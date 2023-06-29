package fetskovich.evgeny.app.features.ui.addbankcard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fetskovich.evgeny.app.core.validation.ValidationFieldState
import fetskovich.evgeny.presentation.theme.ApplicationTheme
import fetskovich.evgeny.presentation.theme.BasicTheme
import fetskovich.evgeny.recipeskmm.app.R

private val smallFieldModifier = Modifier
    .padding(
        top = 6.dp,
    )
    .width(90.dp)

@Composable
fun AddCardFieldsComponent(
    cardNumber: ValidationFieldState,
    expirationDate: ValidationFieldState,
    cvv: ValidationFieldState,
    onCardNumberChanged: (String) -> Unit,
    onExpirationDateChanged: (String) -> Unit,
    onCvvChanged: (String) -> Unit,
    onTakeCardFromPhotoClick: () -> Unit,
    modifier: Modifier
) {
    val focusManager = LocalFocusManager.current

    Card(
        backgroundColor = ApplicationTheme.colors.background,
        elevation = 4.dp,
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            CardNumberField(
                cardNumber = cardNumber,
                onCardNumberChanged = onCardNumberChanged,
                focusManager = focusManager,
                onTakeCardFromPhotoClick = onTakeCardFromPhotoClick,
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(
                        top = 8.dp
                    )
                    .fillMaxWidth()
            ) {

                ExpirationDateField(
                    expirationDate = expirationDate,
                    onExpirationDateChanged = onExpirationDateChanged,
                    focusManager = focusManager,
                )

                CvvField(
                    cvv = cvv,
                    onCvvChanged = onCvvChanged,
                    focusManager = focusManager,
                )
            }
        }

    }
}

@Composable
private fun ColumnScope.CardNumberField(
    cardNumber: ValidationFieldState,
    onCardNumberChanged: (String) -> Unit,
    focusManager: FocusManager,
    onTakeCardFromPhotoClick: () -> Unit,
) {
    Text(
        text = stringResource(id = R.string.add_another_bank_card_card_number),
        style = MaterialTheme.typography.body2,
        color = ApplicationTheme.colors.secondaryVariant,
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .border(
                BorderStroke(
                    2.dp,
                    if (cardNumber is ValidationFieldState.Invalid) {
                        ApplicationTheme.colors.error
                    } else {
                        ApplicationTheme.colors.secondaryVariant
                    }
                ),
                shape = RoundedCornerShape(8.dp)
            )
    ) {

        TextField(
            value = cardNumber.text,
            onValueChange = onCardNumberChanged,
            maxLines = 1,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .weight(1f)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_photo),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = onTakeCardFromPhotoClick
                )
        )

        Spacer(modifier = Modifier.width(20.dp))
    }

    if (cardNumber is ValidationFieldState.Invalid) {
        Text(
            text = cardNumber.errorMessage,
            color = ApplicationTheme.colors.error,
            fontSize = 10.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun ExpirationDateField(
    expirationDate: ValidationFieldState,
    onExpirationDateChanged: (String) -> Unit,
    focusManager: FocusManager,
) {
    Column {
        Text(
            text = stringResource(id = R.string.add_another_bank_card_expiration),
            style = MaterialTheme.typography.body2,
            color = ApplicationTheme.colors.secondaryVariant,
        )

        Column(
            modifier = smallFieldModifier
        ) {
            Box(
                modifier = Modifier
                    .border(
                        BorderStroke(
                            2.dp,
                            if (expirationDate is ValidationFieldState.Invalid) {
                                ApplicationTheme.colors.error
                            } else {
                                ApplicationTheme.colors.secondaryVariant
                            }
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                BasicTextField(
                    value = expirationDate.text,
                    onValueChange = {
                        onExpirationDateChanged(it.take(5))
                    },
                    singleLine = true,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Right)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    cursorBrush = SolidColor(ApplicationTheme.colors.primary),
                    modifier = Modifier
                        .padding(
                            start = 6.dp,
                            top = 4.dp,
                            bottom = 4.dp,
                        )
                        .fillMaxWidth()

                )
            }

            if (expirationDate is ValidationFieldState.Invalid) {
                Text(
                    text = expirationDate.errorMessage,
                    color = ApplicationTheme.colors.error,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CvvField(
    cvv: ValidationFieldState,
    onCvvChanged: (String) -> Unit,
    focusManager: FocusManager,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        Text(
            text = stringResource(id = R.string.add_another_bank_card_cvv),
            style = MaterialTheme.typography.body2,
            color = ApplicationTheme.colors.secondaryVariant,
        )

        Column(
            modifier = smallFieldModifier
        ) {
            Box(
                modifier = Modifier
                    .border(
                        BorderStroke(
                            2.dp,
                            if (cvv is ValidationFieldState.Invalid) {
                                ApplicationTheme.colors.error
                            } else {
                                ApplicationTheme.colors.secondaryVariant
                            }
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                BasicTextField(
                    value = cvv.text,
                    onValueChange = {
                        onCvvChanged(it.take(4))
                    },
                    singleLine = true,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    cursorBrush = SolidColor(ApplicationTheme.colors.primary),
                    modifier = Modifier
                        .padding(
                            start = 6.dp,
                            top = 4.dp,
                            bottom = 4.dp,
                        )
                        .fillMaxWidth()
                )
            }

            if (cvv is ValidationFieldState.Invalid) {
                Text(
                    text = cvv.errorMessage,
                    color = ApplicationTheme.colors.error,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddCardFieldsComponentPreview() {
    BasicTheme {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {

            AddCardFieldsComponent(
                cardNumber = ValidationFieldState.Valid("5555 6666 1231 12312"),
                expirationDate = ValidationFieldState.Invalid("1212", "Wrong text"),
                cvv = ValidationFieldState.Initial(),
                onCardNumberChanged = {},
                onExpirationDateChanged = {},
                onCvvChanged = {},
                onTakeCardFromPhotoClick = {},
                modifier = Modifier
            )
        }
    }
}