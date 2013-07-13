package eu.veldsoft.marias;

import java.util.List;

class ChooseTromf {
	// i-th bit of the int is whether there is card i in first 7 cards
	public List<Integer> rozdania;

	public GameSimulator gs;

	public ChooseTromf() {
	}

	public int getCardMask(int c) {
		return (0);
	}

	/**
	 * Heuristicky zisti, ci je zrejme, co sa ma zvolit ako tromf uz len z poctu
	 * farieb.
	 * 
	 */
	public boolean jeTromfJasny(int ff[]) {
		return (false);
	}

	/**
	 * Heuristicky zisti, ktore karty su vhodne na volbu tromfa. Potom sa bude
	 * prehladavat strom pre kazdu takuto volbu.
	 * 
	 * @return int obsahuje jednotky pri tych kartach, ktore su vhodne na volbu
	 *         tromfa.
	 * 
	 */
	public int kandidatiNaTromfa(int rozdanie) {
		return (0);
	}

	public void generujRecursive(int rozdanie, int farby[], int ff[]) {
	}

	public void generujRozdania() {
	}

	public String printRozdanie(int rozdanie, boolean detail) {
		return (null);
	}

	public String printRozdanie(int rozdanie) {
		return (printRozdanie(rozdanie, false));
	}

	public void ratajOdRozdania(int rozdanie) {
	}
}
