package spaceappschallenge.moonville.activities;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.fragments.SaveFileChooserFragment;
import spaceappschallenge.moonville.interfaces.OnSaveSelectListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public abstract class SaveFileManagerActivity extends GameActivity implements OnSaveSelectListener{

	protected TextView title;
	protected Button new_file;
	protected SaveFileChooserFragment file_chooser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_save_manager);
		
		this.title = (TextView) this.findViewById(R.id.sma_title);
		this.new_file = (Button) this.findViewById(R.id.sma_newfile);
		this.file_chooser = (SaveFileChooserFragment) this.getSupportFragmentManager().findFragmentById(R.id.sma_filelist);
		this.file_chooser.setOnSaveSelectListener(this);
	}
	
}
