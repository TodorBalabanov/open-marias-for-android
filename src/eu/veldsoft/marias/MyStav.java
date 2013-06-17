package eu.veldsoft.marias;

import java.util.List;

class MyStav {
	public Player me;

	public Stav stav;

	public List hand[] = new List[3];

	public int talon[] = new int[2];

	public MyStav(Player p) {
	}

	public MyStav(MyStav s) {
	}

	/**
	 * Heuristic evaluation of state.
	 */
	public double evaluate() {
		return (0.0);
	}

	/**
	 * Simulates playing card c in current state and returns new state.
	 */
	public MyStav makeMove(int c) {
		return (null);
	}

	/**
	 * Generates list of all possible states after one move.
	 */
	public List<MyStav> generate() {
		return (null);
	}

	public void checkIntegrity() {
	}

	/**
	 * Return number of turns to end of game.
	 */
	public int getHeight() {
		return (0);
	}
}
