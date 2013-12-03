package com.florida.receiptapp.fragments;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.florida.receiptapp.AddReceiptActivity;
import com.florida.receiptapp.CommonFunctions;
import com.florida.receiptapp.R;
import com.florida.receiptapp.adapters.ReceiptListAdapter;
import com.florida.receiptapp.classes.Category;
import com.florida.receiptapp.classes.Receipt;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.ParseQueryAdapter.OnQueryLoadListener;

public class ReceiptListFragment extends Fragment {
	public static final int REQUEST_CODE_STANDARD = 1;
	ParseQueryAdapter<Category> category_adapter;
	ReceiptListAdapter adapter;
	ListView lv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_receipts, container, false);
		
		final ListView listview = (ListView) view.findViewById(R.id.listview_receipt);
		lv = listview;
		Button btn_add_receipt = (Button) view.findViewById(R.id.btn_add_receipt);
		btn_add_receipt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), AddReceiptActivity.class);
				startActivityForResult(intent, REQUEST_CODE_STANDARD);
			}
		});
		
		category_adapter = new ParseQueryAdapter<Category>(getActivity(), new ParseQueryAdapter.QueryFactory<Category>() {
			public ParseQuery<Category> create() {
				ParseQuery<Category> query = ParseQuery.getQuery("Category");
				query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
				query.whereEqualTo("user", ParseUser.getCurrentUser());
				return query;
			}
		});
		category_adapter.setTextKey("name");
		category_adapter.setPaginationEnabled(false);
		final Spinner spn_category = (Spinner) view.findViewById(R.id.spn_receipt_categories);
		
		spn_category.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String category_name = ((Category) parent.getItemAtPosition(position)).getName();
				String category_id = ((Category) parent.getItemAtPosition(position)).getObjectId();
				
				if (category_name.equals("All")) {
					adapter = new ReceiptListAdapter(getActivity());
				} else {
					adapter = new ReceiptListAdapter(getActivity(), category_id);
				}
				
				listview.setAdapter(adapter);
				adapter.loadObjects();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {			
			}
			
		});
		listview.setAdapter(adapter);
		spn_category.setAdapter(category_adapter);
		
		category_adapter.addOnQueryLoadListener(new OnQueryLoadListener<Category>() {

			@Override
			public void onLoaded(List<Category> arg0, Exception arg1) {
					SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
					String default_settings = settings.getString((String) getResources().getText(R.string.pref_key_receipt_list_default_sort), "all");
					spn_category.setSelection(CommonFunctions.getIndex(spn_category, default_settings));
			}

			@Override
			public void onLoading() {
			}
		 });
		
		adapter = new ReceiptListAdapter(getActivity());
		
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				String receipt_id = ((Receipt) parent.getItemAtPosition(position)).getObjectId();
				
				Intent intent = new Intent(getActivity(), AddReceiptActivity.class);
				
				intent.putExtra("receipt_id", receipt_id);

				startActivityForResult(intent, REQUEST_CODE_STANDARD);
			}
		});
		
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		listview.setBackgroundColor(settings.getInt((String) getResources().getText(R.string.pref_key_receipts_background_color), Color.TRANSPARENT));
		return view;
	}
	
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		lv.setBackgroundColor(settings.getInt((String) getResources().getText(R.string.pref_key_receipts_background_color), Color.TRANSPARENT));
		adapter.loadObjects();
		category_adapter.loadObjects();
		super.onResume();
		
	}



	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == REQUEST_CODE_STANDARD) {
			if (resultCode == Activity.RESULT_OK) {
				adapter.loadObjects();
			}
		}
	}
	
	
}
