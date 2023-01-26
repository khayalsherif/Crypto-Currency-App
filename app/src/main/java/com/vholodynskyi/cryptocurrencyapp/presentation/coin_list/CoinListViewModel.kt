package com.vholodynskyi.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vholodynskyi.cryptocurrencyapp.common.Resource
import com.vholodynskyi.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: dagger.Lazy<GetCoinsUseCase>
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase.get().invoke().onEach { result ->
            _state.value = when (result) {
                is Resource.Error -> CoinListState(error = result.message ?: "")
                is Resource.Loading -> CoinListState(isLoading = true)
                is Resource.Success -> CoinListState(coins = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }
}