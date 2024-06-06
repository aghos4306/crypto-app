package com.aghogho.cryptoappcleancode.domain.usecases.get_coin

import com.aghogho.cryptoappcleancode.common.Resource
import com.aghogho.cryptoappcleancode.data.remote.dto.CoinDetailDto
import com.aghogho.cryptoappcleancode.data.remote.dto.Links
import com.aghogho.cryptoappcleancode.data.remote.dto.LinksExtended
import com.aghogho.cryptoappcleancode.data.remote.dto.Stats
import com.aghogho.cryptoappcleancode.data.remote.dto.Tag
import com.aghogho.cryptoappcleancode.data.remote.dto.TeamMember
import com.aghogho.cryptoappcleancode.data.remote.dto.Whitepaper
import com.aghogho.cryptoappcleancode.data.remote.dto.toCoinDetail
import com.aghogho.cryptoappcleancode.domain.repository.CoinRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException

@OptIn(ExperimentalCoroutinesApi::class)
class GetCoinUseCaseTest {

    private lateinit var coinRepository: CoinRepository
    private lateinit var getCoinUseCase: GetCoinUseCase
    private val coinId = "coin1"

    private val coinDetailDto = CoinDetailDto(
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

    private val coinDetail = coinDetailDto.toCoinDetail()

    @Before
    fun setUp() {
        coinRepository = mock()
        getCoinUseCase = GetCoinUseCase(coinRepository)
    }
    @Test
    fun `invoke emits Loading and then Success when repository returns data`() = runTest {
        whenever(coinRepository.getCoinById(coinId)).thenReturn(coinDetailDto)

        val results = getCoinUseCase(coinId).toList()

        assert(results[0] is Resource.Loading)
        assert(results[1] is Resource.Success && (results[1] as Resource.Success).data == coinDetail)
    }

    @Test
    fun `invoke emits Loading and then Error when HttpException is thrown`() = runTest {
        val httpException = mock<HttpException>()
        whenever(coinRepository.getCoinById(coinId)).thenThrow(httpException)
        whenever(httpException.localizedMessage).thenReturn("An unexpected error occurred")

        val results = getCoinUseCase(coinId).toList()

        assert(results[0] is Resource.Loading)
        assert(results[1] is Resource.Error && (results[1] as Resource.Error).message == "An unexpected error occurred")
    }

    @Test
    fun `invoke emits Loading and then Error when IOException is thrown`() = runTest {
        whenever(coinRepository.getCoinById(coinId)).thenAnswer { throw IOException("No internet connection") }

        val results = getCoinUseCase(coinId).toList()

        assert(results[0] is Resource.Loading)
        assert(results[1] is Resource.Error && (results[1] as Resource.Error).message == "Cannot fetch data, please check internet connection")
    }

}
