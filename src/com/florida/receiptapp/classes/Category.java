package com.florida.receiptapp.classes;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Category")
public class Category extends ParseObject {
	public Category() { }
	
	public void setName(String _name) {
		put("name", _name);
	}
	
	public String getName() {
		return getString("name");
	}
	
	public void setUser(ParseObject _user) {
		put("user", _user);
	}
	
	public ParseObject getUser(){
		return getParseObject("user");
	}
	
}
