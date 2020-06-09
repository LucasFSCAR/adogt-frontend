package com.example.myapplication.api.services

import com.example.myapplication.model.Pet
import retrofit2.Call
import retrofit2.http.*

interface PetService {
    @GET("/pet")
    fun allPets(): Call<List<Pet>>

    @GET("/getPetsByUser/{id}")
    fun getPetByUserId(@Path(value="id") id:String): Call<List<Pet>>

    @POST("/pet")
    fun createPet(@Body pet:Pet): Call<Pet>

    @PUT("/pet/{id}")
    fun updatePet(@Body pet:Pet, @Path(value="id") id:String): Call<Pet>

    @DELETE("/pet/{id}")
    fun deletePet(@Path(value="id") id:String): Call<Unit>
}
