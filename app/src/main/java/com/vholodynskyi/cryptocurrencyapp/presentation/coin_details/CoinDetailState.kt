package com.vholodynskyi.cryptocurrencyapp.presentation.coin_details

import com.vholodynskyi.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
