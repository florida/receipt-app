package com.florida.receiptapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.florida.receiptapp.classes.Category;
import com.parse.ParseException;
import com.parse.ParseQuery;

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
		
		create = (Button) findViewById(R.id.create_category);
		cancel = (Button) findViewById(R.id.cancel_create_category);
		
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_category_dialog, menu);
		return true;
	}

	private class ButtonListener implements android.view.View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.create_category:
				if (category == null) {
					category = new Category();
				}
				category.setName(category_name.getText().toString());
				category.saveInBackground();
				setResult(Activity.RESULT_OK);
				finish();
				break;
			case R.id.cancel_create_category:
				if (category != null) {
					AlertDialog.Builder alert_dialog = new AlertDialog.Builder(AddCategoryDialogActivity.this);
					alert_dialog.setTitle("Confirm Delete...");
					alert_dialog.setMessage("Are you sure you want to delete this category?");
					alert_dialog.setIcon(R.drawable.ic_action_navigation_cancel);
					alert_dialog.setPositiveButton("Yes", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(AddCategoryDialogActivity.this, "Category deleted", Toast.LENGTH_LONG).show();
							category.deleteInBackground();
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
					
				} else {
					finish();
				}
				break;
			}			
		}
	}
}
