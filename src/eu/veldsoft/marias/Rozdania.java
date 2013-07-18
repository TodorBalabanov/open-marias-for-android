package eu.veldsoft.marias;

import java.util.List;

class Rozdania {

	public Stav stav;

	public boolean quickGame;

	// this flag is true if the time is up
	public boolean fail;

	public Profiler profiler;

	// list of possible distributions
	// format is:
	// 32 bit string of 0s and 1s
	// first 22 bits tells about which cards has which player
	// r[getCardPosInRozdanie(i)] = 1 => hrac vpravo ma kartu i
	// r[getCardPosInRozdanie(i)] = 0 => hrac vlavo ma kartu i alebo je v
	// talone, alebo uz bola, alebo ju mam ja
	// getCardPosInRozdanie je surjekcia 32 -> 22, pre 22 neznamych kariet je to
	// bijekcia
	// zvysnych 10 bitov koduje 2 karty v talone
	public List<Integer> r;

	public List<Integer> positions;

	public List<Integer> hand;

	public int talon[] = new int[2];

	public Rozdania() {
	}

	/**
	 * Rozdanie ma 32 bitov. Prvych 22 hovoria o tom, kto vlastni jednotlive
	 * karty. Na i-tej pozicii je 0 ak ma danu kartu hrac vlavo odo mna, 1, ak
	 * vpravo. 22 bitov je preto, ze sa preskakuju karty, o ktorych viem, ze ich
	 * mam ja. Poslednych 10 bitov koduje 2 karty v talone.
	 */
	public void initPositions() {
	}

	public List<Integer> generujRecursive(List<Integer> universe, int kolko,
			int timeLimit) {
		return (null);
	}

	public void generuj(boolean somForhont, int myID, int timeLimit) {
	}

	public int getCardPosInRozdanie(int c) {
		return (0);
	}

	public int getCardMask(int c) {
		return (0);
	}

	public int getMask(int pos) {
		return (0);
	}

	public static class Pair<K, V> {
		private final K element0;
		private final V element1;

		public static <K, V> Pair<K, V> createPair(K element0, V element1) {
			return new Pair<K, V>(element0, element1);
		}

		public Pair(K element0, V element1) {
			this.element0 = element0;
			this.element1 = element1;
		}

		public K getElement0() {
			return element0;
		}

		public V getElement1() {
			return element1;
		}

	}

	/**
	 * Returns pair (left,right) from rozdanie at por-th position in list r left
	 * is cards that has left player right is cards that has right player
	 * 
	 */
	public Pair<List<Integer>, List<Integer>> getCardsAtRozdanie(int por) {
		return (null);
	}

	/**
	 * Helper function to update isOne and isZero arrays.
	 * 
	 */
	public void p2nemaC(List<Integer> isOne, List<Integer> isZero, int bit,
			int c) {
	}
}
