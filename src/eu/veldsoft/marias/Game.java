package eu.veldsoft.marias;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.SharedPreferences;
import android.util.Log;

/**
 * 
 * Most important class that handles events, manages players, cards and whole
 * gameplay with results. Main functions are *Clicked() and animationFinished().
 * 
 * TODO: split this class into more smaller with exact functionality.
 * 
 * @author Miso Kovac
 * @email kovac@fmph.uniba.sk
 * @data 14 Aug 2013
 */
class Game {
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(Game.class.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * Players container.
	 */
	public List<Player> players = new ArrayList<Player>();

	/**
	 * Deck of cards.
	 */
	public List<Integer> deck = new ArrayList<Integer>();

	/**
	 * Current state of the game.
	 */
	public Stav stav = new Stav();

	/**
	 * If is now the human turn flag.
	 */
	public boolean waitingForClick;

	/**
     *
     */
	public BiddingDialog bd;

	/**
	 * Reference to game windows.
	 */
	public GameActivity gameActivity = null;

	/**
	 * Quick game flag.
	 */
	public boolean quickGame;

	/**
	 * Profiler instance.
	 */
	public Profiler profiler;

	/**
	 * for poeple test
	 */
	public int stats[] = new int[3];

	/**
	 * Used instead of destructor.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	protected void finalize() throws Throwable {
		LOGGER.info("" + stats[0] + stats[1] + stats[2]);

		String uncompressed = "" + stats[0] + " " + stats[1] + " " + stats[2];

		DataOutputStream out = new DataOutputStream(new FileOutputStream(
				"vysledky.txt"));
		out.writeUTF(uncompressed);
		out.close();
	}

	/**
	 * Constructor.
	 * 
	 * @param gameActivity
	 *            Marias object reference.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public Game(GameActivity gameActivity) {
		this.gameActivity = gameActivity;

		// TODO bd = new BiddingDialog(this);

		profiler = new Profiler();

		for (int i = 0; i < stats.length; i++) {
			stats[i] = 0;
		}
	}

	/**
	 * Initialize game.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void init() {
		LOGGER.info("game init");
		quickGame = false;

		SharedPreferences preferences = gameActivity
				.getPreferences(gameActivity.MODE_PRIVATE);

		preferences.getBoolean("shuffling_random", true);
		preferences.getInt("shuffling_seed", 47);

		if (preferences.getBoolean("shuffling_random", true) == true) {
			LOGGER.info("random");
			MainActivity.prng.setSeed(System.currentTimeMillis());
		} else {
			LOGGER.info("not random");
			MainActivity.prng.setSeed(preferences.getInt("shuffling_seed", 47));
		}

		shuffleDeck();

		players.clear();
		players.add(PlayerFactory.create(preferences.getString("players_front",
				"HumanPlayer")));
		players.add(PlayerFactory.create(preferences.getString("players_left",
				"RandomPlayer")));
		players.add(PlayerFactory.create(preferences.getString("players_right",
				"RandomPlayer")));
		players.get(0).name = preferences.getString("players_front_name",
				"front");
		players.get(1).name = preferences
				.getString("players_left_name", "left");
		players.get(2).name = preferences.getString("players_right_name",
				"right");

		int id = 0;
		for (Player player : players) {
			player.setStav(stav);
			player.setId(id);
			player.profiler = profiler;
			player.init();
			id++;
		}

		resetMoney();

		stav.forhont = 2;
		stav.kolo = -6;
	}

	public void resetMoney() {
		// TODO To be done by Miro ...
	}

	/**
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void results() {
		int p = stav.trick();

		if (quickGame == false) {
			DeskView.log("Stich berie " + players.get(p).name);
			LOGGER.info("Stich berie " + players.get(p).name);
		}

		for (Integer c : stav.kopa) {
			if (Card.value(c).equals("10") || Card.value(c).equals("eso")) {
				players.get(p).body += 10;
			}
		}

		if (stav.kolo == 9) {
			players.get(p).body += 10;
		}

		stav.id = p;

		/*
		 * First kolo increase, then animation.
		 */
		stav.kolo++;

		if (quickGame == false) {
			DeskView.animateStich(p);
		}

		stav.pHist.add(p);
		stav.vysid = p;
		stav.kopa.clear();

		if (stav.kolo == 10) {
			profiler.stop("game - trick taking");
			int res = stav.results(true);
			LOGGER.info("stav results: " + res);
			players.get(stav.forhont).peniaze += res * 2;
			players.get((stav.forhont + 1) % 3).peniaze -= res;
			players.get((stav.forhont + 2) % 3).peniaze -= res;
			if (players.get(0).type == "human"
					&& players.get(1).type == "minimax"
					&& players.get(2).type == "smart") {
				stats[stav.forhont] += res * 2;
				stats[(stav.forhont + 1) % 3] -= res;
				stats[(stav.forhont + 2) % 3] -= res;
			}
		}

		profiler.stop("trick taking - stich results");
		if (quickGame == true) {
			animationFinished(stav.kolo);
		}
	}

