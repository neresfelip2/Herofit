package br.com.neresfelip.herofitbrasil.presentation.ui

import kotlinx.coroutines.flow.MutableStateFlow

sealed class UIState<out T> {
    object Loading: UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(val description: String) : UIState<Nothing>()
}

fun <T> generateUIStateFlow(initialValue: UIState<T> = UIState.Loading) = MutableStateFlow<UIState<T>>(initialValue)
