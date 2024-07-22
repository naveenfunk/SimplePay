package com.example.simplepay.ui.screen.transaction.states

import com.example.simplepay.R
import com.example.simplepay.domain.model.TransactionResult
import com.example.simplepay.domain.model.TransactionType

data class TransactionInfoState(
    var amount: String = "",
    var selectedType: TransactionUiType = TransactionUiType(
        TransactionType.PURCHASE,
        R.string.purchase
    ),
    val allType: List<TransactionUiType>,
    var cardNumber: String = "",
    var cvv: String = "",
    var cardExpiryMonth: String = "",
    var cardExpiryYear: String = "",
    val transactionResult: TransactionResult = TransactionResult.FAILURE,
    val isTransactionInfoValid: Boolean = false
)

data class TransactionUiType(
    val transactionType: TransactionType,
    val stringResId: Int
)
