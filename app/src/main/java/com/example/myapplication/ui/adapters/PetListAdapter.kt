package com.example.myapplication.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Pet
import com.example.myapplication.ui.activities.PetInfoActivity
import com.example.myapplication.ui.activities.RegisterPetActivity
import com.google.android.material.card.MaterialCardView


class PetListAdapter(private var petList: List<Pet>) :
        RecyclerView.Adapter<PetListAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_card, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val petName = holder.view.findViewById<TextView>(R.id.pet_name)
        petName.text = petList[position].name

        val petAge = holder.view.findViewById<TextView>(R.id.pet_age)
        petAge.text = petList[position].age.toString()

        val petBreed = holder.view.findViewById<TextView>(R.id.pet_breed)
        petBreed.text = petList[position].breed

        val petDescription = holder.view.findViewById<TextView>(R.id.pet_description)
        petDescription.text = petList[position].description

        val petCard = holder.view.findViewById<MaterialCardView>(R.id.pet_card)
        petCard.setOnClickListener{
            val i = Intent(holder.view.context, PetInfoActivity::class.java)
            i.putExtra("name", petName.text.toString())
            i.putExtra("age", petAge.text.toString())
            i.putExtra("breed", petBreed.text.toString())
            i.putExtra("description", petDescription.text.toString())

            holder.view.context.startActivity(i)
        }
    }

    fun updateData(newList: List<Pet>) {
        this.petList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = petList.size
}
