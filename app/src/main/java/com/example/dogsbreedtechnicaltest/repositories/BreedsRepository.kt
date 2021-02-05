package com.example.dogsbreedtechnicaltest.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dogsbreedtechnicaltest.models.BreedResponse
import com.example.dogsbreedtechnicaltest.network.ApiClient
import com.example.dogsbreedtechnicaltest.network.ApiService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BreedsRepository {
    private val apiService: ApiService = ApiClient.retrofit!!.create(ApiService::class.java)
    val breeds: LiveData<List<String>?>
        get() {
            val data = MutableLiveData<List<String>?>()
            apiService.allBreeds!!.enqueue(object : Callback<BreedResponse?> {
                override fun onResponse(call: Call<BreedResponse?>, breedResponse: Response<BreedResponse?>) {
                    if (breedResponse.isSuccessful) {
                        if (breedResponse.body()!!.status.equals("success")) {
                            val message = breedResponse.body()!!.message
                            data.value = JSONObject(message!!.asJsonObject!!.toString()).keys().asSequence().toList()
                        } else {
                            data.value = null
                        }
                    } else {
                        data.value = null
                    }
                }

                override fun onFailure(call: Call<BreedResponse?>, t: Throwable) {
                    data.value = null
                }
            })
            return data
        }

}