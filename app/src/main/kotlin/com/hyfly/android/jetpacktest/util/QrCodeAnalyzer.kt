package com.example.test.util

import android.graphics.ImageFormat
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.nio.ByteBuffer

class QrCodeAnalyzer(
    private val onQrCodeScanned: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val supportedImageFormats = listOf(
        ImageFormat.YUV_420_888, ImageFormat.YUV_422_888, ImageFormat.YUV_444_888
    )

    override fun analyze(image: ImageProxy) {
        if (image.format in supportedImageFormats) {
            val bytes = image.planes.first().buffer.toByteArray()

            val source = PlanarYUVLuminanceSource(
                bytes, image.width, image.height, 0, 0, image.width, image.height, false
            )

            val binaryBitmap = BinaryBitmap(HybridBinarizer(source))

            val multiFormatReader = MultiFormatReader()
            multiFormatReader.setHints(
                mapOf(
                    DecodeHintType.POSSIBLE_FORMATS to arrayListOf(BarcodeFormat.QR_CODE)
                )
            )

            try {
                val result = multiFormatReader.decodeWithState(binaryBitmap)
                onQrCodeScanned(result.text)
            } catch (e: Exception) {
                // QR code not found
                e.printStackTrace()
            } finally {
                image.close()
            }
        } else {
            image.close()
        }
    }
}

private fun ByteBuffer.toByteArray(): ByteArray {
    return ByteArray(remaining()).also {
        get(it)
    }
}