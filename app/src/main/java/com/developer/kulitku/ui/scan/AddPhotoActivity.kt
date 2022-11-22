package com.developer.kulitku.ui.scan

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.developer.kulitku.databinding.ActivityAddPhotoBinding
import com.developer.kulitku.ui.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import com.google.common.util.concurrent.ListenableFuture
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class AddPhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPhotoBinding
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var cameraSelector: CameraSelector
    private lateinit var imgCaptureExecutor: ExecutorService
    private lateinit var fileName: String
    private lateinit var file: File
    private lateinit var imageURI: String
    private lateinit var outputFileOptions: ImageCapture.OutputFileOptions
    private var imageCapture: ImageCapture? = null
    private val cameraPermissionResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { permissionGranted ->
            if (permissionGranted) {
                startCamera()
            } else {
                Snackbar.make(
                    binding.root,
                    "The camera permission is required",
                    Snackbar.LENGTH_INDEFINITE
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        imgCaptureExecutor = Executors.newSingleThreadExecutor()

        cameraPermissionResult.launch(android.Manifest.permission.CAMERA)

        fileName = "JPEG_${System.currentTimeMillis()}"
        file = File(externalMediaDirs[0], fileName)
        imageURI = file.toUri().toString()

        // Save the image in the above file
        outputFileOptions = ImageCapture.OutputFileOptions.Builder(file).build()
        initView()
    }

    private fun initView() {
        binding.buttonOpenGallery.setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java))
        }
        binding.buttonCaptureImage.setOnClickListener {
            takePhoto()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                animateFlash()
            }
        }

        binding.buttonSwitchCamera.setOnClickListener {
            //change the cameraSelector
            cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                CameraSelector.DEFAULT_FRONT_CAMERA
            } else {
                CameraSelector.DEFAULT_BACK_CAMERA
            }
            // restart the camera
            startCamera()
        }

        binding.buttonSend.setOnClickListener {
            val intent = Intent(this, ScanResultActivity::class.java)
            intent.putExtra(ScanResultActivity.EXTRA_IMG, imageURI)
            startActivity(intent)
        }

        binding.buttonBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun startCamera() {
        // listening for data from the camera
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            imageCapture = ImageCapture.Builder().build()
            // connecting a preview use case to the preview in the xml file.
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.preview.surfaceProvider)
            }
            try {
                // clear all the previous use cases first.
                cameraProvider.unbindAll()
                // binding the lifecycle of the camera to the lifecycle of the application.
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (e: Exception) {
                Log.d(TAG, "Use case binding failed")
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        imageCapture?.takePicture(
            outputFileOptions,
            imgCaptureExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Log.i(TAG, "The image has been saved in ${file.toUri()}")

                    val handler = Handler(Looper.getMainLooper())
                    handler.post {
                        binding.imageviewPreview.visibility = View.VISIBLE
                        binding.preview.visibility = View.GONE

                        Glide.with(this@AddPhotoActivity)
                            .load(imageURI)
                            .into(binding.imageviewPreview)

                        binding.buttonSend.visibility = View.VISIBLE
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        binding.root.context,
                        "Error taking photo",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d(TAG, "Error taking photo:$exception")
                }

            })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun animateFlash() {
        binding.root.postDelayed({
            binding.root.foreground = ColorDrawable(Color.WHITE)
            binding.root.postDelayed({
                binding.root.foreground = null
            }, 50)
        }, 100)
    }

    companion object {
        val TAG = "AddPhotoActivity"
    }
}