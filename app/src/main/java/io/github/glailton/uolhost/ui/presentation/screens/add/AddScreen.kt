package io.github.glailton.uolhost.ui.presentation.screens.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.glailton.uolhost.R
import io.github.glailton.uolhost.core.domain.enums.GroupType
import io.github.glailton.uolhost.ui.presentation.components.CustomAlertDialog
import io.github.glailton.uolhost.ui.presentation.components.GradientButton
import io.github.glailton.uolhost.ui.presentation.components.LoadingProgressBar
import io.github.glailton.uolhost.ui.presentation.components.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AddScreen(
    viewModel: AddViewModel,
    onBackClicked: () -> Unit,
    onDismiss: () -> Boolean
) {
    val state = viewModel.state.collectAsState().value
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBar(
                showBackIcon = true,
                onBack = { onBackClicked() }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (state.isLoading) {
                    LoadingProgressBar()
                } else {
                    AddContent(viewModel, state, onDismiss, snackBarHostState, scope)
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AddContent(
    viewModel: AddViewModel,
    state: AddState,
    onDismiss: () -> Boolean,
    snackBarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    val gradientColor = listOf(Color(0xFF6687FF), Color(0xFF61FAFF))

    if (state.showDialogError) {
        CustomAlertDialog(
            title = stringResource(id = R.string.error_dialog_title),
            text = stringResource(id = R.string.error_dialog_text),
            onConfirmButton = { viewModel.createPlayer() },
            onDismiss = { onDismiss.invoke() }
        )
    }

    if (state.showSnackBar) {
        val context = LocalContext.current
        scope.launch {
            val result = snackBarHostState.showSnackbar(
                message = context.getString(R.string.create_player_message),
                withDismissAction = true,
                duration = SnackbarDuration.Short
            )
            when(result) {
                SnackbarResult.Dismissed -> {
                    viewModel.updateIsSuccess(true)
                }

                SnackbarResult.ActionPerformed -> TODO()
            }
        }
    }

    Text(
        modifier = Modifier
            .padding(all = 8.dp),
        text = stringResource(id = R.string.add_player),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        value = state.name,
        onValueChange = { viewModel.updateName(it) },
        label = { Text(text = stringResource(R.string.name)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        isError = false
    )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        value = state.email,
        onValueChange = { viewModel.updateEmail(it) },
        label = { Text(text = stringResource(R.string.email)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        isError = false
    )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        value = state.phone,
        onValueChange = { viewModel.updatePhoneNumber(it) },
        label = { Text(text = stringResource(R.string.phone_number)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        isError = false
    )

    ExposedDropdown(viewModel, state)

    GradientButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        gradientColors = gradientColor,
        cornerRadius = 16.dp,
        nameButton = stringResource(id = R.string.create),
        roundedCornerShape = RoundedCornerShape(
            topStart = 30.dp,
            bottomEnd = 30.dp
        ),
        onClick = { viewModel.createPlayer() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdown(viewModel: AddViewModel, state: AddState) {
    val teams = GroupType.getList()
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
                .menuAnchor(),
            value = state.selectedText.name,
            onValueChange = { viewModel.updateGroupType(it) },
            readOnly = true,
            label = { Text(text = stringResource(R.string.team)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            teams.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = item)) },
                    onClick = {
                        viewModel.updateSelectedText(GroupType.fromValue(item))
                        expanded = false
                    }
                )
            }
        }
    }
}
