package com.florida.receiptapp.fragments;

import com.florida.receiptapp.AddCategoryDialogActivity;
import com.florida.receiptapp.R;
import com.florida.receiptapp.adapters.CategoryAdapter;
import com.florida.receiptapp.classes.Category;

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
import android.widget.Button;
import android.widget.ListView;

public class CategoriesFragment extends Fragment {
	CategoryAdapter adapter;
	public static final int REQUEST_CODE_STANDARD = 1;
	ListView lv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_category, container, false);
		
		Button btn_add_category = (Button) view.findViewById(R.id.btn_add_category);
		btn_add_category.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), AddCategoryDialogActivity.class);
				startActivityForResult(intent, REQUEST_CODE_STANDARD);
			}
		});
		ListView listview = (ListView) view.findViewById(R.id.listview_category);
		lv = listview;
		adapter = new CategoryAdapter(getActivity());
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				String category_id = ((Category) parent.getItemAtPosition(position)).getObjectId();
				Intent intent = new Intent(getActivity(), AddCategoryDialogActivity.class);
				intent.putExtra("category_id", category_id);
				startActivityForResult(intent, REQUEST_CODE_STANDARD);
			}
		
		});
		
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		lv.setBackgroundColor(settings.getInt((String) getResources().getText(R.string.pref_key_category_background_color), Color.TRANSPARENT));
		
		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		lv.setBackgroundColor(settings.getInt((String) getResources().getText(R.string.pref_key_category_background_color), Color.TRANSPARENT));
		adapter.loadObjects();
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
