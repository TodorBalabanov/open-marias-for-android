package eu.veldsoft.marias;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

/**
 * 
 */
public class AboutActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		//TODO Use string resources instead of shared preferences.
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);

		Toast.makeText(
				AboutActivity.this,
				"<b>OpenMarias</b>, free OPENsource Marias<br/>"
						+ "2009-2011 by Miso Kovac<br/>"
						+ "See <a href='http://sourceforge.net/projects/openmarias'>http://sourceforge.net/projects/openmarias</a>"
						+ "<br/>"
						+ "Version: <b>"
						+ preferences.getInt("version_major", 0)
						+ "."
						+ preferences.getInt("version_minor", 0)
						+
						(preferences.getInt("version_revision", 0) == 0 ? ""
								: "."
										+ preferences.getInt(
												"version_revision", 0))
						+ "</b>", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_about, menu);
		return true;
	}

}
