//package com.aghogho.cryptoappcleancode.presentation.coin_detail
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.ExperimentalLayoutApi
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Divider
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.aghogho.cryptoappcleancode.presentation.coin_detail.components.CoinTag
//import com.aghogho.cryptoappcleancode.presentation.coin_detail.components.TeamListItem
//import com.google.accompanist.flowlayout.FlowRow
//
//@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
//@Composable
//fun CoinDetailScreen(
//    viewModel: CoinDetailViewModel = hiltViewModel(),
//    navController: NavController
//) {
//    val state = viewModel.state.value
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        state.coin.let {
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = PaddingValues(20.dp)
//            ) {
//                item {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(20.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        if (it != null) {
//                            Text(
//                                text = "${it.rank}. ${it.name} (${it.symbol})",
//                                style = MaterialTheme.typography.bodyMedium,
//                                modifier = Modifier.weight(8f)
//                            )
//                        }
//                        if (it != null) {
//                            Text(
//                                text = if (it.isActive) "active" else "inactive",
//                                color = if (it.isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
//                                fontStyle = FontStyle.Italic,
//                                textAlign = TextAlign.End,
//                                modifier = Modifier
//                                    .align(Alignment.CenterVertically)
//                                    .weight(2f)
//                            )
//                        }
//                    }
//                    Spacer(modifier = Modifier.height(15.dp))
//                    if (it != null) {
//                        Text(
//                            text = it.description,
//                            style = MaterialTheme.typography.bodyMedium
//                        )
//                    }
//                    Spacer(modifier = Modifier.height(15.dp))
//                    Text(
//                        text = "Tags",
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                    Spacer(modifier = Modifier.height(15.dp))
//                    FlowRow(
//                        mainAxisSpacing = 10.dp,
//                        crossAxisSpacing = 10.dp,
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        it?.tags?.forEach { tag ->
//                            CoinTag(tag = tag)
//                        }
//                    }
//                    Spacer(modifier = Modifier.height(15.dp))
//                    Text(
//                        text = "Team members",
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                    Spacer(modifier = Modifier.height(15.dp))
//                }
//                items(it?.team ?: emptyList()) { teamMember ->
//                    TeamListItem(
//                        teamMember = teamMember,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(10.dp)
//                    )
//                    Divider()
//                }
//            }
//
//        }
//        if (state.error.isNotBlank()) {
//            Text(
//                text = state.error,
//                color = MaterialTheme.colorScheme.error,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .align(Alignment.Center)
//            )
//        }
//        if (state.isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//    }
//}

package com.aghogho.cryptoappcleancode.presentation.coin_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aghogho.cryptoappcleancode.presentation.coin_detail.components.CoinTag
import com.aghogho.cryptoappcleancode.presentation.coin_detail.components.TeamListItem
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text(
                        text = "${state.coin?.name} Detail",
                        modifier = Modifier.padding(start = (1).dp)
                    )
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            state.coin?.let { coin ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(8f)
                            )
                            Text(
                                text = if (coin.isActive) "active" else "inactive",
                                color = if (coin.isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .weight(2f)
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = coin.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Tags",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            coin.tags.forEach { tag ->
                                CoinTag(tag = tag)
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Team members",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    items(coin.team) { teamMember ->
                        TeamListItem(
                            teamMember = teamMember,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        Divider()
                    }
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
