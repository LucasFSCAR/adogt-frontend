package com.example.myapplication.api.controllers

import com.example.myapplication.api.RetrofitConfig
import com.example.myapplication.model.Token
import com.example.myapplication.model.User
import com.example.myapplication.model.UserAuthentication
import retrofit2.Call
object UserController {

    @JvmStatic
    fun createUser(user:User): Call<User> {
        return RetrofitConfig().userService().createUser(user)
    }
    @JvmStatic
    fun loginUser(user:UserAuthentication): Call<Token> {
        return RetrofitConfig().userService().loginUser(user)
    }

    @JvmStatic
    fun getUserByEmail(email:String): Call<User> {
        return RetrofitConfig().userService().getUserByEmail(email)
    }
}