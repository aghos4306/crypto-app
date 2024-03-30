package com.aghogho.cryptoappcleancode.data.repository

import com.aghogho.cryptoappcleancode.data.remote.CoinPaprikaApi
import com.aghogho.cryptoappcleancode.data.remote.dto.CoinDetailDto
import com.aghogho.cryptoappcleancode.data.remote.dto.CoinDto
import com.aghogho.cryptoappcleancode.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}
