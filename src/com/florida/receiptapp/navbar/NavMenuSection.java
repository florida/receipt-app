package com.florida.receiptapp.navbar;

public class NavMenuSection implements NavDrawerItem {
	public static final int SECTION_TYPE = 0;
	public int id;
	public String label;
	
	private NavMenuSection() {
	}
	
	public static NavMenuSection create(int id, String label) {
		NavMenuSection section = new NavMenuSection();
		section.setLabel(label);
		return section;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public int getType() {
		return SECTION_TYPE;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public boolean updateActionBarTitle() {
		return false;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

}
