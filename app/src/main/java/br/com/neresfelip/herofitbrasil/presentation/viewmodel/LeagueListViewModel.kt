package br.com.neresfelip.herofitbrasil.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.neresfelip.herofitbrasil.data.mapper.LeagueMapper.toUI
import br.com.neresfelip.herofitbrasil.domain.usecase.LeagueListUseCase
import br.com.neresfelip.herofitbrasil.presentation.ui.UIState
import br.com.neresfelip.herofitbrasil.presentation.ui.generateUIStateFlow
import br.com.neresfelip.herofitbrasil.presentation.model.LeagueUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeagueListViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val leagueListUseCase: LeagueListUseCase
) : ViewModel() {

    private val sportName: String? = stateHandle["name"]

    private val _leagueListState = generateUIStateFlow<List<LeagueUI>>()
    val leagueListState = _leagueListState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = leagueListUseCase.getLeaguesBySportName(sportName ?: "")
            result
                .onSuccess {
                    _leagueListState.value = UIState.Success(it.toUI())
                }.onFailure {
                    _leagueListState.value = UIState.Error(it.message ?: "Algo deu errado")
                }
        }
    }

}