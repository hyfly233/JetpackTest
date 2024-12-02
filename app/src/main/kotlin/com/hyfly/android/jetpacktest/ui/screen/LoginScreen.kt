package com.example.test.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hyfly.android.jetpacktest.navigation.HomeSections
import com.hyfly.android.jetpacktest.ui.component.SetUrlDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavHostController) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var openAlertDialog = remember { mutableStateOf(false) }
    var text02 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // 使用 Spacer 占据中间的剩余空间
        Spacer(modifier = Modifier.weight(1f))

        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = { username = it },
            label = { Text("用户名") })

        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text("密码") })

        // 使用 Spacer 占据中间的剩余空间
        Spacer(modifier = Modifier.weight(0.2f))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(HomeSections.HOME.route) }) {
            Text("登录")
        }

        // 使用 Spacer 占据中间的剩余空间
        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            modifier = Modifier.padding(bottom = 20.dp),
            onClick = {

                openAlertDialog.value = true
            }) {
            Text("设置服务器")
        }

        if (openAlertDialog.value) {
            SetUrlDialog(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = { openAlertDialog.value = false }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    Login(navController)
}