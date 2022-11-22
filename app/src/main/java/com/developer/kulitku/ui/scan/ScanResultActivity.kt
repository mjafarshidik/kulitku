package com.developer.kulitku.ui.scan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.databinding.ActivityScanResultBinding
import com.developer.kulitku.ui.home.HomeActivity
import id.zelory.compressor.Compressor
import kotlinx.coroutines.launch
import java.io.File


class ScanResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanResultBinding
    private lateinit var viewModel: ScanViewModel
    private lateinit var labelScan: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val extras = intent.extras
        intent.hasExtra(EXTRA_IMG)
        val imgUri = extras?.getString(EXTRA_IMG)

        viewModel = ScanViewModel()

        val file = File(imgUri?.toUri()?.path)

        lifecycleScope.launch {
            val compressedImageFile = Compressor.compress(this@ScanResultActivity, file)
            viewModel.uploadImage(compressedImageFile)
        }

        Glide.with(this)
            .load(imgUri)
            .transform(RoundedCorners(32))
            .apply(RequestOptions.overrideOf(314, 526))
            .into(binding.ivPhotoResult)

        viewModel.labelState.observe(this) {
            when (it) {
                is ResultState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ResultState.Success -> {
                    labelScan = it.value.result?.jsonMemberClass.toString()

                    val modalBottomSheet = ModalBottomSheet()
                    val bundle = Bundle()

                    modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
                    bundle.putString("message", labelScan)
                    modalBottomSheet.arguments = bundle
                    Log.d("EKO", labelScan)
                    binding.progressBar.visibility = View.GONE
                }
                is ResultState.Failure -> {
                    Toast.makeText(
                        this@ScanResultActivity,
                        it.throwable.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.buttonBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    companion object {
        const val EXTRA_IMG = "extra_img"
    }
}
