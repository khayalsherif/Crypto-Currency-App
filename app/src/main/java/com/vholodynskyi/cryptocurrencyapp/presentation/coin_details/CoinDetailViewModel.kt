package com.vholodynskyi.cryptocurrencyapp.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vholodynskyi.cryptocurrencyapp.common.Constants.PARAM_COIN_ID
import com.vholodynskyi.cryptocurrencyapp.common.Resource
import com.vholodynskyi.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: dagger.Lazy<GetCoinUseCase>,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase.get().invoke(coinId)
            .onEach { result ->
                _state.value = when (result) {
                    is Resource.Error -> CoinDetailState(error = result.message ?: "")
                    is Resource.Loading -> CoinDetailState(isLoading = true)
                    is Resource.Success -> CoinDetailState(coin = result.data)
                }
            }.launchIn(viewModelScope)
    }
}