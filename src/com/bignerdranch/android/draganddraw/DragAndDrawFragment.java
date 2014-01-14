package com.bignerdranch.android.draganddraw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DragAndDrawFragment extends Fragment {
	private int viewID;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_drag_and_draw, parent, false);
		if (viewID > 0) viewID++;								//Assign view ID in code
		else		    viewID = 1;			
		view.setId(viewID);										//View must have ID to save instance state
		return view;
	}
}
