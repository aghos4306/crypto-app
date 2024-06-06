package com.aghogho.cryptoappcleancode.domain.usecases.get_coin

import com.aghogho.cryptoappcleancode.common.Resource
import com.aghogho.cryptoappcleancode.data.remote.dto.toCoinDetail
import com.aghogho.cryptoappcleancode.domain.model.CoinDetail
import com.aghogho.cryptoappcleancode.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try{
            emit(Resource.Loading())
            val coin = coinRepository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Cannot fetch data, please check internet connection"))
        }
    }
}
