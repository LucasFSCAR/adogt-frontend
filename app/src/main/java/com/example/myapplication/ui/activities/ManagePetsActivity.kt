package com.example.myapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api.controllers.PetController
import com.example.myapplication.model.Pet
import com.example.myapplication.singletons.UserInfo
import com.example.myapplication.ui.adapters.PetListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagePetsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var notPossibleToLoad: LinearLayoutCompat
    private lateinit var refreshButton: Button
    private lateinit var createButton: FloatingActionButton
    private lateinit var petDoestExists: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manage_pets)

        viewManager = LinearLayoutManager(this)
        viewAdapter = PetListAdapter(emptyList())
        notPossibleToLoad = findViewById(R.id.not_loaded2)
        refreshButton = findViewById(R.id.refresh_button2)
        petDoestExists = findViewById(R.id.petdne)

        recyclerView = findViewById<RecyclerView>(R.id.pet_list2).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        loadList()
        refreshButton.setOnClickListener {
            notPossibleToLoad.visibility = View.GONE
            loadList()
        }

    }

    private fun loadList() {
        val id = UserInfo.userData!!.id
        val call = PetController.getPetByUserId(id)
        call.enqueue(object : Callback<List<Pet>> {
            override fun onResponse(call: Call<List<Pet>>?, response: Response<List<Pet>>?) {
                if (response != null && response!!.body() != null) {
                    response!!.body()?.let {
                        (viewAdapter as PetListAdapter).updateData(it)
                        if(it.size == 0)
                            petDoestExists.visibility = View.VISIBLE
                    }
                } else {
                    handleEmptyList()
                }
            }

            override fun onFailure(call: Call<List<Pet>>?, t: Throwable?) {
                handleEmptyList()
            }
        })
    }

    fun handleEmptyList() {
        notPossibleToLoad.visibility = View.VISIBLE
    }

}