package com.example.retrofitexample.di

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitexample.data.Country
import com.example.retrofitexample.data.ServerResponse

class CountryViewModel @ViewModelInject constructor(repository: CountryRepository) :
    ViewModel() {

    private val _response: MutableLiveData<ServerResponse<List<Country>, Throwable>> =
        repository.getServiceResponse()

    val response: LiveData<ServerResponse<List<Country>, Throwable>>
        get() = _response
}