	/**
	 * When clicked on CardItem, it depends on stav.kolo (current gamestate)
	 * whether to execute cardClicked, talonClicked or tromfClicked. cardClicked
	 * is called when a human selects a card he wish to play.
	 * 
	 * @param k
	 * 
	 * @see CardItem
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void cardClicked(int k) {
		profiler.stop(players.get(stav.id).name);
		LOGGER.info("click");

		if (quickGame == true) {
			return;
		}

		if (waitingForClick == false) {
			LOGGER.info("pozhovej");
			DeskView.print("pozhovej", 0);
			return;
		}

		if (stav.kolo == 10) {
			return;
		}

		if (turn(k) == false) {
			return;
		}

		waitingForClick = false;

		DeskView.animateCard(k, stav.id);

		stav.dalsi();
	}

	/**
	 * Returns "" if the card is valid. Error message otherwise.
	 * 
	 * @param k
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public String validateTalon(int k) {
		if (players.get(stav.forhont).hand.contains(k) == false) {
			return ("ZO SVOJICH KARIET!");
		}

		if (stav.hra.farba == false) {
			return ("");
		}

		if (Card.value(k).equals("10") || Card.value(k).equals("eso")) {
			return ("DO TALONA NESMU IST 10 A ESO");
		}

		if (k == stav.hra.tromf) {
			return ("DO TALONA NESMIE IST VOLENY TROMF");
		}

		return ("");
	}

	/**
	 * When clicked on CardItem, it depends on stav.kolo (current gamestate)
	 * whether to execute cardClicked, talonClicked or tromfClicked.
	 * talonClicked is called when forhont wants to select card to talon.
	 * 
	 * @see CardItem
	 * 
	 * @param k
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void talonClicked(int k) {
		String res = validateTalon(k);

		if (res.equals("") == false) {
			LOGGER.info(res);
			if (players.get(stav.forhont).type.equals("human")
					&& quickGame == false) {
				DeskView.print(res, stav.forhont);
			}
			return;
		}
		players.get(stav.forhont).removeCard(k);

		if (players.get(stav.forhont).hand.size() == 11) {
			players.get(stav.forhont).talonCards[0] = k;

			if (quickGame = false) {
				DeskView.talon(k);
			}

			if (players.get(stav.forhont).type == "human" && quickGame == false) {
				DeskView.print("ESTE JEDNU", stav.forhont);
			}
		} else if (players.get(stav.forhont).hand.size() == 10) {
			/*
			 * first kolo increase, then animation
			 */
			players.get(stav.forhont).talonCards[1] = k;

			stav.kolo = -1;

