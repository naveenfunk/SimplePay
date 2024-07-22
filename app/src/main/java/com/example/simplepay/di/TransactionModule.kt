package com.example.simplepay.di

import com.example.simplepay.ui.screen.transaction.validator.TransactionValidator
import com.example.simplepay.ui.screen.transaction.validator.TransactionValidatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TransactionModule {

    @Binds
    abstract fun provideTransactionValidator(transactionValidatorImpl: TransactionValidatorImpl) : TransactionValidator
}