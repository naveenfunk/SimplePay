package com.example.simplepay.ui.screen.transaction.states

import com.example.simplepay.R
import com.example.simplepay.domain.model.CardInfo
import com.example.simplepay.domain.model.TransactionInfo
import com.example.simplepay.domain.model.TransactionResult
import com.example.simplepay.domain.model.TransactionType
import java.math.BigDecimal

data class TransactionInfoState(
    var amount: String = "",
    var selectedType: TransactionUiType = TransactionUiType(
        TransactionType.PURCHASE,
        R.string.purchase
    ), //TODO: making purchase as default option as user can proceed to next step without selecting anything
    val allType: List<TransactionUiType>,
    var cardPan: String = "",
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

fun TransactionInfoState.toDomain() = TransactionInfo(
    amount = BigDecimal(amount),
    transactionType = selectedType.transactionType,
    cardInfo = CardInfo(
        cardPan = cardPan,
        expiryMonth = if (cardExpiryMonth.isNotBlank()) cardExpiryMonth.toInt() else -1,
        expiryYear = if (cardExpiryYear.isNotBlank()) cardExpiryYear.toInt() else -1,
        securityCode = cvv
    ),
    result = transactionResult
)