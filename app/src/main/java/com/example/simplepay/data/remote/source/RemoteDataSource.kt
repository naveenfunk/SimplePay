package com.example.simplepay.data.remote.source

import com.example.simplepay.data.remote.model.TransactionRemote

interface RemoteDataSource {

    suspend fun createTransaction(transaction: TransactionRemote): Boolean
}