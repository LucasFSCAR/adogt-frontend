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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPetActivity : AppCompatActivity() {

    lateinit var createButton: Button
    lateinit var breedInput: EditText
    lateinit var ageInput: EditText
    lateinit var nameInput: EditText
    lateinit var descriptionInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_pet)

        createButton = findViewById(R.id.register_pet)
        nameInput = findViewById(R.id.create_pet_name)
        ageInput = findViewById(R.id.create_pet_age)
        breedInput = findViewById(R.id.create_pet_breed)
        descriptionInput = findViewById(R.id.create_pet_description)

        createButton.setOnClickListener {
            if (nameInput.text!!.isEmpty() || ageInput.text!!.isEmpty() || breedInput.text!!.isEmpty() || descriptionInput.text!!.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            } else {
                createPet()
            }
        }
    }

    // TODO: Check requisition error 500
    private fun createPet() {
        val pet = Pet(nameInput.text.toString(), ageInput.text.toString().toInt(), breedInput.text.toString(), descriptionInput.text.toString(), "", "", "")
        val call = PetController.createPet(pet)
        call.enqueue(object : Callback<Pet> {
            override fun onResponse(call: Call<Pet>?, response: Response<Pet>?) {
                if (response != null && response!!.body() != null) {
                    Toast.makeText(applicationContext, "Pet criado com sucesso!", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(applicationContext, "Deu ruim!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Pet>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Deu ruim!", Toast.LENGTH_LONG).show()
            }
        })
    }

}