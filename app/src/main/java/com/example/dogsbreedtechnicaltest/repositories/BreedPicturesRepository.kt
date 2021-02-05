package com.example.dogsbreedtechnicaltest.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dogsbreedtechnicaltest.models.BreedResponse
import com.example.dogsbreedtechnicaltest.network.ApiClient.retrofit
import com.example.dogsbreedtechnicaltest.network.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreedPicturesRepository {
    private val apiService: ApiService = retrofit!!.create(ApiService::class.java)
    fun getBreedPictures (breedName: String?): LiveData<List<String>?> {
            val data = MutableLiveData<List<String>?>()
            apiService.getBreedPictures(breedName)!!.enqueue(object : Callback<BreedResponse?> {
                override fun onResponse(call: Call<BreedResponse?>, breedResponse: Response<BreedResponse?>) {
                    if (breedResponse.isSuccessful) {
                        if (breedResponse.body()!!.status.equals("success")) {
                            val message = breedResponse.body()!!.message
                            val gson = Gson()
                            val itemType = object : TypeToken<List<String>>() {}.type
                            val itemList = gson.fromJson<List<String>>(message.toString(), itemType)
                            data.value = itemList
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