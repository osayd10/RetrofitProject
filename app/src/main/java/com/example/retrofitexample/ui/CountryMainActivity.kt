package com.example.retrofitexample.ui

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexample.R
import com.example.retrofitexample.di.CountryViewModel
import com.example.retrofitexample.recyclerview.CountryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: CountryViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var failureTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressbar)
        failureTextView = findViewById(R.id.failureMessage)

        setupRecyclerView()

        viewModel.response.observe(this, Observer {
            if (it.error != null) {
                failureTextView.text = it.error.toString()
                showFailureMessage()
            } else
                countryAdapter.submitList(it.data)
        })
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        countryAdapter = CountryAdapter(this)
        recyclerView.adapter = countryAdapter
        hideLoading()
    }

    private fun hideLoading() {
        progressBar.isVisible = false
        recyclerView.isVisible = true
    }

    private fun showFailureMessage() {
        recyclerView.isVisible = false
        failureTextView.isVisible = true
    }
}


