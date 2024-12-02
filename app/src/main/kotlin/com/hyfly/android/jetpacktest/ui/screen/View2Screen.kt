package com.hyfly.android.jetpacktest.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.hyfly.android.jetpacktest.ui.theme.JetpackTestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun View2Screen(navController: NavHostController) {

    var imageUriList = remember { mutableStateOf<List<Uri>>(emptyList()) }

    val topPadding = 8.dp

    // 打开单个图库
    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents(),
            onResult = { uriList ->
                imageUriList.value = uriList
            })

    JetpackTestTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            "View2", maxLines = 1, overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "返回上一页"
                            )
                        }
                    },
                )
            },
        ) { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                Column(
                    modifier = Modifier
                        .padding(topPadding)
                        .fillMaxSize(),
                    horizontalAlignment = CenterHorizontally,
                ) {
                    var text01 by remember { mutableStateOf("") }

                    OutlinedTextField(value = text01,
                        onValueChange = { text01 = it },
                        label = { Text("Label01") })

                    var text02 by remember { mutableStateOf("") }

                    OutlinedTextField(value = text02,
                        onValueChange = { text02 = it },
                        label = { Text("Label02") })


                    Spacer(modifier = Modifier.fillMaxHeight(0.1f))


                    Button(onClick = { galleryLauncher.launch("image/*") }) {
                        Text("选择相册中的当个图片")
                    }

                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.height(100.dp)
                    ) {
                        imageUriList.value.forEach { imageUri ->
                            item {
                                Image(
                                    painter = rememberAsyncImagePainter(model = imageUri),
                                    contentDescription = null,
                                    modifier = Modifier.size(72.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.fillMaxHeight(0.1f))

                    Button(onClick = { }) {
                        Text("点击发起 HTTP 请求")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun View2ScreenPreview() {
    val navController = rememberNavController()
    JetpackTestTheme {
        View2Screen(navController = navController)
    }
}
