package com.example.myapplication.ui.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.api.controllers.PetController
import com.example.myapplication.api.controllers.UserController
import com.example.myapplication.model.Pet
import com.example.myapplication.model.User
import com.example.myapplication.singletons.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetInfoActivity : AppCompatActivity() {

    private lateinit var petNameField: TextView
    private lateinit var petBreedField: TextView
    private lateinit var petAgeField: TextView
    private lateinit var petDescriptionField: TextView
    private lateinit var petOwnerNameField: TextView
    private lateinit var petOwnerPhoneField: TextView
    private var petOwnerName = ""
    private var petOwnerPhone = ""
    private var userId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pet_activity)

        petNameField = findViewById(R.id.pet_name_field)
        petBreedField = findViewById(R.id.pet_breed_detail)
        petAgeField = findViewById(R.id.pet_age_detail)
        petDescriptionField = findViewById(R.id.pet_description_field)
        petOwnerNameField = findViewById(R.id.pet_owner_name_field)
        petOwnerPhoneField = findViewById(R.id.pet_owner_phone_field)

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val breed = intent.getStringExtra("breed")
        val description = intent.getStringExtra("description")
        userId = intent.getStringExtra("userId")

        getPetOwnerInfo()

        petNameField.setText(name)
        petAgeField.setText(age)
        petBreedField.setText(breed)
        petDescriptionField.setText(description)
        petOwnerNameField.setText(petOwnerName)
        petOwnerPhoneField.setText(petOwnerPhone)

    }

    private fun getPetOwnerInfo() {

        val call = UserController.getUserById(userId)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                if (response != null && response!!.body() != null) {
                    // Toast.makeText(applicationContext, "Recuperou os dados do dono!", Toast.LENGTH_LONG).show()
                    petOwnerPhone = response.body()!!.cellphone
                    petOwnerName = response.body()!!.name
                    petOwnerNameField.setText(petOwnerName)
                    petOwnerPhoneField.setText(petOwnerPhone)

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