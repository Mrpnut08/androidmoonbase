package spaceappschallenge.moonville.activities;

import android.os.Bundle;
import android.widget.Button;

public class LoadGameActivity extends SaveFileManagerActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.title.setText("Load Game");
		this.new_file.setEnabled(false);
		this.new_file.setVisibility(Button.INVISIBLE);
	}
}
