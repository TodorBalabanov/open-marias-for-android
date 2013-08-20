package eu.veldsoft.marias;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(MainActivity.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * Pseudo-random number generator.
	 */
	public static Random prng = new Random();

	/**
	 * Load default settings values as it was done in the original source code.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 04 Jul 2013
	 */
	private void loadDefaultSettings() {
		// TODO Load settings from preferences.
	}

	/**
	 * There are few buttons in UI which need listeners.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 04 Jul 2013
	 */
	private void attachButtonListeners() {
		((Button) findViewById(R.id.new_game_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						LOGGER.info("new game");
						startActivity(new Intent(MainActivity.this,
								GameActivity.class));
						LOGGER.info("new game end");
					}
				});

		((Button) findViewById(R.id.settings_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						startActivity(new Intent(MainActivity.this,
								SettingsActivity.class));
					}
				});

		((Button) findViewById(R.id.about_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						startActivity(new Intent(MainActivity.this,
								AboutActivity.class));
					}
				});

		((Button) findViewById(R.id.quit_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
						System.exit(0);
					}
				});

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		attachButtonListeners();

		loadDefaultSettings();
		
		//TODO DeskView.createInstance(ui, game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