			if (quickGame == false) {
				DeskView.talon(k, true);
			} else {
				animationFinished(-1);
			}
		} else if (players.get(stav.forhont).hand.size() < 10) {
			LOGGER.info(players.get(stav.forhont).name
					+ " ma menej ako 10 kariet ??");
		}
	}

	/**
	 * When clicked on CardItem, it depends on stav.kolo (current gamestate)
	 * whether to execute cardClicked, talonClicked or tromfClicked.
	 * tromfClicked is called when forhont wants to select tromf.
	 * 
	 * @param k
	 * 
	 * @see CardItem
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void tromfClicked(int k) {
		if (players.get(stav.forhont).hand.contains(k) == false) {
			if (quickGame == false) {
				DeskView.print("VYBERAJ LEN ZO SVOJEJ RUKY!", stav.forhont);
			}

			return;
		}

		waitingForClick = false;

		stav.hra.tromf = k;

		if (quickGame == false) {
			DeskView.ejectTromf(k, false);
			DeskView.log(players.get(stav.forhont).name + " zvolil tromfa.");
		}

		rozdaj2();
	}

	/**
	 * Current player wants to play card k, this function executes all
	 * necessities.
	 * 
	 * @param k
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public boolean turn(int k) {
		if (quickGame == false) {
			LOGGER.info(players.get(stav.id).name + ": " + Card.title(k));
		}

		String result = players.get(stav.id).validate(k);

		if (result.equals("") == false) {
			if (quickGame == false) {
				DeskView.print(result, stav.id);
			}

			LOGGER.info(players.get(stav.id).name + " zahrala nevalidnu kartu "
					+ Card.title(k));

			return (false);
		}

		if (Card.value(k).equals("hornik")
				&& players.get(stav.id).hand.contains(k + 1)) {
			int hlaska = 20;

			if (Card.color(k).equals(Card.color(stav.hra.tromf))) {
				hlaska = 40;
			}

			if (quickGame == false) {
				DeskView.print("" + hlaska, stav.id);
			}

			players.get(stav.id).body += hlaska;
			players.get(stav.id).hlasky++;
			stav.hlaska(hlaska);
		}

		stav.cHist.add(k);
		stav.kopa.add(k);
		players.get(stav.id).removeCard(k);

		return (true);
	}

	/**
	 * Deck shuffling.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void shuffleDeck() {
		deck.clear();

		for (int i = 0; i < 32; i++) {
			deck.add(i);
		}

		int swapCount = 1000 + MainActivity.prng.nextInt(1000);

		// TODO Better shuffling algorithm shuld be used.
		for (int i = 0; i < swapCount; i++) {
			Integer card = deck.remove(MainActivity.prng.nextInt(32));
			deck.add(card);
		}
	}

	/**
	 * First round of card dealing. Forhont gets 7 cards, other 5.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void rozdaj1() {
		/*
		 * first kolo increase, then animation
		 */
		profiler.start("rozdaj 1");

		stav.kolo = -5;

		for (int i = 0; i < 3; i++) {
			players.get(i).hand.clear();
		}

		for (int i = 0; i < 7; i++) {
			players.get(stav.forhont).hand.add(deck.get(i));

			if (quickGame == false) {
				DeskView.rozdaj(deck.get(i), stav.forhont, i, i);
			}
		}

		for (int i = 7; i < 12; i++) {
			players.get((stav.forhont + 1) % 3).hand.add(deck.get(i));

			if (quickGame == false) {
				DeskView.rozdaj(deck.get(i), (stav.forhont + 1) % 3, i - 7, i);
			}
		}

		for (int i = 12; i < 17; i++) {
			players.get((stav.forhont + 2) % 3).hand.add(deck.get(i));

			if (quickGame == false) {
				DeskView.rozdaj(deck.get(i), (stav.forhont + 2) % 3, i - 12, i,
						i == 16);
			}
		}

		profiler.stop("rozdaj 1");

		if (quickGame == true) {
			animationFinished(-5);
		}
	}

	/**
	 * Second round of card dealing. All players get 5 cards.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void rozdaj2() {
		profiler.start("rozdaj 2");

		/*
		 * first kolo increase, then animation
		 */
		stav.kolo = -3;

		for (int i = 17; i < 22; i++) {
			players.get(stav.forhont).hand.add(deck.get(i));

			if (quickGame == false) {
				DeskView.rozdaj(deck.get(i), stav.forhont, i - 10, i - 17);
			}
		}

		for (int i = 22; i < 27; i++) {
			players.get((stav.forhont + 1) % 3).hand.add(deck.get(i));

			if (quickGame == false) {
				DeskView.rozdaj(deck.get(i), (stav.forhont + 1) % 3, i - 17,
						i - 17);
			}
		}

		for (int i = 27; i < 32; i++) {
			players.get((stav.forhont + 2) % 3).hand.add(deck.get(i));

			if (quickGame == false) {
				DeskView.rozdaj(deck.get(i), (stav.forhont + 2) % 3, i - 22,
						i - 17, i == 31);
			}
		}

		profiler.stop("rozdaj 2");

		if (quickGame == true) {
			animationFinished(-3);
		}
	}

	/**
	 * Switches player type for player at position i.
	 * 
	 * @param i
	 * @param newType
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void changePlayer(int i, String newType) {
		if (i < 0 || i > 2) {
			return;
		}

		if (quickGame == true) {
			return;
		}

		List<Integer> hand = players.get(i).hand;

		int body = players.get(i).body;
		int peniaze = players.get(i).peniaze;
		int hlasky = players.get(i).hlasky;

		String message = players.get(i).message;

		int talonCards[] = new int[2];
		talonCards[0] = players.get(i).talonCards[0];
		talonCards[1] = players.get(i).talonCards[1];

		players.remove(i);
		// TODO It is possible this to be wrong index i.
		players.add(i, PlayerFactory.create(newType));

		players.get(i).hand = hand;
		players.get(i).body = body;
		players.get(i).peniaze = peniaze;
		players.get(i).hlasky = hlasky;
		players.get(i).setStav(stav);
		players.get(i).profiler = profiler;
		players.get(i).message = message;
		players.get(i).setId(i);

		if (stav.forhont == i) {
			players.get(i).talonCards[0] = talonCards[0];
			players.get(i).talonCards[1] = talonCards[1];
		}

		players.get(i).init();

		DeskView.draw();
	}

	/**
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void newGame() {
		profiler.start("dealing and bidding");

		if (bd.isVisible() == true) {
			bd.hide();
		}

		for (Player player : players) {
			player.body = 0;
			player.hlasky = 0;
			player.quickGame = quickGame;
		}

		stav.newGame();

		shuffleDeck();

		if (quickGame == false) {
			DeskView.log("Nova hra.");
			DeskView.log("Forhont je " + players.get(stav.forhont).name);
			DeskView.gather();
			DeskView.draw();
		}

		rozdaj1();
	}

	/**
	 * Major function, is responsible for future actions, that depends on game
	 * state. This is due to event driven programming, where main loop is hidden
	 * from programmers. This function must exist, maybe it can split some
	 * functionality in more functions.
	 * 
	 * @param round
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void animationFinished(int round) {
		if (quickGame == false) {
			LOGGER.info("animation finished " + stav.kolo + ":" + round);
		}

		if (stav.kolo != round) {
			LOGGER.info("animation synchro fail");
			return;
		}

		if (stav.kolo == -6) {
			if (quickGame == false) {
				DeskView.gather();
			}

			stav.kolo = -5;
			return;
		}

		if (stav.kolo == -5) {
			if (quickGame == false) {
				for (int i = 0; i < players.get(0).hand.size(); i++) {
					DeskView.revealCard(players.get(0).hand.get(i));
				}
			}

			players.get(0).sortHand();

			/*
			 * first kolo increase, then animation
			 */
			stav.kolo = -4;

			if (quickGame == false) {
				for (int i = 0; i < players.get(0).hand.size(); i++) {
					DeskView.rozdaj(players.get(0).hand.get(i), 0, i, 0,
							i == players.get(0).hand.size() - 1);
				}
			} else {
				animationFinished(-4);
			}

			return;
		}

		if (stav.kolo == -4) {
			if (players.get(stav.forhont).type == "human" && quickGame == false) {
				DeskView.print("ZVOL TROMF", stav.forhont);
				waitingForClick = true;
			} else {
				int k = players.get(stav.forhont).tromf();
				tromfClicked(k);
			}
			return;
		}

		if (stav.kolo == -3) {
			/*
			 * first kolo increase, then animation
			 */
			stav.kolo = -2;

			if (quickGame == false) {
				for (int i = 0; i < players.get(0).hand.size(); i++) {
					DeskView.revealCard(players.get(0).hand.get(i));
				}
			}

			players.get(0).sortHand();

			if (quickGame == false) {
				DeskView.fixHand(0, true);
			} else {
				animationFinished(-2);
			}

			return;
		}

		if (stav.kolo == -2) {
			if (players.get(stav.forhont).type == "human" && quickGame == false) {
				DeskView.print("2 KARTY DO TALONU", stav.forhont);
				waitingForClick = true;
			} else {
				int tal = players.get(stav.forhont).talon();
				talonClicked(tal % 32);
				talonClicked(tal / 32);
			}
			return;
		}

		if (stav.kolo == -1) {
			if (quickGame == false) {
				DeskView.fixHand(0);
			}

			/*
			 * farba - dobra - dobra
			 */
			if (quickGame == false) {
				DeskView.draw();
			}

			stav.hra.farba = true;
			stav.kopa.clear();
			stav.id = stav.forhont;
			bd.startBidding();

			return;
		}

		if (stav.kolo == 10) {
			stav.hra.tromf = -1;

			if (quickGame == false) {
				profiler.start("draw");
				DeskView.draw();
				DeskView.drawResults();
				profiler.stop("draw");
			}

			profiler.stop("game - results");
			profiler.start("game - after results");

			return;
		}

		if (stav.kolo >= 0) {
			if (stav.kopa.size() < 3) {
				profiler.start(players.get(stav.id).name);

				int k = players.get(stav.id).play();

				profiler.stop(players.get(stav.id).name);

				if (quickGame == false) {
					LOGGER.info(players.get(stav.id).name + " rozmyslal: "
							+ profiler.totals.get(players.get(stav.id).name));
				}

				if (k == -1 && players.get(stav.id).type == "human") {
					profiler.start(players.get(stav.id).name);
					waitingForClick = true;
					return;
				}

				if (turn(k) == false) {
					LOGGER.info("Niekto nevie zahrat toto kolo");
				}

				if (quickGame == false) {
					if (players.get(stav.id).message != "") {
						DeskView.log(players.get(stav.id).message);
						players.get(stav.id).message = "";
						DeskView.draw();
					}

					DeskView.animateCard(k, stav.id);
				}

				stav.dalsi();

				if (quickGame == true) {
					animationFinished(stav.kolo);
				}
			} else {
				profiler.start("trick taking - stich results");
				results();
			}

			return;
		}
	}
}
