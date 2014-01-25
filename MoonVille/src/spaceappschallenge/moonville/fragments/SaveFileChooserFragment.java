package spaceappschallenge.moonville.fragments;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.listadapters.SaveFileAdapter;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SaveFileChooserFragment extends ListFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_savefile_chooser, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		this.setListAdapter(new SaveFileAdapter(this.getActivity()));
	}
	
	public int getListSize() {
		return this.getListAdapter().getCount();
	}
}
