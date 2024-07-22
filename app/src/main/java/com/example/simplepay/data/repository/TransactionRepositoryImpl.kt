package com.example.simplepay.data.repository

import com.example.simplepay.data.local.model.toDomain
import com.example.simplepay.data.local.model.toEntity
import com.example.simplepay.data.local.source.LocalDataSource
import com.example.simplepay.data.remote.model.toRemote
import com.example.simplepay.data.remote.source.RemoteDataSource
import com.example.simplepay.domain.model.TransactionInfo
import com.example.simplepay.domain.model.TransactionResult
import kotlinx.coroutines.delay
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) :
    TransactionRepository {

    override suspend fun createTransaction(transaction: TransactionInfo) : TransactionInfo {
        val transactionTime = System.currentTimeMillis()
        val transactionEntity = transaction.toEntity(transactionTime)
        val transactionDbId = localDataSource.saveTransaction(transactionEntity) // saving before server request
        val remoteResponse = remoteDataSource.createTransaction(transaction.toRemote(transactionTime))
        if (remoteResponse) {
            val updatedTransaction = transactionEntity.copy(id = transactionDbId, result = TransactionResult.APPROVED)
            localDataSource.updateTransaction(updatedTransaction) // saving after server request
            return updatedTransaction.toDomain()
        }
        return transactionEntity.toDomain()
    }

    override suspend fun getLastTransaction(): TransactionInfo? {
        return localDataSource.getLastTransaction()?.toDomain()
    }
}