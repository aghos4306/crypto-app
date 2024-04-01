package com.aghogho.cryptoappcleancode.presentation.coin_detail

import com.aghogho.cryptoappcleancode.domain.model.Coin
import com.aghogho.cryptoappcleancode.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
