package com.example.simplepay.data.repository

import com.example.simplepay.domain.model.TransactionInfo

interface TransactionRepository {

    suspend fun createTransaction(transaction: TransactionInfo): TransactionInfo

    suspend fun getLastTransaction(): TransactionInfo?
}