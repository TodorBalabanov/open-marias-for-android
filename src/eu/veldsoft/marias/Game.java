package eu.veldsoft.marias;

import java.util.List;
import java.util.Random;

/**
 * 
 * Most important class that handles events, manages players, cards and whole
 * gameplay with results. Main functions are *Clicked() and animationFinished().
 * 
 * TODO: split this class into more smaller with exact functionality.
 * 
 * @author Miso Kovac
 * 
 */
class Game {

	// TODO Use shared preferences.
	// public QSettings settings;

	public List<Player> players;

	public List<Integer> deck;

	// Current state of the game
	public Stav stav;

	// if is now the human turn
	public boolean waitingForClick;

	public BiddingDialog bd;

	public Marias marias;

	public boolean quickGame;

	public Profiler profiler;

	public Random rg;

	// for poeple test
	public int stats[] = new int[3];

	public Game(Marias m) {
		// TODO To be done by ...
	}

	public void init() {
		// TODO To be done by ...
	}

	public void resetMoney() {
		// TODO To be done by ...
	}

	public void results() {
		// TODO To be done by ...
	}

	/**
	 * When clicked on CardItem, it depends on stav.kolo (current gamestate)
	 * whether to execute cardClicked, talonClicked or tromfClicked. cardClicked
	 * is called when a human selects a card he wish to play.
	 * 
	 * @see CardItem
	 * 
	 */
	public void cardClicked(int k) {
		// TODO To be done by ...
	}

	/**
	 * Returns "" if the card is valid. Error message otherwise.
	 * 
	 */
	public String validateTalon(int k) {
		// TODO To be done by ...
		return (null);
	}

	/**
	 * When clicked on CardItem, it depends on stav.kolo (current gamestate)
	 * whether to execute cardClicked, talonClicked or tromfClicked.
	 * talonClicked is called when forhont wants to select card to talon.
	 * 
	 * @see CardItem
	 * 
	 */
	public void talonClicked(int k) {
		// TODO To be done by ...
	}

	/**
	 * When clicked on CardItem, it depends on stav.kolo (current gamestate)
	 * whether to execute cardClicked, talonClicked or tromfClicked.
	 * tromfClicked is called when forhont wants to select tromf.
	 * 
	 * @see CardItem
	 * 
	 */
	public void tromfClicked(int k) {
		// TODO To be done by ...
	}

	/**
	 * Current player wants to play card k, this function executes all
	 * necessities.
	 * 
	 */
	public boolean turn(int k) {
		// TODO To be done by ...
		return (false);
	}

	public void shuffleDeck() {
		// TODO To be done by ...
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
