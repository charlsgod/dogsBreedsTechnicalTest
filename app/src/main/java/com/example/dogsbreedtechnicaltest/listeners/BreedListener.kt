package com.example.dogsbreedtechnicaltest.listeners

import com.example.dogsbreedtechnicaltest.models.Breed

interface BreedListener {
    fun onBreedClick(breed: String?)
}