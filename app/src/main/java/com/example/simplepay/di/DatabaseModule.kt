package com.example.simplepay.di

import android.content.Context
import androidx.room.Room
import com.example.simplepay.data.local.TransactionDao
import com.example.simplepay.data.local.TransactionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): TransactionDao {
        return Room.databaseBuilder(
            context,
            TransactionDatabase::class.java, "simplepay-db"
        ).fallbackToDestructiveMigration().build().transactionDao()
    }
}