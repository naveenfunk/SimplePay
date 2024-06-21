package com.example.simplepay.ui.screen.transaction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.simplepay.domain.model.CardInfo
import com.example.simplepay.domain.model.TransactionInfo
import com.example.simplepay.domain.model.TransactionViewEffect

@Composable
fun TransactionScreen(navController: NavController) {
    val viewModel = hiltViewModel<TransactionVM>()

    val state by viewModel.screenState.asFlow().collectAsStateWithLifecycle(
        initialValue = viewModel.screenState.value!!,
        minActiveState = Lifecycle.State.STARTED
    )

    TransactionScreenView(state, navController)
}

@Composable
fun TransactionScreenView(viewState: TransactionViewEffect, navController: NavController) {
    when (viewState) {
        is TransactionViewEffect.TransactionInputScreen -> TransactionInputScreen {
            // FIXME: Send the transaction info input from screen to the transaction flow
            val dummyTransactionInfo = TransactionInfo()
            viewState.onContinueButton(dummyTransactionInfo)
        }

        is TransactionViewEffect.CardInfoInputScreen -> CardInfoInputScreen {
            // FIXME: Hook it to the real card info input from screen to the transaction flow
            val dummyCardInfo = CardInfo()
            viewState.onContinueButton(dummyCardInfo)
        }

        TransactionViewEffect.ProcessingScreen -> ProcessingScreen()
        is TransactionViewEffect.ResultScreen -> TransactionResultScreen(
            viewState.result,
            navController
        )

        TransactionViewEffect.Init -> Unit
    }
}