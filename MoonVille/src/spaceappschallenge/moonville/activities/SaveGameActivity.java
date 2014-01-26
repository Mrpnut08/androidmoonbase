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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.title.setText("Save Game");
		this.new_file.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		int filecount = this.file_chooser.getListSize();
		
		if (filecount < 10) {
			try {
			GameDetails metasave = new GameDetails(this.getIntent().getStringExtra("playername"),
									this.getIntent().getIntExtra("difficulty", Difficulty.NORMAL),
									"Save-"+filecount+".sav");
			
			FileOutputStream save_output = new FileOutputStream(new File(this.getExternalFilesDir(null), metasave.getSaveFile()));
			ObjectOutputStream output_stream = new ObjectOutputStream(save_output);
			output_stream.writeObject(metasave);
			output_stream.close();
			save_output.close();
			
			this.startActivity(new Intent(this,NewGameActivity.class));
			this.finish();
			} catch (Exception err) {
				
			}
			
		} else {
			Toast.makeText(this, "Max number of save files reached",Toast.LENGTH_LONG).show();
		}
		
	}
	
	
}