//package com.example.signx.ui.camera
//
//import android.graphics.Bitmap
//import android.graphics.ImageFormat
//import android.graphics.YuvImage
//import android.media.Image
//import android.util.Log
//import androidx.annotation.OptIn
//import androidx.camera.core.ExperimentalGetImage
//import androidx.camera.core.ImageAnalysis
//import androidx.camera.core.ImageProxy
//import com.example.signx.presentation.camera.HandLandmarkDetector
//import java.io.ByteArrayOutputStream
//
//class CameraAnalyzer(
//    private val handDetector: HandLandmarkDetector,
//) : ImageAnalysis.Analyzer {
//
//    @OptIn(ExperimentalGetImage::class)
//    override fun analyze(imageProxy: ImageProxy) {
//        val mediaImage = imageProxy.image
//
//        if (mediaImage != null) {
//            val bitmap = mediaImage.toBitmap()
//
//            if (bitmap != null) {
//                // Detect hands using MediaPipe
//                val result = handDetector.detectHands(bitmap)
//
//                result?.let {
//                    Log.d("CameraAnalyzer", "Detected ${it.landmarks().size} hands")
//                    // You can update UI with the result here if needed
//                }
//            } else {
//                Log.e("CameraAnalyzer", "Received null Bitmap")
//            }
//        }
//
//        imageProxy.close()
//    }
//
//    // Convert YUV Image to Bitmap
//    private fun Image.toBitmap(): Bitmap? {
//        try {
//            val yBuffer = planes[0].buffer
//            val uBuffer = planes[1].buffer
//            val vBuffer = planes[2].buffer
//
//            val ySize = yBuffer.remaining()
//            val uSize = uBuffer.remaining()
//            val vSize = vBuffer.remaining()
//
//            val nv21 = ByteArray(ySize + uSize + vSize)
//            yBuffer.get(nv21, 0, ySize)
//            vBuffer.get(nv21, ySize, vSize)
//            uBuffer.get(nv21, ySize + vSize, uSize)
//
//            val yuvImage = YuvImage(nv21, ImageFormat.NV21, width, height, null)
//            val out = ByteArrayOutputStream()
//            yuvImage.compressToJpeg(android.graphics.Rect(0, 0, width, height), 100, out)
//            val imageBytes = out.toByteArray()
//
//            return android.graphics.BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//        } catch (e: Exception) {
//            Log.e("CameraAnalyzer", "Error converting image to bitmap", e)
//            return null
//        }
//    }
//}
