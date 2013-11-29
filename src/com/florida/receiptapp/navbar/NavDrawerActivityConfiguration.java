package com.florida.receiptapp.navbar;

import android.widget.BaseAdapter;

public class NavDrawerActivityConfiguration {
	private int main_layout;
	private int drawer_shadow;
	private int drawer_layout_id;
	private int left_drawer_id;
	private int[] action_menu_items_to_hide;
	private NavDrawerItem[] nav_items;
	private int drawer_open_desc;
	private int drawer_close_desc;
	private BaseAdapter base_adapter;
	
	public int getMainLayout() {
		return main_layout;
	}
	
	public void setMainLayout(int main_layout) {
		this.main_layout = main_layout;
	}
	
	public int getDrawerShadow() {
		return drawer_shadow;
	}
	
	public void setDrawerShadow(int drawer_shadow) {
		this.drawer_shadow = drawer_shadow;
	}
	
	public int getDrawerLayoutId() {
		return drawer_layout_id;
	}
	
	public void setDrawerLayoutId(int drawer_layout_id) {
		this.drawer_layout_id = drawer_layout_id;
	}
	
	public int getLeftDrawerId() {
		return left_drawer_id;
	}
	
	public void setLeftDrawerId(int left_drawer_id) {
		this.left_drawer_id = left_drawer_id;
	}
	
	public int[] getActionMenuItemsToHide() {
		return action_menu_items_to_hide;
	}
	
	public void setActionMenuItemsToHide(int[] action_menu_items_to_hide) {
		this.action_menu_items_to_hide = action_menu_items_to_hide;
	}
	
	public NavDrawerItem[] getNavItems() {
		return nav_items;
	}
	
	public void setNavItems(NavDrawerItem[] nav_items) {
		this.nav_items = nav_items;
	}
	
	public int getDrawerOpenDesc() {
		return drawer_open_desc;
	}
	
	public void setDrawerOpenDesc(int drawer_open_desc) {
		this.drawer_open_desc = drawer_open_desc;
	}
	
	public int getDrawerCloseDesc() {
		return drawer_close_desc;
	}
	
	public void setDrawerCloseDesc(int drawer_close_desc) {
		this.drawer_close_desc = drawer_close_desc;
	}
	
	public BaseAdapter getBaseAdapter() {
		return base_adapter;
	}
	
	public void setBaseAdapter(BaseAdapter base_adapter) {
		this.base_adapter = base_adapter;
	}
}
