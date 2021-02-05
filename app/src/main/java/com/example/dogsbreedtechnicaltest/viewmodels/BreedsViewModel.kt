package com.example.dogsbreedtechnicaltest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dogsbreedtechnicaltest.repositories.BreedsRepository

class BreedsViewModel : ViewModel() {
    private val breedsRepository: BreedsRepository = BreedsRepository()
    val breeds: LiveData<List<String>?>
        get() = breedsRepository.breeds

}