package spaceappschallenge.moonville.listadapters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.domain.Difficulty;
import spaceappschallenge.moonville.domain.GameDetails;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SaveFileAdapter extends BaseAdapter {
	
	private ArrayList<GameDetails> saves; ///< Stores list of the game detail of usable saves that were found in the device.
	
	private Context context;
	
	public SaveFileAdapter(Context context) {
		
		//Store context for future updates.
		this.context = context;
		
		//Initialize Arraylist.
		this.saves = new ArrayList<GameDetails>();
		
		//update the save list.
		this.updateList();
	}

	@Override
	public int getCount() {
		return this.saves.size();
	}
	
	@Override
	public Object getItem(int position) {
		return this.saves.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//Get the corresponding GameDetails Object from the array.
		GameDetails save = this.saves.get(position);
		
		//Inflate the listitem_savefile layout.
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		convertView = inflater.inflate(R.layout.listitem_savefile, parent, false);
		
		//Extract the TextViews from the inflated layout.
		TextView number,name,difficulty,turn;
		number = (TextView) convertView.findViewById(R.id.lisf_number);
		name = (TextView) convertView.findViewById(R.id.lisf_name);
		difficulty = (TextView) convertView.findViewById(R.id.lisf_difficulty);
		turn = (TextView) convertView.findViewById(R.id.lisf_turn);
		
		//Translate the difficulty value to its name.
		String difficulty_name = "";
		switch(save.getDifficultyLevel()) {
			case Difficulty.EASY:
				difficulty_name = this.context.getString(R.string.difficulty_easy);
				break;
				
			case Difficulty.NORMAL:
				difficulty_name = this.context.getString(R.string.difficulty_normal);
				break;
				
			case Difficulty.HARD:
				difficulty_name = this.context.getString(R.string.difficulty_hard);
				break;
		}
		
		//Set the corresponding text to each view.
		number.setText(String.valueOf(position));
		name.setText(save.getPlayerName());
		difficulty.setText(difficulty_name);
		turn.setText("Turn " + save.getTurn());
		
		//return the finished view.
		return convertView;
		
	}
	
	/**
	 * Searches in the application external directory for savefiles and updates the 
	 * adapter with it.
	 */
	private void updateList() {
		
		//Clear ArrayList.
		this.saves.clear();
		
		//Return if Eternal media is not mounted.
		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			this.notifyDataSetChanged();
			return;
		}
		
		//Get list of ".sav" files
		FilenameFilter name_filter = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".sav");
			}
		}; // ~FilenameFilter name_filter = new FilenameFilter()
		
		String[] save_files = this.context.getExternalFilesDir(null).list(name_filter);
				
		//Obtain the GameDetail of each save file.
		FileInputStream file_input;
		ObjectInputStream save_input;
		
		for (String file : save_files) {
			try {
				//Open the file for reading.
				file_input= new FileInputStream(new File (this.context.getExternalFilesDir(null),file));
				save_input = new ObjectInputStream(file_input);
				
				//Read the First object (GameDetailObject) in the class)
				this.saves.add((GameDetails) save_input.readObject());
				
				//Close the streams
				save_input.close();
				file_input.close();
			
			//Log the cause of failure in case of error.
			} catch (Exception err) {
				Log.d("save manager", "Error reading "+file+": ("+err.getClass().getSimpleName()+")"+err.getMessage());
			}
		}// ~for (String file : save_files)
		
		//Notify attached views to update with the new data.
		this.notifyDataSetChanged();
	}
}