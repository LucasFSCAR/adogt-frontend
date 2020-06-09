package com.example.myapplication.ui.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.api.controllers.PetController
import com.example.myapplication.api.controllers.UserController
import com.example.myapplication.model.Pet
import com.example.myapplication.model.User
import com.example.myapplication.ui.adapters.PetListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var registerButton: Button
    lateinit var nameInput: EditText
    lateinit var surnameInput: EditText
    lateinit var phoneInput: EditText
    lateinit var emailInput: EditText
    lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_user)
        registerButton = findViewById(R.id.register_button)
        nameInput = findViewById(R.id.name_input)
        surnameInput = findViewById(R.id.surname_input)
        phoneInput = findViewById(R.id.phone_input)
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)

        registerButton.setOnClickListener {
            if (nameInput.text!!.isEmpty() || passwordInput.text!!.isEmpty() || emailInput.text!!.isEmpty() || phoneInput.text!!.isEmpty() || surnameInput.text!!.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            } else {
                createRequest()
            }
        }
    }

    private fun createRequest() {
    val user = User(nameInput.text.toString(), surnameInput.text.toString(), emailInput.text.toString(), phoneInput.text.toString(), passwordInput.text.toString(), "")
        val call = UserController.createUser(user)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                if (response != null && response!!.body() != null) {
                    Toast.makeText(applicationContext, "Usuário criado com sucesso!", Toast.LENGTH_LONG).show()
                    finish()

                } else {
                    Toast.makeText(applicationContext, "Deu ruim!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Deu ruim!", Toast.LENGTH_LONG).show()
            }
        })
    }


}