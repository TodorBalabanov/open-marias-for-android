package eu.veldsoft.marias;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	// TODO It is only for project to compile.
	private class Settings {
		public static final int IniFormat = 0;

		public Settings(String string, int format) {
		}

		public int value(String string, int value) {
			return (0);
		}

		public Object value(String string, String string2) {
			return ("");
		}
	}

	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(Game.class.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	// TODO Use shared preferences.
	public Settings settings;

	/**
	 * Players container.
	 */
	public List<Player> players;

	/**
	 * Deck of cards.
	 */
	public List<Integer> deck;

	/**
	 * Current state of the game.
	 */
	public Stav stav;

	/**
	 * If is now the human turn flag.
	 */
	public boolean waitingForClick;

	/**
     *
     */
	public BiddingDialog bd;

	/**
	 * Marias game logic.
	 */
	public Marias marias;

	/**
	 * Quick game flag.
	 */
	public boolean quickGame;

	/**
	 * Profiler instance.
	 */
	public Profiler profiler;

	/**
	 * Pseudo-random number generator.
	 */
	public Random rg;

	/**
	 * for poeple test
	 */
	public int stats[] = new int[3];

	/**
	 * Constructor.
	 * 
	 * @param m
	 *            Marias object reference.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public Game(Marias m) {
		// TODO Deep copy is bettter.
		marias = m;

		// TODO Should be implemented because it is different than Qt
		// implementation.
		settings = new Settings("marias.ini", Settings.IniFormat);

		bd = new BiddingDialog(this);

		profiler = new Profiler();

		for (int i = 0; i < stats.length; i++) {
			stats[i] = 0;
		}
	}

	/**
	 * Initialize game.
	 * 
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void init() {
		LOGGER.info("game init");
		quickGame = false;

		// TODO Settings should be available in order this fragmetn to work.
		if (settings.value("shuffling/random", 1) == 1) {
			LOGGER.info("random");
			rg.setSeed(System.currentTimeMillis());
			;
		} else {
			LOGGER.info("not random");
			rg.setSeed(settings.value("shuffling/seed", 47));
		}

		shuffleDeck();

		players.clear();
		players.add(PlayerFactory.create(settings.value("players/front",
				"HumanPlayer").toString()));
		players.add(PlayerFactory.create(settings.value("players/left",
				"RandomPlayer").toString()));
		players.add(PlayerFactory.create(settings.value("players/right",
				"RandomPlayer").toString()));
		players.get(0).name = settings.value("players/front_name", "front")
				.toString();
		players.get(1).name = settings.value("players/left_name", "left")
				.toString();
		players.get(2).name = settings.value("players/right_name", "right")
				.toString();

		// TODO Use for-each loop.
		for (int i = 0; i < 3; i++) {
			// TODO May be internal objects are unchainged if there is deep
			// copy.
			players.get(i).setStav(stav);
			players.get(i).setId(i);
			players.get(i).profiler = profiler;
			players.get(i).init();
		}

		resetMoney();

		stav.forhont = 2;
		stav.kolo = -6;
	}

	public void resetMoney() {
		// TODO To be done by Miro ...
	}

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
		// if(quickGame==false)
		// LOGGER.info(players.get(stav.forhont).name << " chce dat do talonu "
		// << Card.titleA(k);

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

		// if(quickGame==false)
		// DeskView.log(players.get(stav.forhont).name+tr(" dal do talonu ")+Card.titleA(k));

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
		// if(quickGame==false)
		// LOGGER.info(players.get(stav.forhont).name <<
		// tr(" chce zvolit tromf ") << Card.titleA(k);

		if (players.get(stav.forhont).hand.contains(k) == false) {
			// qDebug("Ale taku kartu nema");

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

		int swapCount = 1000 + rg.nextInt(1000);

		// TODO Better shuffling algorithm shuld be used.
		for (int i = 0; i < swapCount; i++) {
			Integer card = deck.remove(rg.nextInt(32));
			deck.add(card);
		}
	}

	/**
	 * First round of card dealing. Forhont gets 7 cards, other 5.
	 * 
	 */
	public void rozdaj1() {
		// TODO To be done by ...
	}

	/**
	 * Second round of card dealing. All players get 5 cards.
	 * 
	 */
	public void rozdaj2() {
		// TODO To be done by ...
	}

	/**
	 * Switches player type for player at position i.
	 * 
	 */
	public void changePlayer(int i, String newType) {
		// TODO To be done by ...
	}

	public void newGame() {
		// TODO To be done by ...
	}

	/**
	 * Major function, is responsible for future actions, that depends on game
	 * state. This is due to event driven programming, where main loop is hidden
	 * from programmers. This function must exist, maybe it can split some
	 * functionality in more functions.
	 * 
	 */
	public void animationFinished(int round) {
		// TODO To be done by ...
	}
}
