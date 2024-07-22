package com.example.simplepay.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.simplepay.data.local.model.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = true)
@TypeConverters(TransactionConverters::class)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun transactionDao() : TransactionDao
}