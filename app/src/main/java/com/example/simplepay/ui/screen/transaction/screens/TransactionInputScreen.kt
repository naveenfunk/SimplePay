package com.example.simplepay.ui.screen.transaction.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplepay.R
import com.example.simplepay.domain.model.TransactionType
import com.example.simplepay.ui.screen.transaction.states.TransactionUiType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionInputScreen(
    amount: String,
    selectedTransactionType: TransactionUiType,
    transactionTypeOptions: List<TransactionUiType>,
    onAmountChange: (String) -> Unit,
    onTransactionTypeSelected: (TransactionUiType) -> Unit,
    onContinueClick: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

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
                value = amount,
                onValueChange = { onAmountChange(it) },
                label = { Text(stringResource(id = R.string.transaction_amount)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {

                OutlinedTextField(
                    value = stringResource(id = selectedTransactionType.stringResId),
                    onValueChange = { },
                    label = { Text(text = stringResource(id = R.string.transaction_type)) },
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    transactionTypeOptions.forEach { transactionType ->
                        DropdownMenuItem(
                            text = {
                                Text(stringResource(id = transactionType.stringResId))
                            },
                            onClick = {
                                onTransactionTypeSelected(transactionType)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Button(
            onClick = onContinueClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(stringResource(id = R.string.continue_text))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionInputScreenPreview() {
    TransactionInputScreen(
        amount = "0.0",
        selectedTransactionType = TransactionUiType(TransactionType.PURCHASE, R.string.none),
        transactionTypeOptions = emptyList(),
        onAmountChange = {},
        onContinueClick = {},
        onTransactionTypeSelected = {})
}

