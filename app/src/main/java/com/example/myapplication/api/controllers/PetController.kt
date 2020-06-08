package com.example.myapplication.api.controllers

import com.example.myapplication.api.RetrofitConfig
import com.example.myapplication.model.Pet
import retrofit2.Call

object PetController {
    @JvmStatic
    fun allPets(): Call<List<Pet>> {
        return RetrofitConfig().petService().allPets()
    }
    @JvmStatic
    fun getPetByUserId(id:String): Call<List<Pet>> {
        return RetrofitConfig().petService().getPetByUserId(id)
    }
    @JvmStatic
    fun createPet(pet:Pet): Call<Pet> {
        return RetrofitConfig().petService().createPet(pet)
    }
    @JvmStatic
    fun updatePet(pet:Pet, id:String): Call<Pet> {
        return RetrofitConfig().petService().updatePet(pet, id)
    }

}