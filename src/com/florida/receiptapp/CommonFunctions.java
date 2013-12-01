package com.florida.receiptapp;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class CommonFunctions {
	
	public boolean CheckNetworkStatus(Context context) {	
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return  (netInfo != null && netInfo.isConnectedOrConnecting());
	}
}
