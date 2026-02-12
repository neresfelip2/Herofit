package br.com.neresfelip.herofitbrasil.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.neresfelip.herofitbrasil.data.mapper.SportMapper.toUI
import br.com.neresfelip.herofitbrasil.domain.usecase.MainUseCase
import br.com.neresfelip.herofitbrasil.presentation.ui.UIState
import br.com.neresfelip.herofitbrasil.presentation.ui.generateUIStateFlow
import br.com.neresfelip.herofitbrasil.presentation.model.SportUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: MainUseCase
) : ViewModel() {

    private val _sportListState = generateUIStateFlow<List<SportUI>>()
    val sportListState = _sportListState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = useCase.getAllSports()
            result
                .onSuccess {
                    _sportListState.value = UIState.Success(it.toUI())
                }
                .onFailure {
                    _sportListState.value = UIState.Error(it.message ?: "Algo deu errado")
                }
        }
    }

}