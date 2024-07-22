package com.example.simplepay.data.local.source

import com.example.simplepay.data.local.model.TransactionEntity

interface LocalDataSource {

    suspend fun saveTransaction(transaction: TransactionEntity)
}