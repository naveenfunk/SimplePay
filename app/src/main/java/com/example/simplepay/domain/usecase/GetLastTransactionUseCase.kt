package com.example.simplepay.domain.usecase

import com.example.simplepay.data.repository.TransactionRepository
import com.example.simplepay.domain.model.TransactionInfo
import javax.inject.Inject

class GetLastTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(): TransactionInfo? {
        return transactionRepository.getLastTransaction()
    }
}