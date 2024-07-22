package com.example.simplepay.ui.screen.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplepay.R
import com.example.simplepay.domain.model.TransactionResult
import com.example.simplepay.domain.model.TransactionType
import com.example.simplepay.ui.screen.transaction.states.TransactionInfoState
import com.example.simplepay.ui.screen.transaction.states.TransactionScreenState
import com.example.simplepay.ui.screen.transaction.states.TransactionUiType
import com.example.simplepay.ui.screen.transaction.validator.TransactionValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionVM @Inject constructor(
    private val transactionValidator: TransactionValidator
) : ViewModel() {

    private val _screenState = MutableStateFlow(TransactionScreenState.TRANSACTION_INPUT)
    val screenState: StateFlow<TransactionScreenState> = _screenState

    private val _transactionInfoState: MutableStateFlow<TransactionInfoState> = MutableStateFlow(
        TransactionInfoState(
            allType = getTransactionTypes(),
        )
    )
    val transactionInfo: StateFlow<TransactionInfoState> = _transactionInfoState

    fun createTransaction() {
        viewModelScope.launch(Dispatchers.Default) {
            gotoNextStep()
            if (isCardInfoValid()) {
                delay(2000)
                setTransactionResult(TransactionResult.APPROVED)
            } else {
                setTransactionResult(TransactionResult.FAILURE)
            }
            gotoNextStep()
        }
    }

    fun onAmountChanged(amount: String) {
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
            _transactionInfoState.value.copy(cardNumber = pan)
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

    fun gotoNextStep() {
        val currentScreenState = _screenState.value
        if (currentScreenState == TransactionScreenState.TRANSACTION_INPUT && !_transactionInfoState.value.isTransactionInfoValid) {
            return
        }
        val nextStep = TransactionScreenState.fromInt(currentScreenState.stepNumber + 1)
        nextStep?.let {
            _screenState.value = it
        }
    }

    fun gotoPreviousStep(): Boolean {
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
        return transactionValidator.isCardPanValid(currentInfo.cardNumber)
                && transactionValidator.isCardExpiryMonthValid(currentInfo.cardExpiryMonth)
                && transactionValidator.isCardExpiryYearValid(currentInfo.cardExpiryYear)
                && transactionValidator.isCardSecurityCodeValid(currentInfo.cvv)
    }

}