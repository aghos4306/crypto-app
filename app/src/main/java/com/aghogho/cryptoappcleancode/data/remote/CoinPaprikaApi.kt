package com.aghogho.cryptoappcleancode.data.remote

import com.aghogho.cryptoappcleancode.data.remote.dto.CoinDetailDto
import com.aghogho.cryptoappcleancode.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): CoinDetailDto
}
