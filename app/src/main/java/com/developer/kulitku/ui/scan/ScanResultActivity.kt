package com.developer.kulitku.ui.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.developer.kulitku.R
import com.developer.kulitku.databinding.ActivityScanResultBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File


class ScanResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val modalBottomSheet = ModalBottomSheet()
        modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)

        val directory = File(externalMediaDirs[0].absolutePath)
        val files = directory.listFiles()
        val data = files?.last()

        Glide.with(this)
            .load(data)
            .transform(RoundedCorners(32))
            .apply(RequestOptions.overrideOf(314, 600))
            .into(binding.ivPhotoResult)

        supportActionBar?.hide()
    }
}

class ModalBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_dialog, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}