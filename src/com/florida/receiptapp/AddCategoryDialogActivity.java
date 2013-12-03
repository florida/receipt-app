package com.florida.receiptapp;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.florida.receiptapp.classes.Category;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class AddCategoryDialogActivity extends Activity {
	Button create, cancel;
	Category category = null;
	EditText category_name;
	String category_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_category);
		
		Intent intent = getIntent();
		category_id = intent.getStringExtra("category_id");
		
		category_name = ((EditText) findViewById(R.id.edtxt_category_name));
		
		create = (Button) findViewById(R.id.btn_create_category);
		cancel = (Button) findViewById(R.id.btn_cancel_create_category);
		
		ButtonListener listener = new ButtonListener();
		create.setOnClickListener(listener);
		cancel.setOnClickListener(listener);
		
		
		
		if (category_id != null) {
			create.setText("Save");
			cancel.setText("Delete");
			ParseQuery<Category> query = ParseQuery.getQuery("Category");
			try {
				category = query.get(category_id);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			category_name.setText(category.getName());
		}
	}

	private class ButtonListener implements android.view.View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_create_category:
				
				ParseQuery<Category> query = ParseQuery.getQuery("Category");
				query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
				query.whereEqualTo("name", category_name.getText().toString());
				Category unique_result = null;
				try {
					unique_result = query.getFirst();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Locale.getDefault();
				if (unique_result == null || (category != null && category.getName().toLowerCase().equals(category_name.getText().toString().toLowerCase()))) {
					String message = (category  != null) ? "Category Saved" : "Category Created";

					category = (category == null) ? new Category() : category;
					
					
					Toast.makeText(AddCategoryDialogActivity.this, message, Toast.LENGTH_LONG).show();
					
					category.setName(category_name.getText().toString());
					category.setUser(ParseUser.getCurrentUser());
					if (CommonFunctions.CheckNetworkStatus(AddCategoryDialogActivity.this)) {
						category.saveInBackground();
					} else {
						category.saveEventually();
					}
					
					setResult(Activity.RESULT_OK);
					finish();
				} else {
					AlertDialog.Builder alert_dialog = new AlertDialog.Builder(AddCategoryDialogActivity.this);
					alert_dialog.setTitle("Category Already Exists...");
					alert_dialog.setMessage("Can't create duplicate categories");
					alert_dialog.setIcon(R.drawable.ic_action_navigation_cancel);
					alert_dialog.setPositiveButton("Ok", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
					alert_dialog.show();
				}
				
				
				break;
			case R.id.btn_cancel_create_category:
				if (category != null) {
					
					if (category.getName().equals("All") || category.getName().equals("Uncategorized")) {
						AlertDialog.Builder alert_dialog = new AlertDialog.Builder(AddCategoryDialogActivity.this);
						alert_dialog.setTitle("Can't delete category");
						alert_dialog.setMessage("Can't create default categories");
						alert_dialog.setIcon(R.drawable.ic_action_navigation_cancel);
						alert_dialog.setPositiveButton("Ok", new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
						});
						alert_dialog.show();
					} else {
						AlertDialog.Builder alert_dialog = new AlertDialog.Builder(AddCategoryDialogActivity.this);
						alert_dialog.setTitle("Confirm Delete...");
						alert_dialog.setMessage("Are you sure you want to delete this category?");
						alert_dialog.setIcon(R.drawable.ic_action_navigation_cancel);
						alert_dialog.setPositiveButton("Yes", new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
								
								
								Toast.makeText(AddCategoryDialogActivity.this, "Category deleted", Toast.LENGTH_LONG).show();
								if (CommonFunctions.CheckNetworkStatus(AddCategoryDialogActivity.this)) {
									category.deleteInBackground();
								} else {
									category.deleteEventually();
									
								}
								
								setResult(Activity.RESULT_OK);
								finish();
							}
						});
						
						alert_dialog.setNegativeButton("No", new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
						});
						alert_dialog.show();
					}
					
				} else {
					finish();
				}
				break;
			}			
		}
	}
}
