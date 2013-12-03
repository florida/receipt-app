package com.florida.receiptapp;

import com.florida.receiptapp.classes.Category;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Spinner;


public class CommonFunctions {
	
	public static boolean CheckNetworkStatus(Context context) {	
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return  (netInfo != null && netInfo.isConnectedOrConnecting());
	}
	
	public static int getIndex(Spinner spinner, String string) {	
		int index = 0;
	
		for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
			if (((Category) spinner.getItemAtPosition(i)).getObjectId().equals(string)) {
				index = i;
			}
		}
		return index;
	}
}
