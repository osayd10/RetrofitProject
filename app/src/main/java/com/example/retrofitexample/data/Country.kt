package com.example.retrofitexample.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country(@SerializedName("flags") @Expose val flags:List<String> ,  val name:String, val population:Double)
