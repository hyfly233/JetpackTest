package com.hyfly.android.jetpacktest.ui.component

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import kotlinx.coroutines.delay

@Composable
fun PermissionRequestDialog(
    permission: String,
    onResult: (Boolean) -> Unit,
) {
    val context = LocalContext.current

    if (isPermissionGranted(context, permission)) {
        onResult(true)
        return
    }

    var launchEffectKey by remember { mutableStateOf(false) }

    var requestPermissionDelay by remember { mutableLongStateOf(0L) }

    val managedActivityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->

            onResult(isGranted)

            if (!isGranted) {
                requestPermissionDelay = 1000
                launchEffectKey = !launchEffectKey
            }

        }
    )

    LaunchedEffect(launchEffectKey) {
        delay(requestPermissionDelay)
        managedActivityResultLauncher.launch(permission)
    }
}

fun isPermissionGranted(context: Context, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

