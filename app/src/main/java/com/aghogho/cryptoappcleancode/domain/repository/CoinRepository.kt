package com.aghogho.cryptoappcleancode.domain.repository

import com.aghogho.cryptoappcleancode.data.remote.dto.CoinDetailDto
import com.aghogho.cryptoappcleancode.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(id: String): CoinDetailDto
}
