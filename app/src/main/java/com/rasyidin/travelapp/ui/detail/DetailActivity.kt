package com.rasyidin.travelapp.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rasyidin.travelapp.core.domain.model.Tourism
import com.rasyidin.travelapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val tourism: Tourism? by lazy {
        intent.getParcelableExtra(EXTRA_DATA)
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDetailTourism(tourism)

        onBackClicked()

        onShareClicked(tourism)
    }

    private fun showDetailTourism(tourism: Tourism?) {
        binding.apply {
            content.tvTourismName.text = tourism?.name
            content.tvLocation.text = tourism?.address
            content.tvDescription.text = tourism?.description
            Glide.with(this@DetailActivity)
                .load(tourism?.image)
                .into(imgTourism)
        }
    }

    private fun onBackClicked() {
        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    private fun onShareClicked(tourism: Tourism?) {
        binding.imgShare.setOnClickListener {
            binding.imgShare.playAnimation()
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, tourism?.description)
                putExtra(Intent.EXTRA_TITLE, tourism?.name)
                type = "text/plain"
            }
            startActivity(shareIntent)
        }
    }
}