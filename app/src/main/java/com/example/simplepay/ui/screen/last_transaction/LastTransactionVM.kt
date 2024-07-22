package com.example.simplepay.ui.screen.last_transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplepay.domain.usecase.GetLastTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LastTransactionVM @Inject constructor(
    getLastTransactionUseCase: GetLastTransactionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LastTransactionState?>(null)
    val uiState: StateFlow<LastTransactionState?> = _uiState

    init {
        viewModelScope.launch {
            val transactionDomain = getLastTransactionUseCase()
            transactionDomain?.let {
                _uiState.value = it.toState()
            }
        }
    }
}