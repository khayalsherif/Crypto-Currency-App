package com.vholodynskyi.cryptocurrencyapp.di

import com.vholodynskyi.cryptocurrencyapp.common.Constants
import com.vholodynskyi.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.vholodynskyi.cryptocurrencyapp.domain.repository.CoinRepository
import com.vholodynskyi.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import com.vholodynskyi.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Scope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

}