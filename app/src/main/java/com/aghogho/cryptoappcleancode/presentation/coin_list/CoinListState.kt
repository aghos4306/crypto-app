package com.aghogho.cryptoappcleancode.presentation.coin_list

import com.aghogho.cryptoappcleancode.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
