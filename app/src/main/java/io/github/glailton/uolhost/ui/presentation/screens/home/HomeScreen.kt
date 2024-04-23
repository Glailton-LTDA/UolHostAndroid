package io.github.glailton.uolhost.ui.presentation.screens.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.MaterialTheme
import io.github.glailton.uolhost.R
import io.github.glailton.uolhost.core.domain.enums.GroupType
import io.github.glailton.uolhost.core.domain.models.Player
import io.github.glailton.uolhost.ui.presentation.components.EmptyContent
import io.github.glailton.uolhost.ui.presentation.components.LoadingProgressBar
import io.github.glailton.uolhost.ui.presentation.components.NetworkError
import io.github.glailton.uolhost.ui.presentation.components.TopBar
import io.github.glailton.uolhost.ui.theme.UolHostTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onAddClicked: () -> Unit
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.getPlayers()
    }

    Scaffold(
        topBar = {
            TopBar(
                showSearch = true,
                showFilter = true,
                showRefresh = true,
                onSearch = {},
                onFilter = {},
                onRefresh = { viewModel.getPlayers() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddClicked.invoke() },
                modifier = Modifier.alpha(if (state.showNetworkError) 0f else 1f)
            ) {
                Icon(Icons.Rounded.Add, contentDescription = "Add")
            }
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                when {
                    state.isLoading -> LoadingProgressBar()
                    state.showNetworkError -> NetworkError(viewModel::getPlayers)
                    state.players.isEmpty() -> EmptyContent()
                    else -> ListContent(state.players)
                }
            }
        }
    )
}

@Composable
fun ListContent(
    players: List<Player>
) {
    LazyColumn(
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = players,
            key = { player ->
                player.id
            }
        ) { player ->
            PlayerItem(player = player)
        }
    }

}

@Composable
fun PlayerItem(player: Player) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(player.codiname.second),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(all = 8.dp)
            ) {
                Column {
                    Text(
                        text = player.name,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = player.codiname.first,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            AssistChip(
                modifier = Modifier,
                enabled = false,
                label = {
                    Text(
                        text = stringResource(id = player.groupType)
                    )
                },
                onClick = {}
            )
        }

    }
}


@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable()
fun HomeScreenDarkPrev() {
    UolHostTheme {
        PlayerItem(
            player = Player(
                id = 1,
                name = "Glailton",
                codiname = Pair("hulk", R.drawable.homem_de_ferro),
                email = "glailton@email.com",
                groupType = GroupType.AVENGERS.value
            )
        )
    }
}
