package com.example.simplepay.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplepay.domain.model.TransactionResult
import com.example.simplepay.domain.model.TransactionType
import java.math.BigDecimal

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "amount") val amount: BigDecimal,
    @ColumnInfo(name = "type") val type: TransactionType,
    @ColumnInfo(name = "cardPan") val cardPan: String,
    @ColumnInfo(name = "result") val result: TransactionResult
)