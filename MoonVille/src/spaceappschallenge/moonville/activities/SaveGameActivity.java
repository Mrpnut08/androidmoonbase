package spaceappschallenge.moonville.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import spaceappschallenge.moonville.domain.Difficulty;
import spaceappschallenge.moonville.domain.GameDetails;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SaveGameActivity extends SaveFileManagerActivity implements
		OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.title.setText("Save Game");
		this.new_file.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		int filecount = this.file_chooser.getListSize();
		;

		if (filecount < 10) {

			this.save("Save-" + filecount + ".sav");

		} else {
			Toast.makeText(this, "Max number of save files reached",
					Toast.LENGTH_LONG).show();
		}

	}

	private void save(String filename) {
		try {

			GameDetails metasave = new GameDetails(this.getIntent()
					.getStringExtra("playername"), this.getIntent()
					.getIntExtra("difficulty", Difficulty.NORMAL), filename);

			FileOutputStream save_output = new FileOutputStream(new File(
					this.getExternalFilesDir(null), metasave.getSaveFile()));
			ObjectOutputStream output_stream = new ObjectOutputStream(
					save_output);

			output_stream.writeObject(metasave);

			output_stream.close();
			save_output.close();

			Intent intent = new Intent(this, NewGameActivity.class);

			intent.putExtra("difficulty", metasave.getDifficultyLevel());
			intent.putExtra("savefile", metasave.getSaveFile());

			this.startActivity(intent);
			this.finish();

		} catch (IOException err) {
			Toast.makeText(this, err.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onSaveSelect(GameDetails save) {
		this.save(save.getSaveFile());
	}
}