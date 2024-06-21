package com.example.simplepay.ui.screen.transaction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CardInfoInputScreen(onContinueClick: () -> Unit) {
    var cardPan by remember { mutableStateOf("") }
    var cardExpiryMonth by remember { mutableStateOf("") }
    var cardExpiryYear by remember { mutableStateOf("") }
    var cardSecurityCode by remember { mutableStateOf("") }

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
                onValueChange = { cardPan = it },
                label = { Text("Card PAN") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = cardExpiryMonth,
                    onValueChange = { cardExpiryMonth = it },
                    label = { Text("Expiry Month") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = cardExpiryYear,
                    onValueChange = { cardExpiryYear = it },
                    label = { Text("Expiry Year") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }

            OutlinedTextField(
                value = cardSecurityCode,
                onValueChange = { cardSecurityCode = it },
                label = { Text("Card Security Code") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                // Log card information (for demonstration purposes only, do not log sensitive information in production)
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
    CardInfoInputScreen(onContinueClick = {})
}
