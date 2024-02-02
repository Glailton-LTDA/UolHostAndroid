package io.github.glailton.uolhost.ui.presentation.screens.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.ContentAlpha
import androidx.wear.compose.material3.MaterialTheme
import io.github.glailton.uolhost.core.domain.enums.GroupType
import io.github.glailton.uolhost.core.domain.models.Player
import io.github.glailton.uolhost.ui.presentation.components.HomeTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(true) {
        viewModel.getPlayers()
    }

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = { /*TODO*/ }) {

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Rounded.Add, contentDescription = "Add")
            }
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                ListContent(state.players)
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
        ){ player ->
            PlayerItem(player = player)
        }
    }

}

@Composable
fun PlayerItem(player: Player) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.onSurface,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp),
                contentScale = ContentScale.FillBounds,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 8.dp)
            ) {
                Text(
                    text = player.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = player.codiname,
                    color = Color.Black.copy(alpha = ContentAlpha.medium),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPrev() {
    ListContent(players = listOf(
        Player(id = 1, name = "Glailton", codiname = "hulk", email = "glailton@email.com", groupType = GroupType.AVENGERS),
        Player(id = 2, name = "Deisi", codiname = "hulk", email = "glailton@email.com", groupType = GroupType.AVENGERS),
    ))
}

@Preview(showBackground = true,     uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable()
fun HomeScreenDarkPrev() {
    ListContent(players = listOf(
        Player(id = 1, name = "Glailton", codiname = "hulk", email = "glailton@email.com", groupType = GroupType.AVENGERS),
        Player(id = 2, name = "Deisi", codiname = "hulk", email = "glailton@email.com", groupType = GroupType.AVENGERS),
    ))
}