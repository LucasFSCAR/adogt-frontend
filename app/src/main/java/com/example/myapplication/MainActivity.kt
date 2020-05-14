package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = PetListAdapter(emptyList())

        recyclerView = findViewById<RecyclerView>(R.id.pet_list).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val call = PetController.allPets()
        call.enqueue(object : Callback<List<Pet>> {
            override fun onResponse(call: Call<List<Pet>>?, response: Response<List<Pet>>?) {
                print("Deu bom")
                response!!.body()?.let { (viewAdapter as PetListAdapter).updateData(it) }
/*                recyclerView.apply {
                    val pets: List<Pet>? = response!!.body()
                    viewAdapter = PetListAdapter(pets!!)
                    setHasFixedSize(true)
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
                */
            }

            override fun onFailure(call: Call<List<Pet>>?, t: Throwable?) {
                print("Deu erro :(")
            }
        })
    }
}