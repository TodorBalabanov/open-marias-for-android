package eu.veldsoft.marias;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BiddingActivity extends Activity {
	/**
	 * Bidding history is used to show what happened during the bidding process.
	 * This text should be shown as toast each time when some kind of bid was
	 * done.
	 */
	private String biddingHistory = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bidding);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bidding, menu);
		return true;
	}

}
