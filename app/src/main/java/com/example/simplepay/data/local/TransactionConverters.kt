package com.example.simplepay.data.local

import androidx.room.TypeConverter
import com.example.simplepay.domain.model.TransactionResult
import com.example.simplepay.domain.model.TransactionType
import java.math.BigDecimal

class TransactionConverters {

    @TypeConverter
    fun fromTransactionType(transactionType: TransactionType) = transactionType.name

    @TypeConverter
    fun toTransactionType(transactionType: String) = TransactionType.valueOf(transactionType)

    @TypeConverter
    fun fromTransactionResult(transactionResult: TransactionResult) = transactionResult.name

    @TypeConverter
    fun toTransactionResult(transactionResult: String) = TransactionResult.valueOf(transactionResult)

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal) = value.toString()

    @TypeConverter
    fun toBigDecimal(value: String) = BigDecimal(value)
}