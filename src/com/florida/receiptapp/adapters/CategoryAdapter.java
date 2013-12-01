package com.florida.receiptapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.florida.receiptapp.R;
import com.florida.receiptapp.R.id;
import com.florida.receiptapp.R.layout;
import com.florida.receiptapp.classes.Category;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class CategoryAdapter extends ParseQueryAdapter<Category>{
	
	public CategoryAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<Category>() {
			public ParseQuery<Category> create() {
				// Here we can configure a ParseQuery to display
				// only top-rated meals.
				ParseQuery query = ParseQuery.getQuery("Category");
				return query;
			}
		});
	}
	
	@Override
	public View getItemView(Category category, View v, ViewGroup parent) {

		if (v == null) {
			v = View.inflate(getContext(), R.layout.category_list_row, null);
		}

		super.getItemView(category, v, parent);


		TextView store_name = (TextView) v.findViewById(R.id.category_name);
		store_name.setText(category.getName());
		
		return v;
	}
}