package spaceappschallenge.moonville.fragments;

import spaceappschallenge.moonville.R;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResourceListFragment extends ListFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_resource_list, container);
		return view; 
	}
}