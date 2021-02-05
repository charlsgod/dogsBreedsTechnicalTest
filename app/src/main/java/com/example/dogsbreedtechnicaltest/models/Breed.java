package com.example.dogsbreedtechnicaltest.models;

import com.google.gson.annotations.SerializedName;

public class Breed {

	@SerializedName("image")
	private String image;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

	public String getImage(){
		return image;
	}

	public String getDescription(){
		return description;
	}

	public String getTitle(){
		return title;
	}
}