package com.example.bookstoreapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Books implements Serializable {

	@SerializedName("author")
	private String author;

	@SerializedName("price")
	private String price;

	@SerializedName("name")
	private String name;

	@SerializedName("publisher")
	private String publisher;

	@SerializedName("url")
	private String url;

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPublisher(String publisher){
		this.publisher = publisher;
	}

	public String getPublisher(){
		return publisher;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Books{" + 
			"author = '" + author + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",publisher = '" + publisher + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}