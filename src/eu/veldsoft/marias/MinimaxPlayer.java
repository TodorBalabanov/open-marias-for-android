package eu.veldsoft.marias;

import java.util.List;

class MinimaxPlayer extends Player {
	public Rozdania rozdania;

	public int myLastCardInHistory;

	public List<Integer> states;

	public double rewards[] = new double[32];

	// this flag is true, if the searching would take too much time
	public boolean fail;

	public int kolkoSaStihlo;

	public int cutoffLevel;

	public TromfChooser tromfChooser;

	// temp variable only for debugging of evaluator
	// public Datasets evals;

	public MinimaxPlayer() {
	}

	public void init() {
	}

	public boolean isMaximizing(MyStav ms) {
		return (false);
	}

	public int minimax(MyStav ms, boolean firstLevel, int alpha) {
		return (0);
	}

	public int processRewards() {
		return (0);
	}

	public int play() {
		return (0);
	}

	public int talon() {
		return (0);
	}

	public int bid() {
		return (0);
	}

	public int tromf() {
		return (0);
	}

	public int pickSmart(List<Integer> legal) {
		return (0);
	}

	public int pickMin(List<Integer> legal) {
		return (0);
	}

	public int pickTalon(List<Integer> legal) {
		return (0);
	}

	public boolean chcemHratSedmu() {
		return (false);
	}
}
