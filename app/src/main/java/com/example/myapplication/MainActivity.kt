package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.controllers.PetController
import com.example.myapplication.model.Pet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var notPossibleToLoad: LinearLayoutCompat
    private lateinit var refreshButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = PetListAdapter(emptyList())
        notPossibleToLoad = findViewById(R.id.not_loaded)

        refreshButton = findViewById(R.id.refresh_button)

        recyclerView = findViewById<RecyclerView>(R.id.pet_list).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        refreshButton.setOnClickListener{
            notPossibleToLoad.visibility = View.GONE
            loadList()
        }
        loadList()
    }

    private fun loadList(){
        val call = PetController.allPets()
        call.enqueue(object : Callback<List<Pet>> {
            override fun onResponse(call: Call<List<Pet>>?, response: Response<List<Pet>>?) {
                response!!.body()?.let { (viewAdapter as PetListAdapter).updateData(it) }
            }

            override fun onFailure(call: Call<List<Pet>>?, t: Throwable?) {
                notPossibleToLoad.visibility = View.VISIBLE
            }
        })
    }
}