package spaceappschallenge.moonville.listadapters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.domain.GameDetails;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SaveFileAdapter extends BaseAdapter {
	
	private String[] save_slots;
	private Context context;
	
	public SaveFileAdapter(Context context) {
		this.context = context;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			this.save_slots = this.context.getExternalFilesDir(null).list(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String filename) {
					return (filename.endsWith(".sav"));
				}
			});
			
			if(this.save_slots == null) {
				this.save_slots = new String[0];
			}
		}	else {
			this.save_slots = new String[0];
		}
		
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
		
		File save_file = new File (parent.getContext().getExternalFilesDir(null),this.save_slots[position]);
		ObjectInputStream input_stream = new ObjectInputStream(new FileInputStream(save_file));
		save_meta = (GameDetails) input_stream.readObject();
		input_stream.close();
		
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.listitem_savefile, parent, false);
		
		number = (TextView) view.findViewById(R.id.lisf_number);
		name = (TextView) view.findViewById(R.id.lisf_name);
		difficulty = (TextView) view.findViewById(R.id.lisf_difficulty);
		turn = (TextView) view.findViewById(R.id.lisf_turn);
		
		number.setText(String.valueOf(position));
		name.setText(save_meta.getUsername());
		difficulty.setText(String.valueOf(save_meta.getDifficultyLevel()));
		turn.setText("Turn " + save_meta.getTurn());
		
		return view;
		
		} catch (Exception err){
			return convertView;
		}
	}
	
}