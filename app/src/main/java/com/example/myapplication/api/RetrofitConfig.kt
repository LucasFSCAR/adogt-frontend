package com.example.myapplication.api

import com.example.myapplication.api.services.PetService
import com.example.myapplication.api.services.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    private val retrofit: Retrofit
    private val baseUrl = "http://7ec9ee141b5d.ngrok.io"

    constructor() {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun petService(): PetService = retrofit.create(PetService::class.java)
    fun userService(): UserService = retrofit.create(UserService::class.java)
}