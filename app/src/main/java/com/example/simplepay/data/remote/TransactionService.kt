package com.example.simplepay.data.remote

import com.example.simplepay.data.remote.model.TransactionRemote
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface TransactionService {

    @PUT("createTransaction")
    suspend fun createTransaction(@Body transactionRemote: TransactionRemote): Response<String>
}