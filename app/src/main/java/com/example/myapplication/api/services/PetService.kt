package com.example.myapplication.api.services

import com.example.myapplication.model.Pet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PetService {
    @GET("/pet")
    fun allPets(): Call<List<Pet>>

    @GET("/getPetsByUser/{id}")
    fun getPetByUserId(@Path(value="id") id:String): Call<List<Pet>>
}
