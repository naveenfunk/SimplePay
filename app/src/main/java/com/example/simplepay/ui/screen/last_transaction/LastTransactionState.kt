package com.example.simplepay.ui.screen.last_transaction

import com.example.simplepay.R
import com.example.simplepay.domain.model.TransactionInfo
import com.example.simplepay.domain.model.TransactionResult
import com.example.simplepay.domain.model.TransactionType

data class LastTransactionState(
    val amount: String = "",
    val cardInfo: String = "",
    val transactionResId: Int = R.string.purchase,
    val resultResId: Int = R.string.failure,
    val isLastTransactionAvailalbe: Boolean = false
)

fun TransactionInfo.toState() = LastTransactionState(
    amount = amount.toString(),
    transactionResId = getTransactionResId(transactionType),
    resultResId = getTransactionResultResId(result),
    cardInfo = cardInfo.cardPan
)

fun getTransactionResId(transactionType: TransactionType) =
    when(transactionType) {
        TransactionType.PURCHASE -> R.string.purchase
        TransactionType.REFUND -> R.string.refund
        TransactionType.CASHOUT -> R.string.cashout
    }

fun getTransactionResultResId(transactionResult: TransactionResult) =
    when(transactionResult) {
        TransactionResult.APPROVED -> R.string.approved
        TransactionResult.DECLINED -> R.string.declined
        TransactionResult.CANCELLED -> R.string.cancelled
        TransactionResult.FAILURE -> R.string.failure
    }