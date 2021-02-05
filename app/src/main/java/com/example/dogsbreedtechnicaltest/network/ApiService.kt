package com.example.dogsbreedtechnicaltest.network

import com.example.dogsbreedtechnicaltest.models.Breed
import com.example.dogsbreedtechnicaltest.models.BreedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @get:GET("breeds/list/all")
    val allBreeds: Call<BreedResponse?>?

    @GET("breed/{specific_breed}/images")
    fun getBreedPictures(@Path("specific_breed") breed: String?): Call<BreedResponse?>?
}