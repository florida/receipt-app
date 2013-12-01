package com.florida.receiptapp.classes;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Receipt")
public class Receipt extends ParseObject {
		
	public Receipt() { }
	
	public void setGst(float _gst) {
		put("gst", _gst);
	}
	
	public void setPst(float _pst) {
		put("hst", _pst);
	}
	
	public void setStoreName(String _name) {
		put("store_name", _name);
	}
	
	public void setSubTotal(float _sub_total) {
		put("sub_total", _sub_total);
	}
	
	public void setTotal(float _total) {
		put("total", _total);
	}
	
	public String getStoreName() {
		return getString("store_name");
	}
	
	public double getGst() {
		return getDouble("gst");
	}
	
	public double getPst() {
		return getDouble("pst");
	}
	
	public double getSubTotal() {
		return getDouble("sub_total");
	}
	
	public double getTotal() {
		return getDouble("total");
	}
	
	public ParseFile getPhotoFile() {
		return getParseFile("photo");
	}

	public void setPhotoFile(ParseFile file) {
		put("photo", file);
	}
	
	public void setCategoryId(String category) {
		put("category_id", category);
	}
	
	public String getCategoryId() {
		return getString("category_id");
	}

}
