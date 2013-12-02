package com.florida.receiptapp;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Html;

public class AboutAppDialog extends AlertDialog {

	public AboutAppDialog(Context context) {
		super(context);
		setTitle("About The App");
		setIcon(R.drawable.ic_about);
		setMessage(Html.fromHtml("<b>Author:</b> Florida Ivanne Elago <br/>" +
				   "<b>Name:</b> Receipt App <br/><br/>" +
				   "Application allows to manage receipts by keeping a record of them in a cloud database, Receipts can be categorized and new categories can be added. Receipts can be also viewed by categories.<br/><br/>" +
				   "Application source is available online at <a href='https://github.com/floridaelago/receipt-app'>Github</a>"));
	}
}
