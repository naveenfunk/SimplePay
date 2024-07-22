package com.example.simplepay.data.local.source

import com.example.simplepay.data.local.TransactionDao
import com.example.simplepay.data.local.model.TransactionEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val transactionDao: TransactionDao) :
    LocalDataSource {

    override suspend fun saveTransaction(transaction: TransactionEntity): Long {
        return transactionDao.saveTransaction(transaction)
    }

    override suspend fun updateTransaction(transaction: TransactionEntity) {
        transactionDao.updateTransaction(transaction)
    }

    override suspend fun getLastTransaction(): TransactionEntity? {
        return transactionDao.getLastTransaction()
    }
}