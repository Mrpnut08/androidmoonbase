package spaceappschallenge.moonville.miscellaneous;

import spaceappschallenge.moonville.R;
import spaceappschallenge.moonville.R.raw;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.preference.PreferenceManager;

/**
 * Plays background music while any activity is opened. All activities should
 * imlpement GameActivity to properly play sounds.
 * 
 * @author Felix
 * 
 */
public class MoonVille extends Application {
	public static String PREFERENCE_BACKGROUND_MUSIC = "pref_music";

	private MediaPlayer player = null;
	private int currentActivityCount = 0;
	private static Context appContext;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		appContext = this;
	}

	public static Context getContext() {
		return appContext;
	}

	public void resumeActivity() {
		currentActivityCount++;
		updateSoundState();
	}

	public void pauseActivity() {
		currentActivityCount--;
		updateSoundState();
	}

	/**
	 * Calls backgroundSoundRunnable after a short delay (in case a new activity
	 * is loading).
	 */
	public void updateSoundState() {
		Handler myHandler = new Handler();
		myHandler.postDelayed(backgroundSoundRunnable, 100);
	}

	/**
	 * Enables or disables background sound based on activities visibility and
	 * preferences.
	 */
	private Runnable backgroundSoundRunnable = new Runnable() {
		@Override
		public void run() {
			SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(appContext);
			
			if (currentActivityCount > 0
					&& settings.getBoolean(PREFERENCE_BACKGROUND_MUSIC, true)) {
				if (player == null) {
					player = MediaPlayer.create(MoonVille.this,
							R.raw.moonloop1reformated_small);
				}
				if (!player.isPlaying()) {
					player.setLooping(true);
					player.setVolume(100, 100);
					player.start();
				}
			} else if (player != null) {
				player.release();
				player = null;
			}
		}
	};
}
