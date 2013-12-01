package com.florida.receiptapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import com.florida.receiptapp.AddReceiptActivity;
import com.florida.receiptapp.R;
import com.florida.receiptapp.R.id;
import com.florida.receiptapp.R.layout;
import com.florida.receiptapp.adapters.ReceiptListAdapter;
import com.florida.receiptapp.classes.Category;
import com.florida.receiptapp.classes.Receipt;
import com.parse.ParseQueryAdapter;

public class ReceiptListFragment extends Fragment {
	public static final int REQUEST_CODE_STANDARD = 1;
	ParseQueryAdapter<Category> category_adapter;
	ReceiptListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.receipt_list_view, container, false);
		
		final ListView listview = (ListView) view.findViewById(R.id.receipt_listview);
		
		Button btn_add_receipt = (Button) view.findViewById(R.id.btn_add_receipt);
		btn_add_receipt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), AddReceiptActivity.class);
				startActivityForResult(intent, REQUEST_CODE_STANDARD);
			}
		});
		
		category_adapter = new ParseQueryAdapter<Category>(getActivity(), Category.class);
		category_adapter.setTextKey("name");
		category_adapter.setPaginationEnabled(false);
		Spinner spn_category = (Spinner) view.findViewById(R.id.receipt_categories);
		
		spn_category.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String category_id = ((Category) parent.getItemAtPosition(position)).getObjectId();
				
				
				if (category_id.equals("H9MEsdbCZT")) {
					adapter = new ReceiptListAdapter(getActivity());
				} else {
					adapter = new ReceiptListAdapter(getActivity(), category_id);
				}
				
				listview.setAdapter(adapter);
				adapter.loadObjects();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		spn_category.setAdapter(category_adapter);
		
		adapter = new ReceiptListAdapter(getActivity());
		
		listview.setAdapter(adapter);
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
		
		return view;
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
