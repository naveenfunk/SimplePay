package com.example.simplepay.data.local.repository

import com.example.simplepay.data.local.source.LocalDataSource
import com.example.simplepay.domain.model.TransactionInfo
import com.example.simplepay.domain.model.toEntity
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    TransactionRepository {

    override suspend fun saveTransaction(transaction: TransactionInfo) {
        localDataSource.saveTransaction(transaction.toEntity())
    }

}