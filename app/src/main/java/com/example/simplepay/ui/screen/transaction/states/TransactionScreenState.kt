package com.example.simplepay.ui.screen.transaction.states

enum class TransactionScreenState(val stepNumber: Int) {
    TRANSACTION_INPUT(1),
    CARD_INFO_INPUT(2),
    PROCESSING(3),
    RESULT(4);

    companion object {
        private val map = entries.associateBy(TransactionScreenState::stepNumber)
        fun fromInt(stepNumber: Int) = map[stepNumber]
    }
}
