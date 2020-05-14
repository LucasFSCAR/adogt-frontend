package com.example.myapplication.api.services

import com.example.myapplication.model.Pet
import retrofit2.Call
import retrofit2.http.GET

interface PetService {
    @GET("/pet")
    fun allPets(): Call<List<Pet>>
}