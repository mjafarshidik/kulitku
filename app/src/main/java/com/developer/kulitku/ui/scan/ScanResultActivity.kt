package com.developer.kulitku.ui.scan

import android.accounts.AccountManager.get
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.developer.kulitku.R
import com.developer.kulitku.data.source.remote.RecommendationIngredientResponse
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.data.source.remote.kubuku.KubukuData
import com.developer.kulitku.data.source.remote.kubuku.KubukuResponse
import com.developer.kulitku.databinding.ActivityScanResultBinding
import com.developer.kulitku.ui.home.HomeActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import id.zelory.compressor.Compressor
import kotlinx.coroutines.launch
import java.io.File


class ScanResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanResultBinding
    private lateinit var viewModel: ScanViewModel
    private lateinit var labelScan: String
    private lateinit var adapter: RecommendationAdapter
    private lateinit var textLabel: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetView.root)
        val extras = intent.extras

        intent.hasExtra(EXTRA_IMG)
        val imgUri = extras?.getString(EXTRA_IMG)
        val file = File(imgUri?.toUri()?.path)
        viewModel = ScanViewModel()

        adapter = RecommendationAdapter()

        lifecycleScope.launch {
            val compressedImageFile = Compressor.compress(this@ScanResultActivity, file)
            viewModel.uploadImage(compressedImageFile)
        }

        Glide.with(this)
            .load(imgUri)
            .transform(RoundedCorners(32))
            .apply(RequestOptions.overrideOf(314, 526))
            .into(binding.ivPhotoResult)

        textLabel = findViewById(R.id.textview_label_scan)
        progressBar = findViewById(R.id.progress_bar_bottom_sheet)
        recyclerView = findViewById(R.id.rvRecommend)

        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(view: View, i: Int) {
                // do something when state changes
                when (i) {
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        progressBar.visibility = View.VISIBLE

                        viewModel.labelState.observe(this@ScanResultActivity) {
                            when (it) {
                                is ResultState.Loading -> {
                                }
                                is ResultState.Success -> {
                                    labelScan = it.value.result?.jsonMemberClass.toString()
                                    textLabel.text = labelScan

                                    progressBar.visibility = View.GONE
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

                        viewModel.recommendationState.observe(this@ScanResultActivity) {
                            when (it) {
                                is ResultState.Loading -> {
                                }
                                is ResultState.Success -> {
                                    adapter.setRecommendation(it.value)
                                    showRecyclerList()
                                    progressBar.visibility = View.GONE
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
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {}
                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_SETTLING -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    }
                }
            }

            override fun onSlide(view: View, v: Float) {
                // do something when slide happens
            }
        })

        binding.buttonBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun showRecyclerList() {
        recyclerView.layoutManager =
            LinearLayoutManager(this@ScanResultActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

    }

    companion object {
        const val EXTRA_IMG = "extra_img"
    }
}
