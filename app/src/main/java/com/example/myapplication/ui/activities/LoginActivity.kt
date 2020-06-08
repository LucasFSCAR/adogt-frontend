package com.example.myapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.api.controllers.UserController
import com.example.myapplication.model.Token
import com.example.myapplication.model.User
import com.example.myapplication.model.UserAuthentication
import com.example.myapplication.singletons.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    companion object {
        val debugMode = false
    }

    lateinit var emailInput: EditText
    lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        emailInput = findViewById(R.id.login_email)
        passwordInput = findViewById(R.id.login_password)

        val loginButton = findViewById<Button>(R.id.login_button)

        if (!debugMode) {
            emailInput.setText("teste@teste")
            passwordInput.setText("123")
        }

        loginButton.setOnClickListener{
            if (debugMode) {
                val i = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(i)
                finish()
            } else {
                login()
            }
        }
        val registerButton = findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener{
            val i = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(i)
        }
    }

    private fun login () {
        val user = UserAuthentication(emailInput.text.toString(), passwordInput.text.toString())
        val call = UserController.loginUser(user)
        call.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>?, response: Response<Token>?) {
                if (response?.body() != null) {
                    Toast.makeText(applicationContext, "Usuário logado com sucesso:", Toast.LENGTH_LONG).show()

                    UserInfo.token = response.body()!!.token!!
                    UserInfo.user = user
                    val i = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(i)
                    finish()

                } else {
                    Toast.makeText(applicationContext, "Deu ruim no login!", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<Token>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Deu ruim no login!", Toast.LENGTH_LONG).show()
            }
        })
    }



}


