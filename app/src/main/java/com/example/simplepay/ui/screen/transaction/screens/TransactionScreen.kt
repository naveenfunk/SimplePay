package com.example.simplepay.ui.screen.transaction.screens

import androidx.compose.runtime.Composable
import com.example.simplepay.ui.screen.transaction.states.TransactionInfoState
import com.example.simplepay.ui.screen.transaction.states.TransactionScreenState
import com.example.simplepay.ui.screen.transaction.states.TransactionUiType

@Composable
fun TransactionScreen(
    viewState: TransactionInfoState,
    viewStepsState: TransactionScreenState,
    onAmountChange: (String) -> Unit,
    onTransactionTypeSelected: (TransactionUiType) -> Unit,
    onTransactionInfoContinueClick: () -> Unit,
    onCardInfoContinueClick: () -> Unit,
    onResultOkClick: () -> Unit,
) {
    when (viewStepsState) {
        TransactionScreenState.TRANSACTION_INPUT -> TransactionInputScreen(
            amount = viewState.amount,
            selectedTransactionType = viewState.selectedType,
            transactionTypeOptions = viewState.allType,
            onAmountChange = onAmountChange,
            onTransactionTypeSelected = onTransactionTypeSelected,
            onContinueClick = onTransactionInfoContinueClick
        )

        TransactionScreenState.CARD_INFO_INPUT -> CardInfoInputScreen(onContinueClick = onCardInfoContinueClick)
        TransactionScreenState.PROCESSING -> ProcessingScreen()
        TransactionScreenState.RESULT -> TransactionResultScreen(
            viewState.transactionResult,
            onOkClick = onResultOkClick
        )
    }
}