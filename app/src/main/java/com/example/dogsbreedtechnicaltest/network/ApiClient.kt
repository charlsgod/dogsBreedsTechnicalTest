package com.example.dogsbreedtechnicaltest.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    @JvmStatic
    var retrofit: Retrofit? = null
        get() {
            if (field == null) {
                field = Retrofit.Builder()
                        .baseUrl("https://dog.ceo/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return field
        }
        private set
}