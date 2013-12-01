package com.florida.receiptapp;

import android.content.Intent;
import android.os.Bundle;

import com.florida.receiptapp.classes.Category;
import com.florida.receiptapp.classes.Receipt;
import com.florida.receiptapp.fragments.CategoriesFragment;
import com.florida.receiptapp.fragments.HomeFragement;
import com.florida.receiptapp.fragments.ReceiptListFragment;
import com.florida.receiptapp.navbar.AbstractNavDrawerActivity;
import com.florida.receiptapp.navbar.NavDrawerActivityConfiguration;
import com.florida.receiptapp.navbar.NavDrawerAdapter;
import com.florida.receiptapp.navbar.NavDrawerItem;
import com.florida.receiptapp.navbar.NavMenuItem;
import com.florida.receiptapp.navbar.NavMenuSection;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends AbstractNavDrawerActivity {
	public static HomeFragement home_fragment = null;
	public static ReceiptListFragment receipt_list_fragment = null;
	public static CategoriesFragment categories_fragment = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ParseObject.registerSubclass(Receipt.class);
		ParseObject.registerSubclass(Category.class);
		Parse.initialize(this, "uAYqM3b7kLiNMn3ly9rLmZS3IeaZA5aRzRavtyto", "s6Gal3CStk0fOVD1AOButBPenI2SNU1MruMbJNkq"); 
		ParseUser.enableAutomaticUser();        
        

        /*
         * For more information on app security and Parse ACL:
         * https://www.parse.com/docs/android_guide#security-recommendations
         */
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
        
        if (findViewById(R.id.content_frame) != null) {
        	if ( savedInstanceState != null ) { return ; }
        	
        	home_fragment = new HomeFragement();
        	getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, home_fragment).commit();        	
        }
	}
	
	@Override
    protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
        
        NavDrawerItem[] menu = new NavDrawerItem[] {
                NavMenuSection.create(100, "Receipts"),
                NavMenuItem.create(101,"Home", "", true, this),
                NavMenuItem.create(102, "Add Receipt", "", true, this),
                NavMenuItem.create(103, "Receipts", "", true, this), 
                NavMenuItem.create(104, "Add Category", "", true, this), 
                NavMenuItem.create(105, "Categories", "", true, this), 
                NavMenuSection.create(300, "App"),
                NavMenuItem.create(203, "About", "", false, this),
                NavMenuItem.create(205, "Quit", "", false, this)};
        
        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
        navDrawerActivityConfiguration.setMainLayout(R.layout.activity_main);
        navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
        navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
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
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, home_fragment).addToBackStack(null).commit();
            break;
        case 102:
        	Intent intent = new Intent(this, AddReceiptActivity.class);
        	startActivity(intent);
            break;
        case 103:
        	if (receipt_list_fragment == null) {
        		receipt_list_fragment = new ReceiptListFragment();
        	}
        	getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, receipt_list_fragment).addToBackStack(null).commit();
            break;
            
        case 104:
        	Intent i = new Intent(this, AddCategoryDialogActivity.class);
        	startActivity(i);
            break;
        case 105:
        	if (categories_fragment == null) {
        		categories_fragment = new CategoriesFragment();
        	}
        	getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, categories_fragment).addToBackStack(null).commit();
            break;
        }
        
    }
}
