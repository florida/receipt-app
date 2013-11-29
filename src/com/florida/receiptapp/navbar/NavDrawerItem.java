package com.florida.receiptapp.navbar;

public interface NavDrawerItem {
	public int getId();
	public String getLabel();
	public int getType();
	public boolean isEnabled();
	public boolean updateActionBarTitle();
	public void setId(int id);
	public void setLabel(String label);
}

