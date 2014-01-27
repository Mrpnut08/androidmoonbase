package spaceappschallenge.moonville.activities;

import android.content.Intent;
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

	@Override
	public void onSaveSelect(String filename) {
		Intent intent = new Intent (this, BaseOverviewActivity.class);
		intent.putExtra("savefile", filename);
		this.startActivity(intent);
		this.finish();
	}
}
