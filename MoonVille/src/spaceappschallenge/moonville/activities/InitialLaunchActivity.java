package spaceappschallenge.moonville.activities;

import spaceappschallenge.moonville.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.support.v4.app.NavUtils;

public class InitialLaunchActivity extends GameActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_initial_launch);
	}

	public void showBaseOverviewScreen(View view) {
		view.getContext().startActivity(
				new Intent(this, BaseOverviewActivity.class));
		this.finish();
	}

}
