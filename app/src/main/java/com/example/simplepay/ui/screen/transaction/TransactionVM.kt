package com.example.simplepay.ui.screen.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplepay.R
import com.example.simplepay.domain.model.TransactionResult
import com.example.simplepay.domain.model.TransactionType
import com.example.simplepay.domain.usecase.CreateTransactionUseCase
import com.example.simplepay.ui.screen.transaction.states.TransactionInfoState
import com.example.simplepay.ui.screen.transaction.states.TransactionScreenState
import com.example.simplepay.ui.screen.transaction.states.TransactionUiType
import com.example.simplepay.ui.screen.transaction.states.toDomain
import com.example.simplepay.ui.screen.transaction.validator.TransactionValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionVM @Inject constructor(
    private val transactionValidator: TransactionValidator,
    private val createTransactionUseCase: CreateTransactionUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow(TransactionScreenState.TRANSACTION_INPUT)
    val screenState: StateFlow<TransactionScreenState> = _screenState

    private val _transactionInfoState: MutableStateFlow<TransactionInfoState> = MutableStateFlow(
        TransactionInfoState(
            allType = getTransactionTypes(),
        )
    )
    val transactionInfo: StateFlow<TransactionInfoState> = _transactionInfoState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        //TODO: Here we can handle more kind of exceptions like, cancellation or declined returned from server
        throwable.printStackTrace()
        setTransactionResult(TransactionResult.FAILURE)
        gotoStep(TransactionScreenState.RESULT)
    }

    fun createTransaction() {
        viewModelScope.launch(Dispatchers.Default + exceptionHandler) {
            gotoStep(TransactionScreenState.PROCESSING)
            if (isCardInfoValid()) {
                withContext(Dispatchers.IO) {
                    val transactionCreated =
                        createTransactionUseCase(_transactionInfoState.value.toDomain())
                    setTransactionResult(transactionCreated.result)
                }
            } else {
                setTransactionResult(TransactionResult.FAILURE)
            }
            gotoStep(TransactionScreenState.RESULT)
        }
    }

    fun onAmountChanged(amount: String) {
        //TODO: adding validation to amount so that user cannot go to next step without a valid amount
        viewModelScope.launch(Dispatchers.Default) {
            if (transactionValidator.isAmountValid(amount))
                _transactionInfoState.emit(
                    _transactionInfoState.value.copy(
                        amount = amount,
                        isTransactionInfoValid = true
                    )
                )
        }
    }

    fun onTransactionTypeSelected(transactionUiType: TransactionUiType) {
        _transactionInfoState.value =
            _transactionInfoState.value.copy(selectedType = transactionUiType)
    }

    fun onCardPanChanged(pan: String) {
        _transactionInfoState.value =
            _transactionInfoState.value.copy(cardPan = pan)
    }

    fun onCardMonthChanged(month: String) {
        _transactionInfoState.value =
            _transactionInfoState.value.copy(cardExpiryMonth = month)
    }

    fun onCardYearChanged(year: String) {
        _transactionInfoState.value =
            _transactionInfoState.value.copy(cardExpiryYear = year)
    }

    fun onCardSecurityCodeChanged(code: String) {
        _transactionInfoState.value =
            _transactionInfoState.value.copy(cvv = code)
    }

    private fun setTransactionResult(transactionResult: TransactionResult) {
        _transactionInfoState.value =
            _transactionInfoState.value.copy(transactionResult = transactionResult)
    }

    fun gotoStep(step: TransactionScreenState) {
        val currentScreenState = _screenState.value
        if (currentScreenState == TransactionScreenState.TRANSACTION_INPUT && !_transactionInfoState.value.isTransactionInfoValid) {
            return
        }
        _screenState.value = step
    }

    fun gotoPreviousStep(): Boolean {
        // TODO: adding this feature to allow user to go back in the transaction flow to make any changes if required.
        val currentScreenState = _screenState.value
        if (currentScreenState.stepNumber >= TransactionScreenState.PROCESSING.stepNumber) {
            return false
        }
        val previousStep = TransactionScreenState.fromInt(currentScreenState.stepNumber - 1)
        previousStep?.let {
            _screenState.value = it
            return true
        }
        return false
    }

    private fun getTransactionTypes(): List<TransactionUiType> {
        return listOf(
            TransactionUiType(TransactionType.PURCHASE, R.string.purchase),
            TransactionUiType(TransactionType.REFUND, R.string.refund),
            TransactionUiType(TransactionType.CASHOUT, R.string.cashout)
        )
    }

    private fun isCardInfoValid(): Boolean {
        val currentInfo = _transactionInfoState.value
        return transactionValidator.isCardPanValid(currentInfo.cardPan)
                && transactionValidator.isCardExpiryMonthValid(currentInfo.cardExpiryMonth)
                && transactionValidator.isCardExpiryYearValid(currentInfo.cardExpiryYear)
                && transactionValidator.isCardSecurityCodeValid(currentInfo.cvv)
    }

}