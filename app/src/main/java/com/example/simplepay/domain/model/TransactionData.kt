package com.example.simplepay.domain.model

data class TransactionData(
    val transactionInfo: TransactionInfo = TransactionInfo(),
    val cardInfo: CardInfo = CardInfo(),
    val transactionResult: TransactionResult = TransactionResult.FAILURE,
)