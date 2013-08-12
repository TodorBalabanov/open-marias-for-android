package eu.veldsoft.marias;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((Button) findViewById(R.id.quit_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
						System.exit(0);
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

		((Button) findViewById(R.id.settings_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						startActivity(new Intent(MainActivity.this,
								SettingsActivity.class));
					}
				});

		((Button) findViewById(R.id.new_game_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						startActivity(new Intent(MainActivity.this,
								GameActivity.class));
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
