package com.example.simplepay.data.local.repository

import com.example.simplepay.data.local.model.toDomain
import com.example.simplepay.data.local.model.toEntity
import com.example.simplepay.data.local.source.LocalDataSource
import com.example.simplepay.domain.model.TransactionInfo
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    TransactionRepository {

    override suspend fun saveTransaction(transaction: TransactionInfo) {
        localDataSource.saveTransaction(transaction.toEntity(System.currentTimeMillis()))
    }

    override suspend fun getLastTransaction(): TransactionInfo? {
        return localDataSource.getLastTransaction()?.toDomain()
    }

}