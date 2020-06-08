package com.example.myapplication.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class PetInfoActivity : AppCompatActivity() {

    private lateinit var petNameField: TextView
    private lateinit var petBreedField: TextView
    private lateinit var petAgeField: TextView
    private lateinit var petDescriptionField: TextView
    private lateinit var petOwnerNameField: TextView
    private lateinit var petOwnerPhoneField: TextView

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

        petNameField.setText(name)
        petAgeField.setText(age)
        petBreedField.setText(breed)
        petDescriptionField.setText(description)

    }
}