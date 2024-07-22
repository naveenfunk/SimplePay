package com.example.simplepay.domain.model

import com.example.simplepay.data.local.model.TransactionEntity
import java.math.BigDecimal

data class TransactionInfo(
    val amount: BigDecimal,
    val transactionType: TransactionType,
    val cardInfo: CardInfo,
    val result: TransactionResult,
)

data class CardInfo(
    val cardPan: String,
    val expiryMonth: Int,
    val expiryYear: Int,
    val securityCode: String,
)

enum class TransactionResult {
    APPROVED,
    DECLINED,
    CANCELLED,
    FAILURE,
}

fun TransactionInfo.toEntity() = TransactionEntity(
    amount = amount,
    type = transactionType,
    cardPan = cardInfo.cardPan.takeLast(6),
    result = result
)