package com.developer.kulitku.ui.scan

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.databinding.ActivityGalleryBinding
import id.zelory.compressor.Compressor
import kotlinx.coroutines.launch
import java.io.*

class GalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryBinding
    private lateinit var viewModel: ScanViewModel
    val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel = ScanViewModel()

        binding.ivPhotoResult.setOnClickListener {
            openGalleryForImage()
        }

        viewModel.labelState.observe(this) {
            when (it) {
                is ResultState.Loading -> {
                }
                is ResultState.Success -> {
                    val labelScan = it.value.result?.jsonMemberClass.toString()

                    val modalBottomSheet = ModalBottomSheet()
                    val bundle = Bundle()

                    modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
                    bundle.putString("message", labelScan)
                    modalBottomSheet.arguments = bundle
                    Log.d("EKO", labelScan)
                }
                is ResultState.Failure -> {
                    Toast.makeText(
                        this@GalleryActivity,
                        it.throwable.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            binding.ivPhotoResult.setImageURI(data?.data)
            val image = fileFromContentUri(this, data?.data!!)
            lifecycleScope.launch {
                val compressedImageFile = Compressor.compress(this@GalleryActivity, image)
                viewModel.uploadImage(compressedImageFile)
            }
        }
    }

    private fun fileFromContentUri(context: Context, contentUri: Uri): File {
        // Preparing Temp file name
        val fileExtension = getFileExtension(context, contentUri)
        val fileName = "temp_file" + if (fileExtension != null) ".$fileExtension" else ""

        // Creating Temp file
        val tempFile = File(context.cacheDir, fileName)
        tempFile.createNewFile()

        try {
            val oStream = FileOutputStream(tempFile)
            val inputStream = context.contentResolver.openInputStream(contentUri)

            inputStream?.let {
                copy(inputStream, oStream)
            }

            oStream.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return tempFile
    }

    private fun getFileExtension(context: Context, uri: Uri): String? {
        val fileType: String? = context.contentResolver.getType(uri)
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(fileType)
    }

    @Throws(IOException::class)
    private fun copy(source: InputStream, target: OutputStream) {
        val buf = ByteArray(8192)
        var length: Int
        while (source.read(buf).also { length = it } > 0) {
            target.write(buf, 0, length)
        }
    }
}