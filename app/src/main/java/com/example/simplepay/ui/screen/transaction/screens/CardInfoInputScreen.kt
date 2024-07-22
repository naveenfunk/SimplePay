package com.example.simplepay.ui.screen.transaction.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplepay.R

@Composable
fun CardInfoInputScreen(
    cardPan: String,
    onCardPanChange: (String) -> Unit,
    cardExpiryMonth: String,
    onCardExpiryMonthChange: (String) -> Unit,
    cardExpiryYear: String,
    onCardExpiryYearChange: (String) -> Unit,
    cardSecurityCode: String,
    onCardSecurityCodeChange: (String) -> Unit,
    onContinueClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = cardPan,
                onValueChange = { onCardPanChange(it) },
                label = { Text(stringResource(id = R.string.card_pan)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = cardExpiryMonth,
                    onValueChange = { onCardExpiryMonthChange(it) },
                    label = { Text(stringResource(id = R.string.expiry_month)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = cardExpiryYear,
                    onValueChange = { onCardExpiryYearChange(it) },
                    label = { Text(stringResource(id = R.string.expiry_year)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }

            OutlinedTextField(
                value = cardSecurityCode,
                onValueChange = { onCardSecurityCodeChange(it) },
                label = { Text(stringResource(id = R.string.card_security_code)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                onContinueClick()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardInfoInputScreenPreview() {
    CardInfoInputScreen(
        cardPan = "",
        onCardPanChange = {},
        cardExpiryMonth = "",
        onCardExpiryMonthChange = {},
        cardExpiryYear = "",
        onCardExpiryYearChange = {},
        cardSecurityCode = "",
        onCardSecurityCodeChange = {},
        onContinueClick = {}
    )
}
