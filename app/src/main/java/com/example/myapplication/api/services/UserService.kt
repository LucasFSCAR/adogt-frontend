package com.example.myapplication.api.services

import com.example.myapplication.model.Token
import com.example.myapplication.model.User
import com.example.myapplication.model.UserAuthentication
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @POST("/user")
    fun createUser(@Body user:User): Call<User>

    @POST("/user/login")
    fun loginUser(@Body user:UserAuthentication): Call<Token>

    @GET("/user/getByEmail/{email}")
    fun getUserByEmail(@Path(value="email") email:String): Call<User>
}