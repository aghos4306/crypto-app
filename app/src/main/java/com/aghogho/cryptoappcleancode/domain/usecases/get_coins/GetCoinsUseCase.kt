package com.aghogho.cryptoappcleancode.domain.usecases.get_coins

import com.aghogho.cryptoappcleancode.common.Resource
import com.aghogho.cryptoappcleancode.data.remote.dto.toCoin
import com.aghogho.cryptoappcleancode.domain.model.Coin
import com.aghogho.cryptoappcleancode.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try{
            emit(Resource.Loading())
            val coins = coinRepository.getCoins().map {
                it.toCoin()
            }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occurred")) //This will attach the msg with the error code and if that does not exist we instead display the string message
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Cannot fetch data, please check internet connection"))
        }
    }
}
