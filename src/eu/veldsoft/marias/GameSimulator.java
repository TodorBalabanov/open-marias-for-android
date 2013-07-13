package eu.veldsoft.marias;

import java.util.Random;
import java.util.List;
import java.io.OutputStream;

class GameSimulator {
	public Profiler profiler;

	public Stav stav;

	public Player p[] = new Player[3];

	public Random rg;

	public List<Integer> biddingHistory;

	public OutputStream out;

	public GameSimulator() {
	}

	public int getCardMask(int c) {
		return (0);
	}

	/**
	 * Vrati ocakavany result pri danom rozdani pri danom zvolenom tromfe.
	 * Nahodne rozda zvysne karty a odohra hru.
	 * 
	 */
	public double expectedResult(int rozdanie, int tromf, int timeLimit) {
		return (0.0);
	}

	/**
	 * Rozda karty nahodne hracom tak, ze pouzitych 7 sa tam nenachadza.
	 * 
	 */
	public void rozdaj(int rozdanie) {
	}

	public void talon() {
	}

	/**
	 * Manazuje bidding.
	 * 
	 * @param res
	 *            response from player
	 * 
	 */
	public void bid(int res) {
	}
}
