package com.example.myapplication.singletons

import com.example.myapplication.model.UserAuthentication

object UserInfo{
    init {}
    var token = ""
    var user:UserAuthentication? = null
}