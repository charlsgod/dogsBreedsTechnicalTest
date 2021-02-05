package com.example.dogsbreedtechnicaltest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsbreedtechnicaltest.R
import com.example.dogsbreedtechnicaltest.adapters.BreedPicturesAdapter.BreedPictureViewHolder
import com.example.dogsbreedtechnicaltest.databinding.ItemContainerBreedPictureBinding

class BreedPicturesAdapter(private val breedsPictures: List<String>) : RecyclerView.Adapter<BreedPictureViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedPictureViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val breedBinding: ItemContainerBreedPictureBinding = DataBindingUtil.inflate(
                layoutInflater!!, R.layout.item_container_breed_picture, parent, false)
        return BreedPictureViewHolder(breedBinding)
    }

    override fun getItemCount(): Int {
        return breedsPictures.size
    }

    inner class BreedPictureViewHolder(private val itemContainerBreedPictureBinding: ItemContainerBreedPictureBinding) : RecyclerView.ViewHolder(itemContainerBreedPictureBinding.root) {
        fun bindBreed(image: String?) {
            itemContainerBreedPictureBinding.image = image
            itemContainerBreedPictureBinding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: BreedPictureViewHolder, position: Int) {
        holder.bindBreed(breedsPictures[position])
    }
}