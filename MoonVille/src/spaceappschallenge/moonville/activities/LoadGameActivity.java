package spaceappschallenge.moonville.activities;

import spaceappschallenge.moonville.domain.GameDetails;
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
	public void onSaveSelect(GameDetails save) {
		
		Class<? extends GameActivity> activity;
		activity= (save.getTurn() <0)? BaseOverviewActivity.class : NewGameActivity.class;
		
		Intent intent = new Intent (this, activity);
		
		intent.putExtra("savefile", save.getSaveFile());
		
		this.startActivity(intent);
		this.finish();
	}
}
