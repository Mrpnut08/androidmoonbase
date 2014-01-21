package spaceappschallenge.moonville.dialog;

import spaceappschallenge.moonville.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

public class SettingsDialog extends DialogFragment implements DialogInterface.OnClickListener{

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
		
		View view = this.getActivity().getLayoutInflater().inflate(R.layout.dialog_settings, null);
		
		builder.setCustomTitle(null);
		builder.setView(view);
		builder.setPositiveButton("Apply Changes", this);
		builder.setNegativeButton(android.R.string.cancel, null);
		return builder.create();
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
}
