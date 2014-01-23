package spaceappschallenge.moonville.activities;

import android.os.Bundle;

public class LoadGameActivity extends SaveFileManagerActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.title.setText("Load Game");
		this.new_file.setEnabled(false);;
	}
}
