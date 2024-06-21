package com.example.simplepay.domain.model

import java.math.BigDecimal
import java.time.YearMonth

sealed class TransactionViewEffect {
    data object Init : TransactionViewEffect()

    data class TransactionInputScreen(
        val onContinueButton: (transactionInfo: TransactionInfo) -> Unit
    ) : TransactionViewEffect()

    data class CardInfoInputScreen(
        val onContinueButton: (cardInfo: CardInfo) -> Unit
    ) : TransactionViewEffect()

    data object ProcessingScreen : TransactionViewEffect()

    data class ResultScreen(val result: TransactionResult) : TransactionViewEffect()
}

data class TransactionInfo(
    val amount: BigDecimal = BigDecimal.ZERO,
    val transactionType: TransactionType = TransactionType.PURCHASE,
)
data class CardInfo(
    val cardPan: String = "",
    val expiryDate: YearMonth = YearMonth.now(),
    val securityCode: String = "",
)

enum class TransactionResult{
    APPROVED,
    DECLINED,
    CANCELLED,
    FAILURE,
}