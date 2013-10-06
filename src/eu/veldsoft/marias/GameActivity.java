package eu.veldsoft.marias;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends Activity {
	/**
	 * Game state machine.
	 */
	private Game game = null;

	/**
	 * Human player card click listener.
	 */
	private OnClickListener onHumanPlayerCardClick = new OnClickListener() {
		@Override
		public void onClick(View view) {
			Toast.makeText(GameActivity.this, view.toString(),
					Toast.LENGTH_SHORT).show();
		}
	};

	/**
	 * Initialize table (deck, player hand, trump and trump suit).
	 */
	private void clearTable() {
		((ImageView) findViewById(R.id.trump_suit))
				.setImageResource(R.drawable.s);

		((ImageView) findViewById(R.id.trump_card))
				.setImageResource(R.drawable.empty);

		((ImageView) findViewById(R.id.right1card1))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.right1card2))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.right1card3))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.right1card4))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.right1card5))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.right1card6))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.right1card7))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.right1card8))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.right1card9))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.right1card10))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.right1card11))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.right1card12))
				.setImageResource(R.drawable.empty);

		((ImageView) findViewById(R.id.left2card1))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2card2))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2card3))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2card4))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2card5))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2card6))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2card7))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2card8))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2card9))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2card10))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2card11))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2card12))
				.setImageResource(R.drawable.empty);

		((ImageView) findViewById(R.id.front3card1))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3card2))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3card3))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3card4))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3card5))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3card6))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3card7))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3card8))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3card9))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3card10))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3card11))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3card12))
				.setImageResource(R.drawable.empty);

		((ImageView) findViewById(R.id.right1played))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.left2played))
				.setImageResource(R.drawable.empty);
		((ImageView) findViewById(R.id.front3played))
				.setImageResource(R.drawable.empty);

		((ImageView) findViewById(R.id.deck)).setImageResource(R.drawable.rub);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		findViewById(R.id.front3card1).setOnClickListener(
				onHumanPlayerCardClick);
		findViewById(R.id.front3card2).setOnClickListener(
				onHumanPlayerCardClick);
		findViewById(R.id.front3card3).setOnClickListener(
				onHumanPlayerCardClick);
		findViewById(R.id.front3card4).setOnClickListener(
				onHumanPlayerCardClick);
		findViewById(R.id.front3card5).setOnClickListener(
				onHumanPlayerCardClick);
		findViewById(R.id.front3card6).setOnClickListener(
				onHumanPlayerCardClick);
		findViewById(R.id.front3card7).setOnClickListener(
				onHumanPlayerCardClick);
		findViewById(R.id.front3card8).setOnClickListener(
				onHumanPlayerCardClick);
		findViewById(R.id.front3card9).setOnClickListener(
				onHumanPlayerCardClick);
		findViewById(R.id.front3card10).setOnClickListener(
				onHumanPlayerCardClick);
		findViewById(R.id.front3card11).setOnClickListener(
				onHumanPlayerCardClick);
		findViewById(R.id.front3card12).setOnClickListener(
				onHumanPlayerCardClick);

		game = new Game(GameActivity.this);
		game.init();

		/*
		 * Clear table is done to initialize visual controls before next round.
		 */
		clearTable();

		DeskView.createInstance(this, game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_game:
			game.newGame();
			Toast.makeText(GameActivity.this, "New Game ...",
					Toast.LENGTH_SHORT).show();
			return (true);

		case R.id.deal_1_phase:
			//TODO Show cards after dealing.
			Toast.makeText(GameActivity.this, "Deal 1 ...", Toast.LENGTH_SHORT)
					.show();
			return (true);

		case R.id.deal_2_phase:
			Toast.makeText(GameActivity.this, "Deal 2 ...", Toast.LENGTH_SHORT)
					.show();
			return (true);

		default:
			return (super.onOptionsItemSelected(item));
		}
	}
}
