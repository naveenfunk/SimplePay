package com.example.simplepay.data.remote.source

import com.example.simplepay.data.remote.TransactionService
import com.example.simplepay.data.remote.model.TransactionRemote
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val transactionService: TransactionService
): RemoteDataSource {

    override suspend fun createTransaction(transaction: TransactionRemote) : Boolean {
        val response = transactionService.createTransaction(transaction)
        return response.isSuccessful
    }
}