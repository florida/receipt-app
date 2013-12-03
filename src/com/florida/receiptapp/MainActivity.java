package com.florida.receiptapp;

import android.content.Intent;
import android.os.Bundle;

import com.florida.receiptapp.fragments.CategoriesFragment;
import com.florida.receiptapp.fragments.HomeFragement;
import com.florida.receiptapp.fragments.ReceiptListFragment;
import com.florida.receiptapp.navbar.AbstractNavDrawerActivity;
import com.florida.receiptapp.navbar.NavDrawerActivityConfiguration;
import com.florida.receiptapp.navbar.NavDrawerAdapter;
import com.florida.receiptapp.navbar.NavDrawerItem;
import com.florida.receiptapp.navbar.NavMenuItem;
import com.florida.receiptapp.navbar.NavMenuSection;

public class MainActivity extends AbstractNavDrawerActivity {
	public static HomeFragement home_fragment = null;
	public static ReceiptListFragment receipt_list_fragment = null;
	public static CategoriesFragment categories_fragment = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        if (findViewById(R.id.frame_content) != null) {
        	if ( savedInstanceState != null ) { return ; }
        	
        	home_fragment = new HomeFragement();
        	getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, home_fragment).commit();        	
        }
	}
	
	@Override
    protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
        
        NavDrawerItem[] menu = new NavDrawerItem[] {
                NavMenuSection.create(100, "Receipts"),
                NavMenuItem.create(101,"Home", "ic_launcher", true, this),
                NavMenuItem.create(102, "Add Receipt", "ic_receipt_add", true, this),
                NavMenuItem.create(103, "Receipts", "ic_receipts", true, this), 
                NavMenuItem.create(104, "Add Category", "ic_category_add", true, this), 
                NavMenuItem.create(105, "Categories", "ic_categories", true, this), 
                NavMenuSection.create(200, "App"),
                NavMenuItem.create(201, "Preferences", "ic_settings", false, this),
                NavMenuItem.create(202, "About", "ic_about", false, this),
                NavMenuItem.create(203, "Quit", "ic_quit", false, this)};
        
        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
        navDrawerActivityConfiguration.setMainLayout(R.layout.activity_main);
        navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
        navDrawerActivityConfiguration.setLeftDrawerId(R.id.listview_drawer_left);
        navDrawerActivityConfiguration.setNavItems(menu);
        navDrawerActivityConfiguration.setDrawerShadow(R.drawable.drawer_shadow);       
        navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
        navDrawerActivityConfiguration.setDrawerCloseDesc(R.string.drawer_close);
        navDrawerActivityConfiguration.setBaseAdapter(
            new NavDrawerAdapter(this, R.layout.navdrawer_item, menu ));
        return navDrawerActivityConfiguration;
    }
    
    @Override
    protected void onNavItemSelected(int id) {
        switch ((int)id) {
        case 101:
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, home_fragment).addToBackStack(null).commit();
            break;
        case 102:
        	Intent receipt_intent = new Intent(this, AddReceiptActivity.class);
        	startActivityForResult(receipt_intent, ReceiptListFragment.REQUEST_CODE_STANDARD);
            break;
        case 103:
        	if (receipt_list_fragment == null) {
        		receipt_list_fragment = new ReceiptListFragment();
        	}
        	getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, receipt_list_fragment).addToBackStack(null).commit();
            break;
            
        case 104:
        	Intent categories_intent = new Intent(this, AddCategoryDialogActivity.class);
        	startActivityForResult(categories_intent, CategoriesFragment.REQUEST_CODE_STANDARD);
            break;
        case 105:
        	if (categories_fragment == null) {
        		categories_fragment = new CategoriesFragment();
        	}
        	getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, categories_fragment).addToBackStack(null).commit();
            break;
        case 201:
        	Intent settings_intent = new Intent(this, SettingsActivity.class);
        	startActivityForResult(settings_intent, 1);
        	break;
        	
        case 202:
        	(new AboutAppDialog(this)).show();
        	break;
        case 203:
        	System.exit(0);
        }
        
    }
}
