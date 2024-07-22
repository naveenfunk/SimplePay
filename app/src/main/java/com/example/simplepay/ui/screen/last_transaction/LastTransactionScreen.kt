package com.example.simplepay.ui.screen.last_transaction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplepay.R

@Composable
fun LastTransactionScreen(viewState: LastTransactionState?, onHomeClick: () -> Unit) {

    if (viewState != null) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = stringResource(id = R.string.amount_s, viewState.amount))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(
                    id = R.string.transaction_type_s,
                    stringResource(id = viewState.transactionResId)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.card_pan_s, viewState.cardInfo))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(
                    id = R.string.transaction_result_s,
                    stringResource(id = viewState.resultResId)
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onHomeClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(stringResource(id = R.string.go_home).uppercase())
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.no_transaction_found_message)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLastTransactionScreen() {
    LastTransactionScreen(LastTransactionState(), onHomeClick = {})
}