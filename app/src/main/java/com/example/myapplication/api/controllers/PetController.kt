package com.example.myapplication.api.controllers

import com.example.myapplication.api.RetrofitConfig
import com.example.myapplication.model.Pet
import retrofit2.Call

object PetController {
    @JvmStatic
    fun allPets(): Call<List<Pet>> {
        return RetrofitConfig().petService().allPets()
    }
}