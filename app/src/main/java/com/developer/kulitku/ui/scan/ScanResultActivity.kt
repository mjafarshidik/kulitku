package com.developer.kulitku.ui.scan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.databinding.ActivityScanResultBinding
import com.developer.kulitku.databinding.BottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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

        viewModel = ScanViewModel()

        val directory = File(externalMediaDirs[0].absolutePath)
        val files = directory.listFiles()
        val data = files.last()

        lifecycleScope.launch {
            val compressedImageFile = Compressor.compress(this@ScanResultActivity, data)
            viewModel.uploadImage(compressedImageFile)
        }

        Glide.with(this)
            .load(data)
            .transform(RoundedCorners(32))
            .apply(RequestOptions.overrideOf(314, 600))
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

        supportActionBar?.hide()


    }

    class ModalBottomSheet : BottomSheetDialogFragment() {
        private lateinit var binding: BottomSheetDialogBinding

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            binding = BottomSheetDialogBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val label = arguments?.getString("message")
            binding.textviewLabelScan.text = label
            Log.d("EKA", label.toString())
        }

        companion object {
            const val TAG = "ModalBottomSheet"

        }
    }
}
