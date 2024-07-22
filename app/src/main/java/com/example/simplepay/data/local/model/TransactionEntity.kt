package com.example.simplepay.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplepay.domain.model.CardInfo
import com.example.simplepay.domain.model.TransactionInfo
import com.example.simplepay.domain.model.TransactionResult
import com.example.simplepay.domain.model.TransactionType
import java.math.BigDecimal

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "amount") val amount: BigDecimal,
    @ColumnInfo(name = "type") val type: TransactionType,
    @ColumnInfo(name = "cardPan") val cardPan: String,
    @ColumnInfo(name = "result") val result: TransactionResult,
    @ColumnInfo(name = "createdOn") val createdOn: Long
)

fun TransactionEntity.toDomain() = TransactionInfo(
    amount = amount,
    transactionType = type,
    cardInfo = CardInfo(
        cardPan = cardPan,
        expiryMonth = -1,
        expiryYear = -1,
        securityCode = ""
    ), result = result
)

fun TransactionInfo.toEntity(createTime: Long) = TransactionEntity(
    amount = amount,
    type = transactionType,
    cardPan = cardInfo.cardPan.takeLast(6),
    result = result,
    createdOn = createTime
)