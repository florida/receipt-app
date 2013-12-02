package com.florida.receiptapp.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.florida.receiptapp.AboutAppDialog;
import com.florida.receiptapp.AddCategoryDialogActivity;
import com.florida.receiptapp.AddReceiptActivity;
import com.florida.receiptapp.MainActivity;
import com.florida.receiptapp.R;
import com.florida.receiptapp.adapters.HomeNavGridAdapter;
import com.florida.receiptapp.classes.GridItem;

public class HomeFragement extends Fragment {
	GridView grid_view;
	ArrayList<GridItem> grid_array;
	HomeNavGridAdapter grid_adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		// Bitmap receipt_add = BitmapFactory.decodeResource(this.getResources(), R.drawable.receipt_add);
		
		grid_array = new ArrayList<GridItem>();
		grid_array.add(new GridItem("ic_receipt_add", "Add Receipt", getActivity()));
		grid_array.add(new GridItem("ic_receipts", " Receipts", getActivity()));
		grid_array.add(new GridItem("ic_category_add", "Add Category", getActivity()));
		grid_array.add(new GridItem("ic_categories", "Categories", getActivity()));
		grid_array.add(new GridItem("ic_settings", "Settings", getActivity()));
		grid_array.add(new GridItem("ic_about", "About App", getActivity()));
		grid_array.add(new GridItem("ic_quit", " Quit", getActivity()));
		
		grid_view = (GridView) view.findViewById(R.id.gridView1);
		grid_adapter = new HomeNavGridAdapter(getActivity(), R.layout.home_row_grid, grid_array);
		grid_view.setAdapter(grid_adapter);
		
		grid_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				switch (position) {
				case 0: 
					Intent intent = new Intent(getActivity(), AddReceiptActivity.class);
					startActivity(intent);
					break;
				case 1:
					ReceiptListFragment receipt_list_fragment = null;
					if (MainActivity.receipt_list_fragment == null) {
						receipt_list_fragment = new ReceiptListFragment();
						MainActivity.receipt_list_fragment = receipt_list_fragment;
					} else { receipt_list_fragment = MainActivity.receipt_list_fragment; }
					
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, receipt_list_fragment).addToBackStack(null).commit();
					break;
				case 2:
					Intent i = new Intent(getActivity(), AddCategoryDialogActivity.class);
		        	startActivity(i);
					break;
				case 3:
					CategoriesFragment categories_fragment = null;
					if (MainActivity.categories_fragment == null) {
						categories_fragment = new CategoriesFragment();
						MainActivity.categories_fragment = categories_fragment;
					} else { categories_fragment = MainActivity.categories_fragment; } 
					getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, categories_fragment).addToBackStack(null).commit();
					break;
				case 4: 
					
					break;
				case 5:
					(new AboutAppDialog(getActivity())).show();
					break;
				case 6: 
					System.exit(0);
					break;
				}
			
			}
		});
		return view;
	}
}
