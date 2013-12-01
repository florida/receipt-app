package com.florida.receiptapp.fragments;

import java.util.ArrayList;

import com.florida.receiptapp.AddReceiptActivity;
import com.florida.receiptapp.MainActivity;
import com.florida.receiptapp.R;
import com.florida.receiptapp.R.drawable;
import com.florida.receiptapp.R.id;
import com.florida.receiptapp.R.layout;
import com.florida.receiptapp.adapters.HomeNavGridAdapter;
import com.florida.receiptapp.classes.GridItem;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class HomeFragement extends Fragment {
	GridView grid_view;
	ArrayList<GridItem> grid_array;
	HomeNavGridAdapter grid_adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_fragment, container, false);
		
		Bitmap camera = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_action_camera);
		Bitmap receipts = BitmapFactory.decodeResource(this.getResources(), R.drawable.receipts);
		Bitmap receipt_add = BitmapFactory.decodeResource(this.getResources(), R.drawable.receipt_add);
		// Bitmap receipts = BitmapFactory.decodeResource(this.getResources(), R.drawable.receipts);
		// Bitmap receipt_add = BitmapFactory.decodeResource(this.getResources(), R.drawable.receipt_add);
		
		grid_array = new ArrayList<GridItem>();
		grid_array.add(new GridItem(receipt_add, "Add Receipt"));
		grid_array.add(new GridItem(receipts, " Receipts"));
		grid_array.add(new GridItem(receipt_add, "Add Category"));
		grid_array.add(new GridItem(receipts, "Categories"));
		grid_array.add(new GridItem(receipt_add, "Settings"));
		grid_array.add(new GridItem(receipt_add, "About App"));
		grid_array.add(new GridItem(receipts, " Quit"));
		
		grid_view = (GridView) view.findViewById(R.id.gridView1);
		grid_adapter = new HomeNavGridAdapter(getActivity(), R.layout.home_row_grid, grid_array);
		grid_view.setAdapter(grid_adapter);
		
		grid_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				switch (position) {
				case 0:
					 //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new CameraFragment()).addToBackStack(null).commit();
					break;
				case 1:
					Intent intent = new Intent(getActivity(), AddReceiptActivity.class);
					startActivity(intent);
					break;
				case 2:
					ReceiptListFragment receipt_list_fragment = null;
					if (MainActivity.receipt_list_fragment == null) {
						receipt_list_fragment = new ReceiptListFragment();
						MainActivity.receipt_list_fragment = receipt_list_fragment;
					} else {
						receipt_list_fragment = MainActivity.receipt_list_fragment;
					}
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, receipt_list_fragment).addToBackStack(null).commit();
					break;	
				}
			}
		});
		return view;
	}
}
