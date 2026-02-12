package br.com.neresfelip.herofitbrasil.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.neresfelip.herofitbrasil.data.mapper.LeagueDetailMapper.toUI
import br.com.neresfelip.herofitbrasil.domain.usecase.LeagueDetailUseCase
import br.com.neresfelip.herofitbrasil.presentation.model.LeagueDetailUI
import br.com.neresfelip.herofitbrasil.presentation.ui.UIState
import br.com.neresfelip.herofitbrasil.presentation.ui.generateUIStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeagueDetailViewModel @Inject constructor(
    private val leagueDetailUseCase: LeagueDetailUseCase,
    stateHandle: SavedStateHandle,
) : ViewModel() {

    val id: Int? = stateHandle["id"]

    private val _leagueDetailState = generateUIStateFlow<LeagueDetailUI>()
    val leagueDetailState = _leagueDetailState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = leagueDetailUseCase.getLeagueDetail(id ?: 0)
            result
                .onSuccess {
                    _leagueDetailState.value = UIState.Success(it.toUI())
                }.onFailure {
                    _leagueDetailState.value = UIState.Error(it.message ?: "Algo deu errado")
                }
        }
    }

}