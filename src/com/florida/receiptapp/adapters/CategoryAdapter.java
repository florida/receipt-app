package com.florida.receiptapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.florida.receiptapp.R;
import com.florida.receiptapp.classes.Category;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class CategoryAdapter extends ParseQueryAdapter<Category>{
	
	public CategoryAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<Category>() {
			public ParseQuery<Category> create() {
				// Here we can configure a ParseQuery to display
				// only top-rated meals.
				ParseQuery<Category> query = ParseQuery.getQuery("Category");
				query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
				query.whereEqualTo("user", ParseUser.getCurrentUser());
				return query;
			}
		});
	}
	
	@Override
	public View getItemView(Category category, View v, ViewGroup parent) {

		if (v == null) {
			v = View.inflate(getContext(), R.layout.row_category, null);
		}

		super.getItemView(category, v, parent);


		TextView store_name = (TextView) v.findViewById(R.id.category_name);
		store_name.setText(category.getName());
		
		return v;
	}
}
