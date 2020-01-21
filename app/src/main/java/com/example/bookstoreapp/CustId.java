package com.example.bookstoreapp;

import com.google.gson.annotations.SerializedName;

public class CustId{

	@SerializedName("response")
	private String response;

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"CustId{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}