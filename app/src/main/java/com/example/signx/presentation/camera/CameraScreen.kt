//package com.example.signx.presentation.camera
//
//import android.annotation.SuppressLint
//import android.util.Log
//import androidx.camera.core.*
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.content.ContextCompat
//import com.example.signx.utils.BitmapUtils
//import com.google.accompanist.permissions.*
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors
//
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun CameraScreen(@SuppressLint("RememberReturnType") viewModel: CameraViewModel = remember { CameraViewModel() }) {
//
//    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
//
//    LaunchedEffect(Unit) {
//        if (!cameraPermissionState.status.isGranted) {
//            cameraPermissionState.launchPermissionRequest()
//        }
//    }
//
//    if (cameraPermissionState.status.isGranted) {
//        CameraPreview()
//    } else {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text("Camera permission is required.")
//            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
//                Text("Grant Permission")
//            }
//        }
//    }
//}
//
//@Composable
//fun CameraPreview() {
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    var previewUseCase by remember { mutableStateOf<Preview?>(null) }
//    var analyzerUseCase by remember { mutableStateOf<ImageAnalysis?>(null) }
//
//    // MediaPipe Detector instance (remembered)
//    val handDetector = remember { HandLandmarkDetector(context) }
//
//    // Single thread executor for CameraX analyzer
//    val cameraExecutor: ExecutorService = remember { Executors.newSingleThreadExecutor() }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            // Close MediaPipe when Composable is removed
//            handDetector.close()
//            cameraExecutor.shutdown()
//        }
//    }
//
//    AndroidView(
//        factory = { ctx ->
//            val previewView = PreviewView(ctx)
//
//            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
//            cameraProviderFuture.addListener({
//                val cameraProvider = cameraProviderFuture.get()
//
//                // Setup Preview
//                previewUseCase = Preview.Builder().build().also {
//                    it.setSurfaceProvider(previewView.surfaceProvider)
//                }
//
//                // Setup Analyzer
//                analyzerUseCase = ImageAnalysis.Builder()
//                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//                    .build()
//                    .also { imageAnalysis ->
//                        imageAnalysis.setAnalyzer(cameraExecutor) { imageProxy ->
//                            processImageProxy(imageProxy, handDetector)
//                        }
//                    }
//
//                // Select back camera
//                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//                try {
//                    cameraProvider.unbindAll()
//                    cameraProvider.bindToLifecycle(
//                        lifecycleOwner,
//                        cameraSelector,
//                        previewUseCase,
//                        analyzerUseCase
//                    )
//                } catch (exc: Exception) {
//                    Log.e("CameraScreen", "Use case binding failed", exc)
//                }
//
//            }, ContextCompat.getMainExecutor(ctx))
//
//            previewView
//        },
//        modifier = Modifier.fillMaxSize()
//    )
//}
//
///**
// * Convert ImageProxy to Bitmap ➔ Send to MediaPipe ➔ Log detection result
// */
//private fun processImageProxy(imageProxy: ImageProxy, detector: HandLandmarkDetector) {
//    val bitmap = BitmapUtils.imageProxyToBitmap(imageProxy)
//    detector.detectHands(bitmap)
//    imageProxy.close()
//}
