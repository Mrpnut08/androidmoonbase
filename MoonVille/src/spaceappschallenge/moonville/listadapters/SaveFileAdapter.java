package spaceappschallenge.moonville.listadapters;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.domain.GameDetails;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SaveFileAdapter extends BaseAdapter {
	
	private File[] save_slots;
	private Context context;
	
	public SaveFileAdapter(Context context) {
		this.context = context;
		this.save_slots = this.context.getExternalFilesDir(null).listFiles();
	}

	@Override
	public int getCount() {
		return this.save_slots.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		try {
		TextView number,name,difficulty,turn;
		GameDetails save_meta;
		
		ObjectInputStream input_stream = new ObjectInputStream(new FileInputStream(this.save_slots[position]));
		save_meta = (GameDetails) input_stream.readObject();
		input_stream.close();
		
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		convertView = inflater.inflate(R.layout.listitem_savefile, parent, false);
		
		number = (TextView) convertView.findViewById(R.id.lisf_number);
		name = (TextView) convertView.findViewById(R.id.lisf_name);
		difficulty = (TextView) convertView.findViewById(R.id.lisf_difficulty);
		turn = (TextView) convertView.findViewById(R.id.lisf_turn);
		
		number.setText(String.valueOf(position));
		name.setText(this.save_slots[position].getName());
		difficulty.setText(save_meta.getDifficultyLevel());
		return convertView;
		} catch (Exception err){
			return null;
		}
	}
	
}