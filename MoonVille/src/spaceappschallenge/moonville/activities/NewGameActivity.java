/*
 * Starts the game. Initializes managers based on user choices.
 */
package spaceappschallenge.moonville.activities;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.domain.GameDetails;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewGameActivity extends GameActivity {
	protected GameDetails gameDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);
				
	}

	public void startNewGame(View view) {
		
		Intent intent = new Intent(this.getIntent());
		intent.setClass(this, InitialLaunchActivity.class);
		
		this.startActivity(intent);	
		this.finish();
	}
}
