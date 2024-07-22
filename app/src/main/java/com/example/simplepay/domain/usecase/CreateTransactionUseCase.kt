package com.example.simplepay.domain.usecase

import com.example.simplepay.data.local.repository.TransactionRepository
import com.example.simplepay.domain.model.TransactionInfo
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(transactionInfo: TransactionInfo) {
        transactionRepository.saveTransaction(transactionInfo)
    }
}