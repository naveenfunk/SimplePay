package com.example.simplepay.ui.screen.home

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

@Composable
fun HomeScreen(onStartTransactionClick: () -> Unit, onShowLastTransactionClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onStartTransactionClick() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.start_a_transaction))
            }

            Button(
                onClick = { onShowLastTransactionClick() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.show_last_transaction))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onStartTransactionClick = {}, onShowLastTransactionClick = {})
}
