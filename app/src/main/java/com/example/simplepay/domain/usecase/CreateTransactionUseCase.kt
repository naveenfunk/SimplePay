package com.example.simplepay.domain.usecase

import com.example.simplepay.data.repository.TransactionRepository
import com.example.simplepay.domain.model.TransactionInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(transactionInfo: TransactionInfo) : TransactionInfo {
        return transactionRepository.createTransaction(transactionInfo)
    }
}