package spaceappschallenge.moonville.dialogs;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.activities.NewGameActivity;
import spaceappschallenge.moonville.activities.SaveGameActivity;
import spaceappschallenge.moonville.domain.Difficulty;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class NewGameDialog extends DialogFragment implements DialogInterface.OnClickListener {
	
	private EditText playername;
	private RadioButton difficulty;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		//Prepare the Dialog builder for building the dialog.
		AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
		
		//Generating dialog content from XML Layout file.
		View view = this.getActivity().getLayoutInflater().inflate(R.layout.dialog_new_game, null);
		
		//Retrieving Input views references.
		
		//setting up dialog content
		builder.setCustomTitle(null);
		builder.setView(view);
		
		//Getting input views references
		this.playername = (EditText) view.findViewById(R.id.ngd_username);
		this.difficulty = (RadioButton) view.findViewById(R.id.ngd_realistic_radiobtn);
		
		//setting dialog buttons.
		builder.setPositiveButton("Start", this);
		builder.setNegativeButton(android.R.string.cancel, null);
		return builder.create();
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		
		//Create intent that calls NewGameActivity.
		Intent i = new Intent(this.getActivity(),SaveGameActivity.class);
		
		//Retrieve the player name from the EditText view and trim any extra spaces.
		String playername = this.playername.getText().toString();
		playername = playername.trim();
		
		//Set name to default playername if string is empty.
		if (playername.length() == 0) {
			playername = this.getString(R.string.default_player_name);
		}
		
		//Stored the inputed information in the intent.
		i.putExtra("playername", playername);
		i.putExtra("difficulty", (this.difficulty.isChecked())? Difficulty.HARD : Difficulty.NORMAL);
		
		//Start the NewGameActivity using the intent.
		this.startActivity(i);
	}
}
