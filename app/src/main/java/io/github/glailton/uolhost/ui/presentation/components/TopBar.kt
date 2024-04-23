package io.github.glailton.uolhost.ui.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.glailton.uolhost.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    showSearch: Boolean = false,
    showFilter: Boolean = false,
    showRefresh: Boolean = false,
    showBackIcon: Boolean = false,
    onSearch: () -> Unit = {},
    onFilter: () -> Unit = {},
    onRefresh: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        navigationIcon = {
            if (showBackIcon) {
                IconButton(onClick = { onBack.invoke() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                }
            }
        },
        actions = {
            if (showSearch) {
                IconButton(onClick = { onSearch.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon)
                    )
                }
            }

            if (showFilter) {
                IconButton(onClick = { onFilter.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = stringResource(R.string.filter_icon)
                    )
                }
            }

            if (showRefresh){
                IconButton(onClick = { onRefresh.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.refresh_icon)
                    )
                }
            }
        }
    )
}