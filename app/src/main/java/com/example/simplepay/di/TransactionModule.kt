package com.example.simplepay.di

import com.example.simplepay.data.repository.TransactionRepository
import com.example.simplepay.data.repository.TransactionRepositoryImpl
import com.example.simplepay.data.local.source.LocalDataSource
import com.example.simplepay.data.local.source.LocalDataSourceImpl
import com.example.simplepay.data.remote.source.RemoteDataSource
import com.example.simplepay.data.remote.source.RemoteDataSourceImpl
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
    abstract fun provideTransactionValidator(transactionValidatorImpl: TransactionValidatorImpl): TransactionValidator

    @Binds
    abstract fun provideTransactionRepository(transactionRepositoryImpl: TransactionRepositoryImpl): TransactionRepository

    @Binds
    abstract fun provideTransactionLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun provideTransactionRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

}