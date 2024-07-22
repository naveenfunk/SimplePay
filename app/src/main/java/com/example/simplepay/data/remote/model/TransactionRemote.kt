package com.example.simplepay.data.remote.model

import com.example.simplepay.domain.model.TransactionInfo

data class TransactionRemote(
    val amount: String,
    val transactionType: String,
    val cardInfo: String,
    val createdOn: Long
)

fun TransactionInfo.toRemote(createdOn: Long) = TransactionRemote(
    amount = amount.toString(),
    transactionType = transactionType.name,
    cardInfo = cardInfo.cardPan.takeLast(6),
    createdOn = createdOn
)