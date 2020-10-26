package com.example.retrofitexample.di

import androidx.lifecycle.MutableLiveData
import com.example.retrofitexample.data.Country
import com.example.retrofitexample.data.ServerResponse
import com.example.retrofitexample.network.CountryApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CountryRepository @Inject constructor() {

    @Inject
    lateinit var retrofitService: CountryApiService

    fun getServiceResponse(): MutableLiveData<ServerResponse<List<Country>, Throwable>> {
        val responseLiveData = MutableLiveData(
            ServerResponse<List<Country>, Throwable>(
                ServerResponse.Status.SUCCESS,
                null,
                null
            )
        )
        retrofitService.getProperties().enqueue(
            object : Callback<List<Country>> {
                override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                    responseLiveData.value = ServerResponse.error(t)
                }

                override fun onResponse(
                    call: Call<List<Country>>,
                    response: Response<List<Country>>
                ) {
                    if (response.isSuccessful) {
                        responseLiveData.value =
                            ServerResponse.success(response.body())
                    } else {
                        responseLiveData.value =
                            ServerResponse.error<List<Country>, Throwable>(null)
                    }
                }
            }
        )
        return responseLiveData
    }
}