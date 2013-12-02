package com.florida.receiptapp.classes;

import android.content.Context;

public class GridItem {
	int image;
	String title;
	
	public GridItem(String image, String title, Context context) {
		super();
		this.image = context.getResources().getIdentifier(image, "drawable", context.getPackageName()); 
		this.title = title;
	}
	
	public int getImage() {
		return image;
	}
	
	public void setImage(int image) {
		this.image = image;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
