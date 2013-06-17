package eu.veldsoft.marias;

import java.util.List;
import java.util.Random;

/**
 * @class Player
 * @author Mirelon
 * 
 *         Player is class that is partially abstract. Extending classes
 *         implement it's methods. It also stores player data.
 */
class Player {
	public String name;

	public String type;

	public List<Integer> hand;

	// points
	public int body;

	// money
	public int peniaze;

	// pocet hlasok
	public int hlasky;

	public Stav stav;

	public int id;

	public String message;

	public boolean quickGame;

	public Profiler profiler;

	public Random rg;

	// if the player is forhont, he should know what is in talon
	public int talonCards[] = new int[2];

	public Player() {
	}

	public void init() {
	}

	public void setStav(Stav s) {
	}

	public void setId(int pid) {
	}

	/**
	 * @param stav
	 *            State of the game.
	 * @return int Id of the played card.
	 */
	public int play() {
		return (0);
	}

	/**
	 * @param stav
	 *            State of the game.
	 * @return int Id of two cards for talon (32*id_1 + id_2).
	 */
	public int talon() {
		return (0);
	}

	/**
	 * @return int Id of the tromf card.
	 */
	public int tromf() {
		return (0);
	}

	/**
	 * @param stav
	 *            State of the game.
	 * @return int Bid (3 bits in one number less than 8) First bit is whether
	 *         the player bids the game, second is bid for the seven and third
	 *         is bid for the hundred.
	 */
	public int bid() {
		return (0);
	}

	/**
	 * Removes card from hand.
	 */
	public void removeCard(int cid) {
	}

	public void say(String s) {
	}

	/**
	 * Utility method, used by validation, it must check, if the player is able
	 * to beat given card (to beat = prebit).
	 * 
	 * @see validate
	 */
	public boolean mozePrebit(int cid, Stav s, List<Integer> h) {
		return (false);
	}

	public boolean mozePrebit(int cid) {
		return (mozePrebit(cid, null, null));
	}

	/**
	 * Utility method, used by validation, it must check, if the player is able
	 * to beat given card (to beat = prebit) with card of same color(suit).
	 * 
	 * @see validate
	 */
	public boolean mozePrebitCisto(int cid, Stav s, List<Integer> h) {
		return (false);
	}

	public boolean mozePrebitCisto(int cid) {
		return (mozePrebitCisto(cid, null, null));
	}

	/**
	 * Utility method, used by validation, it checks, if the player can play a
	 * card with same color.
	 * 
	 * @see validate
	 */
	public boolean maFarbu(int cid, List<Integer> h) {
		return (false);
	}

	public boolean maFarbu(int cid) {
		return (maFarbu(cid));
	}

	public boolean somForhont() {
		return (false);
	}

	public int tromfCount(Stav s, List<Integer> h) {
		return (0);
	}

	public int tromfCount() {
		return (tromfCount(null, null));
	}

	/**
	 * Checks, if the card is valid (can be played) in current game state. Or in
	 * the given state with given hand.
	 */
	public String validate(int cid, Stav s, List<Integer> h) {
		return (null);
	}

	public String validate(int cid) {
		return (validate(cid, null, null));
	}

	/**
	 * Returns list of cards that are allowed to play. Optional in the given
	 * state and with given hand.
	 */
	public List<Integer> getLegalList(Stav s, List<Integer> h) {
		return (null);
	}

	public List<Integer> getLegalList() {
		return (getLegalList(null, null));
	}

	public void sortHand() {
	}
}
