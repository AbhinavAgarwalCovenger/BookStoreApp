package com.example.bookstoreapp;

import com.google.gson.annotations.SerializedName;

public class Login{

	@SerializedName("password")
	private String password;

	@SerializedName("loginType")
	private String loginType;

	@SerializedName("email")
	private String email;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setLoginType(String loginType){
		this.loginType = loginType;
	}

	public String getLoginType(){
		return loginType;
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
			"Login{" + 
			"password = '" + password + '\'' + 
			",loginType = '" + loginType + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}