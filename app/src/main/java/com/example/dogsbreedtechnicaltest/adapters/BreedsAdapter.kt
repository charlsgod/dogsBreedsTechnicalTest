package com.example.dogsbreedtechnicaltest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsbreedtechnicaltest.R
import com.example.dogsbreedtechnicaltest.adapters.BreedsAdapter.BreedViewHolder
import com.example.dogsbreedtechnicaltest.databinding.ItemContainerBreedBinding
import com.example.dogsbreedtechnicaltest.listeners.BreedListener

class BreedsAdapter(private val breeds: List<String>, private val breedListener: BreedListener) : RecyclerView.Adapter<BreedViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val breedBinding: ItemContainerBreedBinding = DataBindingUtil.inflate(
                layoutInflater!!, R.layout.item_container_breed, parent, false)
        return BreedViewHolder(breedBinding)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bindBreed(breeds[position])
    }

    override fun getItemCount(): Int {
        return breeds.size
    }

    inner class BreedViewHolder(private val itemContainerBreedBinding: ItemContainerBreedBinding) : RecyclerView.ViewHolder(itemContainerBreedBinding.root) {
        fun bindBreed(breed: String?) {
            itemContainerBreedBinding.breed = breed
            itemContainerBreedBinding.executePendingBindings()
            itemContainerBreedBinding.root.setOnClickListener {breedListener.onBreedClick(breed) }
        }
    }
}