package com.example.simplepay.data.local.repository

import com.example.simplepay.domain.model.TransactionInfo

interface TransactionRepository {

    suspend fun saveTransaction(transaction: TransactionInfo)

    suspend fun getLastTransaction(): TransactionInfo?
}