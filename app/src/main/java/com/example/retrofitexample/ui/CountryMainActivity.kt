package com.example.retrofitexample.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexample.R
import com.example.retrofitexample.databinding.ActivityMainBinding
import com.example.retrofitexample.di.CountryViewModel
import com.example.retrofitexample.recyclerview.CountryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: CountryViewModel by viewModels()
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        setupRecyclerView()

        viewModel.response.observe(this, Observer {
            if (it.error != null) {
                binding.failureMessage.text = it.error.toString()
                showFailureMessage()
            } else
                countryAdapter.submitList(it.data)
        })
    }

    private fun setupRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager
        countryAdapter = CountryAdapter(this)
        binding.recyclerview.adapter = countryAdapter
        hideLoading()
    }

    private fun hideLoading() {
        binding.progressbar.isVisible = false
        binding.recyclerview.isVisible = true
    }

    private fun showFailureMessage() {
        binding.recyclerview.isVisible = false
        binding.failureMessage.isVisible = true
    }
}


