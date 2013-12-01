package com.florida.receiptapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.florida.receiptapp.R;
import com.florida.receiptapp.R.id;
import com.florida.receiptapp.R.layout;
import com.florida.receiptapp.classes.Receipt;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class ReceiptListAdapter extends ParseQueryAdapter<Receipt> {
//public static String category_id = null; 
	public ReceiptListAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<Receipt>() {
			public ParseQuery<Receipt> create() {
				// Here we can configure a ParseQuery to display
				// only top-rated meals.
				ParseQuery<Receipt> query = ParseQuery.getQuery("Receipt");
				return query;
			}
		});
	}
	
	public ReceiptListAdapter(Context context, final String category_id) {
		super(context, new ParseQueryAdapter.QueryFactory<Receipt>() {
			public ParseQuery<Receipt> create() {
				// Here we can configure a ParseQuery to display
				// only top-rated meals.
				ParseQuery<Receipt> query = ParseQuery.getQuery("Receipt");
				query.whereEqualTo("category_id", category_id);
				return query;
			}
		});
	}
	
	
	
	@Override
	public View getItemView(Receipt receipt, View v, ViewGroup parent) {

		if (v == null) {
			v = View.inflate(getContext(), R.layout.receipt_list_row, null);
		}

		super.getItemView(receipt, v, parent);


		TextView store_name = (TextView) v.findViewById(R.id.store_name);
		store_name.setText(receipt.getStoreName());
		TextView price = (TextView) v.findViewById(R.id.total);
		price.setText(Double.toString(receipt.getTotal()));
		
		return v;
	}

	
}
