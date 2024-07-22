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
    var selectedMonth: Int = -1,
    val allMonth: List<Int>,
    var selectedYear: Int = -1,
    val allYear: List<Int>,
    val transactionResult: TransactionResult = TransactionResult.FAILURE,
    val isTransactionInfoValid: Boolean = false
)

data class TransactionUiType(
    val transactionType: TransactionType,
    val stringResId: Int
)
