package com.example.simplepay.ui.screen.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplepay.domain.TransactionFlow
import com.example.simplepay.domain.model.TransactionViewEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionVM @Inject constructor(
    private val transactionFlow: TransactionFlow
) : ViewModel() {

    private val _screenState = MutableLiveData<TransactionViewEffect>(TransactionViewEffect.Init)
    val screenState: LiveData<TransactionViewEffect> = _screenState

    init {
        CoroutineScope(Dispatchers.Main).launch {
            transactionFlow.viewEffect.collect {
                _screenState.value = it
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            transactionFlow.run()
        }
    }
}