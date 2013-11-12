package com.florida.receiptapp;

public class Receipt {
	
	private String name;
	private float gst;
	private float hst;
	private float sub_total;
	private float total;
	
	
	public Receipt(String _name) {
		name = _name;
	}
	
	public void setGst(float _gst) {
		gst = _gst;
	}
	
	public void setHst(float _hst) {
		hst = _hst;
	}
	
	public void setName(String _name) {
		name = _name;
	}
	
	public void setSubTotal(float _sub_total) {
		sub_total = _sub_total;
	}
	
	public void total(float _total) {
		total = _total;
	}
	
	public String getName() {
		return name;
	}
	
	public float getGst() {
		return gst;
	}
	
	public float getHst() {
		return hst;
	}
	
	public float getSubTotal() {
		return sub_total;
	}
	
	public float getTotal() {
		return total;
	}

}
