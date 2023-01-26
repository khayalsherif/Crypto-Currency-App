package com.vholodynskyi.cryptocurrencyapp.domain.use_case.get_coins

import com.vholodynskyi.cryptocurrencyapp.common.Resource
import com.vholodynskyi.cryptocurrencyapp.data.remote.dto.toCoin
import com.vholodynskyi.cryptocurrencyapp.domain.model.Coin
import com.vholodynskyi.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase(
    private val repository: dagger.Lazy<CoinRepository>
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.get().getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "Couldn't reach server"))
        }
    }
}