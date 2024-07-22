package com.example.simplepay.data.local

import androidx.room.Dao
import androidx.room.Insert
import com.example.simplepay.data.local.model.TransactionEntity

@Dao
interface TransactionDao {

    @Insert
    suspend fun saveTransaction(transaction: TransactionEntity)

}