package com.example.dogsbreedtechnicaltest.models

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

class BreedResponse {
    @SerializedName("message")
    val message: JsonElement? = null

    @SerializedName("status")
    val status: String? = null
}