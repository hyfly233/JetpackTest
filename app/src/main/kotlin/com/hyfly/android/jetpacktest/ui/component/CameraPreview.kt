package com.hyfly.android.jetpacktest.ui.component

import android.graphics.Color
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.test.util.QrCodeAnalyzer

@Composable
fun CameraPreview(onQrCodeScanned: (String) -> Unit) {

    val lifeCycleOwner = LocalLifecycleOwner.current

    AndroidView(
        factory = { context ->
            // 为相机预览创建一个预览视图
            val previewView = PreviewView(context)
                .apply {
                    setBackgroundColor(Color.GREEN)
                    layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    implementationMode = PreviewView.ImplementationMode.PERFORMANCE
                }

            // 创建一个预览实例
            val preview = Preview.Builder()
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
                    onQrCodeScanned(code)
                })

            ProcessCameraProvider.getInstance(context)
                .get()
                .bindToLifecycle(
                    lifeCycleOwner, cameraSelector, preview, imageAnalysis
                )

            previewView
        })
}

