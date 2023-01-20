package com.vholodynskyi.cryptocurrencyapp.domain.repository

import com.vholodynskyi.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.vholodynskyi.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}