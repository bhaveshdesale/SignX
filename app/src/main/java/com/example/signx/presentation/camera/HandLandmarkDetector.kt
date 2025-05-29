package com.example.signx.presentation.camera

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import com.google.mediapipe.framework.image.BitmapImageBuilder

class HandLandmarkDetector(context: Context) {

    private lateinit var handLandmarker: HandLandmarker

    init {
        try {
            val baseOptions = BaseOptions.builder()
                .setModelAssetPath("models/hand_landmarker.task") // Make sure the model is in the assets folder
                .build()

            val options = HandLandmarker.HandLandmarkerOptions.builder()
                .setBaseOptions(baseOptions)
                .setRunningMode(RunningMode.IMAGE)
                .setNumHands(2)
                .build()

            handLandmarker = HandLandmarker.createFromOptions(context, options)
            Log.d("HandLandmarkDetector", "HandLandmarker initialized successfully.")
        } catch (e: Exception) {
            Log.e("HandLandmarkDetector", "Error initializing HandLandmarker", e)
            e.printStackTrace()
        }
    }

    fun detectHands(bitmap: Bitmap): HandLandmarkerResult? {
        return try {
            // Safety check for empty bitmap
            if (bitmap.width == 0 || bitmap.height == 0) {
                Log.e("MediaPipe", "Empty Bitmap!")
                return null
            }

            // Convert Bitmap to MPImage
            val mpImage = BitmapImageBuilder(bitmap).build()

            val result = handLandmarker.detect(mpImage)
            Log.d("MediaPipe", "Detected ${result.landmarks().size} hands")
            result
        } catch (e: Exception) {
            Log.e("HandLandmarkDetector", "Error detecting hands", e)
            e.printStackTrace()
            null
        }
    }

    fun close() {
        try {
            handLandmarker.close()
            Log.d("HandLandmarkDetector", "HandLandmarker closed.")
        } catch (e: Exception) {
            Log.e("HandLandmarkDetector", "Error closing HandLandmarker", e)
        }
    }
}
