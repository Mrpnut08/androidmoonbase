package spaceappschallenge.moonville.activities;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.dialogs.NewGameDialog;
import spaceappschallenge.moonville.dialogs.SettingsDialog;
import spaceappschallenge.moonville.factories.ApplicationService;
import spaceappschallenge.moonville.factories.MoonBaseManager;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends GameActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Loads the preset settings the first time.
		PreferenceManager.setDefaultValues(this, R.xml.settings, false);
		
		setContentView(R.layout.activity_mainmenu);
		
		//Retrieves the menu items from the main menu
		TextView[] menuitems = new TextView[4];
		menuitems[0] = (TextView) findViewById(R.id.mm_newgame);
		menuitems[1] = (TextView) findViewById(R.id.mm_loadgame);
		menuitems[2] = (TextView) findViewById(R.id.mm_settings);
		menuitems[3] = (TextView) findViewById(R.id.mm_credits);
		
		for(int i=0; i<4; i++) {
			menuitems[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, 
					this.getWindowManager().getDefaultDisplay().getHeight()*0.075F);
			
		}
		
		ApplicationService app = ApplicationService.getInstance();
		app.setApplicationContext(this.getApplicationContext());
	}

	// methods called by onClick property of button in xml
	public void showBaseOverviewScreen(View view) {
		MoonBaseManager.loadSavedMoonbase(view.getContext());
		if (MoonBaseManager.getCurrentMoonBase() != null) {
			view.getContext().startActivity(
					new Intent(this, BaseOverviewActivity.class));
		} else
			Toast.makeText(this, "No saved game found", Toast.LENGTH_SHORT)
					.show();
	}

	public void showNewGameScreen(View view) {
		new NewGameDialog().show(this.getSupportFragmentManager(), "NewGame");
	}

	public void showSettingsScreen(View view) {
		new SettingsDialog().show(this.getSupportFragmentManager(), "Settings");
	}
	
	public void showCreditsScreen(View view) {
		view.getContext()
				.startActivity(new Intent(this, CreditsActivity.class));
	}

}
