package com.example.simplepay.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.simplepay.data.local.model.TransactionEntity

@Dao
interface TransactionDao {

    @Insert
    suspend fun saveTransaction(transaction: TransactionEntity) : Long

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions ORDER BY createdOn DESC LIMIT 1")
    suspend fun getLastTransaction(): TransactionEntity?
}