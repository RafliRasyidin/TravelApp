package com.rasyidin.travelapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rasyidin.travelapp.core.adapter.TourismAdapter
import com.rasyidin.travelapp.core.data.Resource
import com.rasyidin.travelapp.databinding.ActivityMainBinding
import com.rasyidin.travelapp.ui.detail.DetailActivity
import com.rasyidin.travelapp.ui.profile.ProfileActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var tourismAdapter: TourismAdapter

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var behavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomSheet()

        setupAdapter()

        onItemClick()

        subscribeToObserver()

        moveToProfileActivity()
    }

    private fun setupAdapter() = binding.rvTourism.apply {
        tourismAdapter = TourismAdapter()
        layoutManager = LinearLayoutManager(this@MainActivity)
        setHasFixedSize(true)
        adapter = tourismAdapter
    }

    private fun onItemClick() {
        tourismAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

    }

    private fun moveToProfileActivity() {
        binding.imgProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun subscribeToObserver() {
        mainViewModel.getTourism().observe(this, { resource ->
            when (resource) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    resource.data?.let {
                        tourismAdapter.setTourism(it)
                    }
                    binding.imgNoData.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.imgNoData.visibility = View.VISIBLE
                    binding.imgNoData.playAnimation()
                    Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show()
                    Log.e("MainActivity", "Message: ${resource.message}")
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.imgNoData.visibility = View.GONE
                }
            }
        })
    }

    private fun setupBottomSheet() {
        behavior = BottomSheetBehavior.from(binding.bottomSheet)
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvTourism.adapter = null
    }
}