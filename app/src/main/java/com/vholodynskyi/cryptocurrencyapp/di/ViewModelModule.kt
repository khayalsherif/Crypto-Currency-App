package com.vholodynskyi.cryptocurrencyapp.di

import com.vholodynskyi.cryptocurrencyapp.domain.repository.CoinRepository
import com.vholodynskyi.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import com.vholodynskyi.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideCoinUseCase(
        repository: dagger.Lazy<CoinRepository>
    ): GetCoinUseCase = GetCoinUseCase(repository)

    @Provides
    fun provideCoinsUseCase(
        repository: dagger.Lazy<CoinRepository>
    ): GetCoinsUseCase = GetCoinsUseCase(repository)
}