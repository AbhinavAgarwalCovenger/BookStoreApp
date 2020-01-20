package com.example.bookstoreapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable {

	@SerializedName("pincode")
	private String pincode;

	@SerializedName("password")
	private String password;

	@SerializedName("address")
	private String address;

	@SerializedName("Cust_or_Merc")
	private boolean custOrMerc;

	@SerializedName("name")
	private String name;

	@SerializedName("phone_number")
	private String phoneNumber;

	@SerializedName("email")
	private String email;

	public void setPincode(String pincode){
		this.pincode = pincode;
	}

	public String getPincode(){
		return pincode;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setCustOrMerc(boolean custOrMerc){
		this.custOrMerc = custOrMerc;
	}

	public boolean isCustOrMerc(){
		return custOrMerc;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"Customer{" + 
			"pincode = '" + pincode + '\'' + 
			",password = '" + password + '\'' + 
			",address = '" + address + '\'' + 
			",cust_or_Merc = '" + custOrMerc + '\'' + 
			",name = '" + name + '\'' + 
			",phone_number = '" + phoneNumber + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}