package spaceappschallenge.moonville.fragments;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.interfaces.OnSaveSelectListener;
import spaceappschallenge.moonville.listadapters.SaveFileAdapter;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SaveFileChooserFragment extends ListFragment{

	private OnSaveSelectListener listener;
	
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
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		this.listener.onSaveSelect((String)this.getListAdapter().getItem(position));
	}
	
	public int getListSize() {
		return this.getListAdapter().getCount();
	}
	
	public void setOnSaveSelectListener(OnSaveSelectListener listener) {
		this.listener = listener;
	}
	
}
