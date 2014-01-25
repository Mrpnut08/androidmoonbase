package spaceappschallenge.moonville.activities;

import java.io.File;

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
		
		if (filecount < 5) {
			File savedir = new File(this.getExternalFilesDir(null),"SaveSlot-"+filecount+File.separatorChar);
			savedir.mkdirs();
			this.startActivity(new Intent(this,NewGameActivity.class));
		} else {
			Toast.makeText(this, "Max number of save files reached",Toast.LENGTH_LONG).show();
		}
		
	}
	
	
}