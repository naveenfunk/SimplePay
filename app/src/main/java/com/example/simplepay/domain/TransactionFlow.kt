package com.example.simplepay.domain

import android.util.Log
import com.example.simplepay.domain.model.CardInfo
import com.example.simplepay.domain.model.TransactionData
import com.example.simplepay.domain.model.TransactionInfo
import com.example.simplepay.domain.model.TransactionResult
import com.example.simplepay.domain.model.TransactionViewEffect
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException


class TransactionFlow @Inject constructor(

) {
    private val transactionData = TransactionData()
    private val _viewEffect = MutableSharedFlow<TransactionViewEffect>()
    val viewEffect = _viewEffect.asSharedFlow()

    suspend fun run() {

        Log.i("TransactionFlow", "Step 1: display transaction input screen")
        val transactionInputWaiter = UIEventWaiter<TransactionInfo>()
        _viewEffect.emit(TransactionViewEffect.TransactionInputScreen {
            transactionInputWaiter.receive(it)
        })
        transactionInputWaiter.await()

        Log.i("TransactionFlow", "display card number input screen")
        val cardInputWaiter = UIEventWaiter<CardInfo>()
        _viewEffect.emit(TransactionViewEffect.CardInfoInputScreen {
            cardInputWaiter.receive(it)
        })
        cardInputWaiter.await()

        Log.i("TransactionFlow", "Step 3: display processing screen")
        _viewEffect.emit(TransactionViewEffect.ProcessingScreen)
        // TODO: Replace the delay with Rest API call to perform the transaction online. Send the transaction data to a Mock server and get the response from it.
        delay(3000)
        val transactionResult = TransactionResult.APPROVED

        Log.i("TransactionFlow", "Step 4: display result screen")
        _viewEffect.emit(TransactionViewEffect.ResultScreen(transactionResult))
        // TODO: Store the transaction record
    }
}

class UIEventWaiter<T> {
    private val deferred = CompletableDeferred<T>()

    suspend fun await(timeout: Long? = null): Result<T> = try {
        if (timeout == null) {
            Result.Success(deferred.await())
        } else {
            withTimeout(timeout) {
                Result.Success(deferred.await())
            }
        }
    } catch (e: TimeoutCancellationException) {
        Result.Failure(EFailureReason.TIMEOUT)
    } catch (e: CancellationException) {
        Result.Failure(EFailureReason.CANCEL)
    }

    fun receive(t: T) = deferred.complete(t)

    fun cancel() = deferred.cancel()

    /********************************* Result definition *************************/
    enum class EFailureReason { CANCEL, TIMEOUT }
    sealed interface Result<T> {
        data class Success<R>(val value: R) : Result<R>
        data class Failure<R>(val reason: EFailureReason) : Result<R>
    }
}
