package com.example.simplepay.ui.screen.transaction

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.simplepay.domain.model.TransactionResult

@Composable
fun TransactionResultScreen(
    transactionResult: TransactionResult,
    navController: NavController
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
                text = "Transaction",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = transactionResult.name,
                modifier = Modifier.padding(16.dp)
            )
        }

        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("OK")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionResultScreenPreview() {
    val navController = rememberNavController()
    TransactionResultScreen(TransactionResult.APPROVED, navController)
}
