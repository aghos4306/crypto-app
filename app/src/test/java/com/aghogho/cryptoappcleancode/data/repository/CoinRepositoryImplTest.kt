package com.aghogho.cryptoappcleancode.data.repository

import com.aghogho.cryptoappcleancode.data.remote.CoinPaprikaApi
import com.aghogho.cryptoappcleancode.data.remote.dto.CoinDetailDto
import com.aghogho.cryptoappcleancode.data.remote.dto.CoinDto
import com.aghogho.cryptoappcleancode.data.remote.dto.Links
import com.aghogho.cryptoappcleancode.data.remote.dto.LinksExtended
import com.aghogho.cryptoappcleancode.data.remote.dto.Stats
import com.aghogho.cryptoappcleancode.data.remote.dto.Tag
import com.aghogho.cryptoappcleancode.data.remote.dto.TeamMember
import com.aghogho.cryptoappcleancode.data.remote.dto.Whitepaper
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class CoinRepositoryImplTest {

    private val firstCoin = CoinDto(
        id = "1", isActive = false, name = "Bitcoin",
        rank = 1, symbol = "BTC", isNew = false, type = "CryptoCoin"
    )
    private val secondCoin = CoinDto(
        id = "2", isActive = false, name = "Etherium",
        rank = 2, symbol = "ETH", isNew = false, type = "CryptoCoin"
    )
    private val thirdCoin = CoinDto(
        id = "3", isActive = false, name = "DodgeCoin",
        rank = 6, symbol = "DC", isNew = false, type = "CryptoCoin"
    )

    private val coinDetail = CoinDetailDto(
        description = "hottest coin", developmentStatus = "completed", firstDataAt = "1st April 2015",
        hardwareWallet = false, hashAlgorithm = "algorithm", id = "1", isNew = false, isActive = false,
        lastDataAt = "2nd March 2019",
        links = Links(explorer = listOf("explorer"), facebook = listOf("facebook"), reddit = listOf("reddit"), source_code = listOf("source_code"), website = listOf("www.coinsite.com"), youtube = listOf("youtube.com")),
        linksExtended = listOf(
            LinksExtended(
                stats = Stats(1, 6, 200, 4000),
                type = "CryptoCoin",
                url = "www.coinsite.com"
            )
        ),
        logo = "www.coinlogo.png",
        message = "coin",
        name = "Bitcoin",
        openSource = true,
        orgStructure = "Flat structure",
        proofType = "proof of concept",
        rank = 1,
        startedAt = "21st June 2015",
        symbol = "BTC",
        tags = listOf(Tag(
            coinCounter = 1, icoCounter = 2, id = "1", name = "BTC"
        )),
        team = listOf(TeamMember(
            id = "1", name = "BTC", position = "first"
        )),
        type = "Coin",
        whitepaper = Whitepaper(
            link = "www.whitepaperbtc.com",
            thumbnail = "white"
        )
    )

    private val listOfCoins = listOf<CoinDto>(firstCoin, secondCoin, thirdCoin)

    @Mock
    lateinit var coinPaprikaApi: CoinPaprikaApi
    private lateinit var coinRepositoryImpl: CoinRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        coinRepositoryImpl = CoinRepositoryImpl(coinPaprikaApi)
    }

    @Test
    fun `getCoinById() should return detail properties of specific coin`() = runBlocking {
        val expectedResult = coinDetail
        whenever(coinRepositoryImpl.getCoinById("1")).thenReturn(coinDetail)
        val result = coinRepositoryImpl.getCoinById("1")
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `getAllCoins() should return a list of all coins from api`() = runBlocking {
        val expectedResult = listOfCoins
        whenever(coinRepositoryImpl.getCoins()).thenReturn(listOfCoins)
        val result = coinRepositoryImpl.getCoins()
        Assert.assertEquals(expectedResult, result)
    }

}
