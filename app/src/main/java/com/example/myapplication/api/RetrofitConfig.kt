package com.example.myapplication.api

import com.example.myapplication.api.services.PetService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    private val retrofit: Retrofit
    private val baseUrl = "http://586b9e88.ngrok.io"

    constructor() {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun petService(): PetService = retrofit.create(PetService::class.java)
}