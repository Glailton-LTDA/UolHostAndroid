package io.github.glailton.uolhost.ui.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import io.github.glailton.uolhost.R

@Composable
fun CustomAlertDialog(
    title: String,
    text: String,
    onConfirmButton: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        text = {
            Text(
                text = text
            )
        },
        title = {
            Text(
                text = title,
                fontSize = 20.sp
            )
        },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmButton()
                }
            ) {
                Text(
                    text = stringResource(R.string.try_again_label)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(
                    text = stringResource(R.string.cancel)
                )
            }
        }
    )
}