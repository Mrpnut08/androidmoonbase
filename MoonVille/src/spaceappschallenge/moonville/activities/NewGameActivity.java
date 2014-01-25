/*
 * Starts the game. Initializes managers based on user choices.
 */
package spaceappschallenge.moonville.activities;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.domain.Difficulty;
import spaceappschallenge.moonville.domain.GameDetails;
import spaceappschallenge.moonville.factories.MoonBaseManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;

public class NewGameActivity extends GameActivity {
	protected GameDetails gameDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);
				
	}

	public void startNewGame(View view) {
		initModel();
		showInitialLaunchScreen(view);
		//showBaseOverviewScreen(view);
	}

	protected void initModel() {
		// Dummy models

		Difficulty diff = new Difficulty(gameDetails.getDifficultyLevel());

		MoonBaseManager.createNewMoonBase(diff, this);
		// MoonBase should become a singleton I think, best way to make it
		// easily accessible -Jos
	}

	public void showInitialLaunchScreen(View view){
		view.getContext().startActivity(new Intent(this,InitialLaunchActivity.class));
		this.finish();
	}
	// I changed the init method to this one, which is triggered by the onClick
	// property in the xml of the button
	// since we are using separate activities, this is a very easy way to
	// implement navigation IMO.
	public void showBaseOverviewScreen(View view) {
		view.getContext().startActivity(
				new Intent(this, BaseOverviewActivity.class));
		this.finish();
	}
}
