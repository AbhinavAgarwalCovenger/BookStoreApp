package com.example.bookstoreapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Login{

	@SerializedName("password")
	private String password;

	@SerializedName("loginType")
	private String loginType;

	@SerializedName("email")
	private String email;

	@SerializedName("Uid")
	private String cartId;

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

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	@Override
 	public String toString(){
		return 
			"Login{" + 
			"password = '" + password + '\'' + 
			",loginType = '" + loginType + '\'' + 
			",email = '" + email + '\'' +
			",cartId = '" + cartId + '\'' +
					"}";
		}
}