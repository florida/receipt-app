package com.florida.receiptapp.classes;

import java.util.Locale;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Category")
public class Category extends ParseObject {
	public Category() { }
	
	public void setName(String _name) {
		Locale.getDefault();
		put("name", _name);
		put("name_lowercase", _name.toLowerCase());
	}
	
	public String getName() {
		return getString("name");
	}
	
	public String getNameLowerCase(){
		return getString("name_lowercase");
	}
	
	public void setUser(ParseObject _user) {
		put("user", _user);
	}
	
	public ParseObject getUser(){
		return getParseObject("user");
	}
	
}
