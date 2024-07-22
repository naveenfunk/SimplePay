package com.example.simplepay

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplepay.AppRoute.HOME
import com.example.simplepay.AppRoute.LAST_TRANSACTION
import com.example.simplepay.AppRoute.TRANSACTION
import com.example.simplepay.ui.screen.home.HomeScreen
import com.example.simplepay.ui.screen.last_transaction.LastTransactionScreen
import com.example.simplepay.ui.screen.last_transaction.LastTransactionVM
import com.example.simplepay.ui.screen.transaction.TransactionVM
import com.example.simplepay.ui.screen.transaction.screens.TransactionScreen
import com.example.simplepay.ui.screen.transaction.states.TransactionScreenState


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) {
            HomeScreen(
                onShowLastTransactionClick = { navController.navigate(LAST_TRANSACTION) },
                onStartTransactionClick = { navController.navigate(TRANSACTION) }
            )
        }
        composable(TRANSACTION) {
            val viewModel = hiltViewModel<TransactionVM>()

            val uiState by viewModel.screenState.collectAsStateWithLifecycle(
                initialValue = viewModel.screenState.value,
                minActiveState = Lifecycle.State.STARTED
            )
            val viewState by viewModel.transactionInfo.collectAsStateWithLifecycle(
                minActiveState = Lifecycle.State.STARTED
            )

            BackHandler {
                if (!viewModel.gotoPreviousStep()) {
                    navController.navigateUp()
                }
            }
            TransactionScreen(
                viewState = viewState,
                viewStepsState = uiState,
                onAmountChange = { viewModel.onAmountChanged(it) },
                onTransactionTypeSelected = { viewModel.onTransactionTypeSelected(it) },
                onTransactionInfoContinueClick = { viewModel.gotoStep(TransactionScreenState.CARD_INFO_INPUT) },
                onCardInfoContinueClick = { viewModel.createTransaction() },
                onResultOkClick = { navController.navigateClearingBackStack(HOME) },
                onCardPanChange = { viewModel.onCardPanChanged(it) },
                onCardExpiryMonthChange = { viewModel.onCardMonthChanged(it) },
                onCardExpiryYearChange = { viewModel.onCardYearChanged(it) },
                onCardSecurityChange = { viewModel.onCardSecurityCodeChanged(it) },
            )
        }
        composable(LAST_TRANSACTION) {
            val viewModel = hiltViewModel<LastTransactionVM>()

            val uiState by viewModel.uiState.collectAsStateWithLifecycle(
                minActiveState = Lifecycle.State.STARTED
            )

            LastTransactionScreen(uiState, onHomeClick = { navController.navigateClearingBackStack(HOME) })
        }
    }
}

fun NavHostController.navigateClearingBackStack(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = false
        }
        launchSingleTop = true
        restoreState = false
    }
}

object AppRoute {
    const val HOME = "home"
    const val TRANSACTION = "transaction"
    const val LAST_TRANSACTION = "last_transaction"
}