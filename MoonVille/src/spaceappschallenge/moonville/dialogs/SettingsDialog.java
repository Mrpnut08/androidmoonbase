package spaceappschallenge.moonville.dialogs;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.miscellaneous.MoonVille;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class SettingsDialog extends DialogFragment implements OnClickListener{

	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	
	private CheckBox music;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
		
		this.settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		
		View view = this.getActivity().getLayoutInflater().inflate(R.layout.dialog_settings, null);
		this.music = (CheckBox) view.findViewById(R.id.sd_music);
		this.music.setChecked(settings.getBoolean("pref_music", true));
		this.music.setOnClickListener(this);
		
		builder.setCustomTitle(null);
		builder.setView(view);
		builder.setPositiveButton("Close", null);
		return builder.create();
	}
	
	@Override
	public void onClick(View v) {
		
		boolean new_value = this.music.isChecked();
		
		this.editor = settings.edit();
		editor.putBoolean("pref_music", new_value);
		MoonVille app = (MoonVille) this.getActivity().getApplication();
		app.updateSoundState();
		this.editor.commit();
	}
}
