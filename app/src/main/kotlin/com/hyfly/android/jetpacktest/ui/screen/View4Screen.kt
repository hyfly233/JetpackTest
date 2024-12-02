package com.example.test.ui.screen

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.icon.OwnIcons
import com.example.test.util.QrCodeAnalyzer
import com.hyfly.android.jetpacktest.ui.component.TestIconToggleButton
import com.hyfly.android.jetpacktest.ui.theme.JetpackTestTheme


typealias AndroidSize = android.util.Size

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun View4Screen(navController: NavHostController) {
    JetpackTestTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            "扫描二维码", maxLines = 1, overflow = TextOverflow.Ellipsis
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
            Content(
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun View4ScreenPreview() {
    val navController = rememberNavController()
    JetpackTestTheme {
        View4Screen(navController = navController)
    }
}

@Composable
private fun Content(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {

        var text by remember { mutableStateOf("") }
        val lifeCycleOwner = LocalLifecycleOwner.current

        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = { context ->
                // 为相机预览创建一个预览视图
                val previewView = PreviewView(context)
                    .apply {
                        implementationMode = PreviewView.ImplementationMode.PERFORMANCE
                    }

                // 创建一个预览实例
                val preview = androidx.camera.core.Preview.Builder()
                    .build()
                    .also {
                        it.surfaceProvider = previewView.surfaceProvider
                    }

                // 选择后置摄像头
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                // 创建一个图像分析实例, 并设置分析器
                val imageAnalysis = ImageAnalysis.Builder().build()
                imageAnalysis.setAnalyzer(
                    ContextCompat.getMainExecutor(context),
                    QrCodeAnalyzer { code ->
                        text = code
                    })

                ProcessCameraProvider.getInstance(context)
                    .get()
                    .bindToLifecycle(
                        lifeCycleOwner, cameraSelector, preview, imageAnalysis
                    )

                previewView
            }
        )

        val widthInPx: Float
        val heightInPx: Float
        val radiusInPx: Float
        with(LocalDensity.current) {
            widthInPx = 250.dp.toPx()
            heightInPx = 250.dp.toPx()
            radiusInPx = 16.dp.toPx()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = .5f)),
            contentAlignment = Alignment.Center,
        ) {
            Canvas(
                modifier = Modifier
                    .size(250.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(16.dp))
                    .onGloballyPositioned {
                        text = it
                            .boundsInRoot()
                            .toString()
                    }
            ) {
                val offset = Offset(
                    x = (size.width - widthInPx) / 2,
                    y = (size.height - heightInPx) / 2,
                )
                val cutoutRect = Rect(offset, Size(widthInPx, heightInPx))
                // Source
                drawRoundRect(
                    topLeft = cutoutRect.topLeft,
                    size = cutoutRect.size,
                    cornerRadius = CornerRadius(radiusInPx, radiusInPx),
                    color = Color.Transparent,
                    blendMode = BlendMode.Clear
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (text.isNotEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .background(Color.White.copy(alpha = .6f), RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = text,
                )
            } else {
                Spacer(
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .weight(2f),
                    contentAlignment = Alignment.Center,
                ) {
                    TestIconToggleButton(
                        modifier = Modifier
                            .background(Color.White.copy(alpha = .6f), RoundedCornerShape(16.dp)),
                        checked = false,
                        onCheckedChange = { },
                        icon = {
                            Icon(
                                imageVector = OwnIcons.Close,
                                contentDescription = null,
                            )
                        },
                        checkedIcon = {
                            Icon(
                                imageVector = OwnIcons.Close,
                                contentDescription = null,
                            )
                        },
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .weight(2f),
                    contentAlignment = Alignment.Center,
                ) {
                    TestIconToggleButton(
                        modifier = Modifier
                            .background(Color.White.copy(alpha = .6f), RoundedCornerShape(16.dp)),
                        checked = false,
                        onCheckedChange = { },
                        icon = {
                            Icon(
                                imageVector = OwnIcons.Check,
                                contentDescription = null,
                            )
                        },
                        checkedIcon = {
                            Icon(
                                imageVector = OwnIcons.Check,
                                contentDescription = null,
                            )
                        },
                    )
                }
            }
        }
    }
}