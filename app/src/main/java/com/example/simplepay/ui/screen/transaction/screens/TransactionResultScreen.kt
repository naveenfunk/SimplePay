package com.example.simplepay.ui.screen.transaction.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplepay.R
import com.example.simplepay.domain.model.TransactionResult

@Composable
fun TransactionResultScreen(
    transactionResult: TransactionResult,
    onOkClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.transaction),
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = stringResource(id = when(transactionResult) {
                    TransactionResult.APPROVED -> R.string.approved
                    TransactionResult.FAILURE -> R.string.failure
                    TransactionResult.DECLINED -> R.string.declined
                    TransactionResult.CANCELLED -> R.string.cancelled
                }),
                modifier = Modifier.padding(16.dp)
            )
        }

        Button(
            onClick = { onOkClick() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(stringResource(id = R.string.ok).uppercase())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionResultScreenPreview() {
    TransactionResultScreen(TransactionResult.APPROVED, onOkClick = {})
}
