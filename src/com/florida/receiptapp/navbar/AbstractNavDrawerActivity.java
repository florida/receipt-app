package com.florida.receiptapp.navbar;

import com.florida.receiptapp.R;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public abstract class AbstractNavDrawerActivity extends FragmentActivity {
	private DrawerLayout m_drawer_layout;
	private ActionBarDrawerToggle m_drawer_toggle;
	
	private ListView m_drawer_list;
	
	private CharSequence m_drawer_title;
	private CharSequence m_title;
	
	private NavDrawerActivityConfiguration nav_conf;
	
	protected abstract NavDrawerActivityConfiguration getNavDrawerConfiguration();
	
	protected abstract void onNavItemSelected(int id);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		nav_conf = getNavDrawerConfiguration();
		setContentView(nav_conf.getMainLayout());
		m_title = m_drawer_title = getTitle();
		
		m_drawer_layout = (DrawerLayout) findViewById(nav_conf.getDrawerLayoutId());
		m_drawer_list = (ListView) findViewById(nav_conf.getLeftDrawerId());
		m_drawer_list.setAdapter(nav_conf.getBaseAdapter());
		m_drawer_list.setOnItemClickListener(new DrawerItemClickListener());
		
		this.initDrawerShadow();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		m_drawer_toggle = new ActionBarDrawerToggle(
				this,
				m_drawer_layout,
				getDrawerIcon(),
				nav_conf.getDrawerOpenDesc(),
				nav_conf.getDrawerCloseDesc()
				) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(m_title);
				invalidateOptionsMenu();
			}
			
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(m_drawer_title);
				invalidateOptionsMenu();
			}
		};
		m_drawer_layout.setDrawerListener(m_drawer_toggle);
	}
	
	protected void initDrawerShadow() {
		m_drawer_layout.setDrawerShadow(nav_conf.getDrawerShadow(), GravityCompat.START);
	}
	
	protected int getDrawerIcon() {
		return R.drawable.ic_drawer;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		m_drawer_toggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		m_drawer_toggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (nav_conf.getActionMenuItemsToHide() != null) {
			boolean drawer_open = m_drawer_layout.isDrawerOpen(m_drawer_list);
			for (int i_item : nav_conf.getActionMenuItemsToHide()) {
				menu.findItem(i_item).setVisible(!drawer_open);
			}
		}
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return m_drawer_toggle.onOptionsItemSelected(item) ? true : false;
	}
	
	 @Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
		 if (keyCode == KeyEvent.KEYCODE_MENU) {
			 if (this.m_drawer_layout.isDrawerOpen(this.m_drawer_list)) {
				 this.m_drawer_layout.closeDrawer(this.m_drawer_list);
			 } else {
				 this.m_drawer_layout.openDrawer(this.m_drawer_list);
			 }
			 return true;
		 }
		 return super.onKeyDown(keyCode, event);
	 }

	protected DrawerLayout getDrawerLayout() {
		return m_drawer_layout;
	}
	
	protected ActionBarDrawerToggle getDrawerToggle() {
		return m_drawer_toggle;
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}
	}
	
	public void selectItem(int position) {
		NavDrawerItem selected_item = nav_conf.getNavItems()[position];
		
		this.onNavItemSelected(selected_item.getId());
		m_drawer_list.setItemChecked(position, true);
		
		if (selected_item.updateActionBarTitle()) {
			setTitle(selected_item.getLabel());
		}
		
		if (this.m_drawer_layout.isDrawerOpen(this.m_drawer_list)) {
			m_drawer_layout.closeDrawer(m_drawer_list);
		}
	}
	
	@Override
    public void setTitle(CharSequence title) {
		m_title = title;
		getActionBar().setTitle(m_title);
	}
}
