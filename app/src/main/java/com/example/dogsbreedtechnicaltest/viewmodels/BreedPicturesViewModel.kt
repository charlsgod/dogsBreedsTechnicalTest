package com.example.dogsbreedtechnicaltest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dogsbreedtechnicaltest.repositories.BreedPicturesRepository

class BreedPicturesViewModel : ViewModel() {
    private val breedPicturesRepository: BreedPicturesRepository = BreedPicturesRepository()
    fun getBreedPictures(breedName: String?): LiveData<List<String>?> {
        return breedPicturesRepository.getBreedPictures(breedName)
    }

}