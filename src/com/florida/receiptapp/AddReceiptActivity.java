package com.florida.receiptapp;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.OnQueryLoadListener;

public class AddReceiptActivity extends Activity {
	private Receipt receipt = null;
	private ParseQueryAdapter<Category> categoryadapter;
	private Spinner category_spinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_add_receipt);
		 
		 Intent intent = getIntent();
		 
		 categoryadapter = new ParseQueryAdapter<Category>(this, Category.class);
		 categoryadapter.setTextKey("name");
		 
		 category_spinner = (Spinner) findViewById(R.id.spn_category);
		 
		 category_spinner.setAdapter(categoryadapter);
		 
		 String receipt_id = intent.getStringExtra("receipt_id");
		 
		 if (receipt_id == null) {
			 category_spinner.setSelection(-1);
		 } else {
			 ParseQuery<Receipt> query = ParseQuery.getQuery("Receipt");
			 try {
				receipt = query.get(receipt_id);
			 } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			
			 
			 setEditTextString(R.id.edtxt_store_name, receipt.getStoreName());
			 setEditTextString(R.id.edtxt_total, String.valueOf(receipt.getTotal()));
			 setEditTextString(R.id.edtxt_sub_total, String.valueOf(receipt.getSubTotal()));
			 setEditTextString(R.id.edtxt_gst, String.valueOf(receipt.getGst()));
			 setEditTextString(R.id.edtxt_pst, String.valueOf(receipt.getPst()));
			 
			 categoryadapter.addOnQueryLoadListener(new OnQueryLoadListener<Category>() {

				@Override
				public void onLoaded(List<Category> arg0, Exception arg1) {
					// TODO Auto-generated method stub
					
					Toast.makeText(AddReceiptActivity.this, String.valueOf(categoryadapter.getCount()), Toast.LENGTH_LONG).show();
					
					category_spinner.setSelection(getIndex(category_spinner, receipt.getCategoryId()));
				}

				@Override
				public void onLoading() {
					// TODO Auto-generated method stub
					
				}
			 });
			 
			 
		 }

	}
	
	private int getIndex(Spinner spinner, String string) {
		
		int index = 0;
		String x = receipt.getCategoryId();
		
		
		for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
			
			
			if (((Category) spinner.getItemAtPosition(i)).getObjectId().equals(string)) {
				index = i;
			}
		}
		
		return index;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_receipt, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_receipt:
			if (receipt == null) {
				receipt = new Receipt();
			} 
			
			receipt.setStoreName(getEditTextString(R.id.edtxt_store_name));
			receipt.setTotal(getEditTextFloat(R.id.edtxt_total));
			receipt.setSubTotal(getEditTextFloat(R.id.edtxt_sub_total));
			receipt.setGst(getEditTextFloat(R.id.edtxt_gst));
			receipt.setPst(getEditTextFloat(R.id.edtxt_pst));
			receipt.setCategoryId(((Category) category_spinner.getSelectedItem()).getObjectId());
			receipt.saveInBackground();
			
			setResult(Activity.RESULT_OK);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public Receipt getCurrentReceipt() {
		return receipt;
	}
	
	public String getEditTextString(int id) {
		return ((EditText) findViewById(id)).getText().toString();
	}
	
	private Float getEditTextFloat(int id) {
		String value = ((EditText) findViewById(id)).getText().toString();
		Float float_value = (float) 0.0;
		
		if (value != null) {
			float_value = Float.parseFloat(value);
		}
		
		return float_value;
	}
	
	public void setEditTextString(int id, String string) {
		((EditText) findViewById(id)).setText(string);
	}
	

}
