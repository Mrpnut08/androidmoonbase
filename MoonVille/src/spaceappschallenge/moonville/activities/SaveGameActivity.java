package spaceappschallenge.moonville.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import spaceappschallenge.moonville.domain.Difficulty;
import spaceappschallenge.moonville.domain.GameDetails;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SaveGameActivity extends SaveFileManagerActivity implements OnClickListener{

	private GameDetails metasave;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.title.setText("Save Game");
		this.new_file.setOnClickListener(this);
		
		this.metasave = new GameDetails(this.getIntent().getStringExtra("playername"),
										this.getIntent().getIntExtra("difficulty", Difficulty.DIF_MED));
	}

	@Override
	public void onClick(View v) {
		
		int filecount = this.file_chooser.getListSize();
		
		if (filecount < 10) {
			try {
			File save_file = new File(this.getExternalFilesDir(null),"Save-"+filecount+".sav");
			ObjectOutputStream output_stream = new ObjectOutputStream(new FileOutputStream(save_file));
			output_stream.writeObject(this.metasave);
			output_stream.close();
			
			this.startActivity(new Intent(this,NewGameActivity.class));
			} catch (Exception err) {
				
			}
			
		} else {
			Toast.makeText(this, "Max number of save files reached",Toast.LENGTH_LONG).show();
		}
		
	}
	
	
}