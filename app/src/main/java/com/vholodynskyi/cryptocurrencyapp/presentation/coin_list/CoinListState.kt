package com.vholodynskyi.cryptocurrencyapp.presentation.coin_list

import com.vholodynskyi.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
