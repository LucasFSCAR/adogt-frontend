package com.example.myapplication.ui.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.api.controllers.PetController
import com.example.myapplication.model.Pet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetEditActivity: AppCompatActivity() {

    private lateinit var petNameEdit: EditText
    private lateinit var petBreedEdit: EditText
    private lateinit var petAgeEdit: EditText
    private lateinit var petDescriptionEdit: EditText
    private lateinit var petAvailable: CheckBox
    private lateinit var confirmButton: Button
    private lateinit var deleteButton: Button
    private var userId = ""
    private var petId = ""
    private var status = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pet_card_edit)

        petNameEdit = findViewById(R.id.pet_name_edit)
        petBreedEdit = findViewById(R.id.pet_breed_edit)
        petAgeEdit = findViewById(R.id.pet_age_edit)
        petDescriptionEdit = findViewById(R.id.pet_description_edit)
        confirmButton = findViewById(R.id.edit_pet_button)
        petAvailable = findViewById(R.id.available)
        deleteButton = findViewById(R.id.delete_pet_button)

        val name = intent.getStringExtra("name")
        val breed = intent.getStringExtra("breed")
        val age = intent.getStringExtra("age")
        val description = intent.getStringExtra("description")
        status = intent.getStringExtra("status")
        userId = intent.getStringExtra("userId")
        petId = intent.getStringExtra("petId")

        petNameEdit.setText(name)
        petBreedEdit.setText(breed)
        petAgeEdit.setText(age)
        petDescriptionEdit.setText(description)
        if (status.equals("disponivel")) {
            petAvailable.setChecked(true)
        } else {
            petAvailable.setChecked(false)
        }

        confirmButton.setOnClickListener {
            if (petNameEdit.text!!.isEmpty() || petBreedEdit.text!!.isEmpty() || petAgeEdit.text!!.isEmpty() || petDescriptionEdit.text!!.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            } else {
                editPet()
            }
        }

        deleteButton.setOnClickListener{
            deletePet()
        }
    }

    private fun editPet() {
        status = if (petAvailable.isChecked) "disponivel" else "indisponivel"

        val pet = Pet(petNameEdit.text.toString(), petAgeEdit.text.toString().toInt(), petBreedEdit.text.toString(), petDescriptionEdit.text.toString(), petId, userId, status)
        val call = PetController.updatePet(pet, petId)
        call.enqueue(object : Callback<Pet> {
            override fun onResponse(call: Call<Pet>?, response: Response<Pet>?) {
                if (response != null && response!!.body() != null) {
                    Toast.makeText(applicationContext, "Pet Atualizado com sucesso!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Deu ruim!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Pet>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Deu ruim!", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun deletePet() {
        val call = PetController.deletePet(petId)
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                if (response != null && response!!.body() != null) {
                    Toast.makeText(applicationContext, "Pet Removido com sucesso!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Deu ruim!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Deu ruim!", Toast.LENGTH_LONG).show()
            }
        })
    }

}