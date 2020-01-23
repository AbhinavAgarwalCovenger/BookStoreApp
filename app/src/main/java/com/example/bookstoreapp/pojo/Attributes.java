package com.example.bookstoreapp.pojo;


import com.google.gson.annotations.SerializedName;

public class Attributes {

	@SerializedName("year")
	private String year;

	@SerializedName("noofpages")
	private String noofpages;

	@SerializedName("publisher")
	private String publisher;

	@SerializedName("binding")
	private String binding;

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
	}

	public void setNoofpages(String noofpages){
		this.noofpages = noofpages;
	}

	public String getNoofpages(){
		return noofpages;
	}

	public void setPublisher(String publisher){
		this.publisher = publisher;
	}

	public String getPublisher(){
		return publisher;
	}

	public void setBinding(String binding){
		this.binding = binding;
	}

	public String getBinding(){
		return binding;
	}

	@Override
 	public String toString(){
		return 
			"Attributes{" +
			"year = '" + year + '\'' + 
			",noofpages = '" + noofpages + '\'' + 
			",publisher = '" + publisher + '\'' + 
			",binding = '" + binding + '\'' + 
			"}";
		}
}