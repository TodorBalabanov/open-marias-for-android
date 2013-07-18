package eu.veldsoft.marias;

import java.util.Map;
import java.util.List;

class TromfChooser {
	protected Map<Integer, Integer> table;

	// co ma momentalne na ruke
	protected int rozdanie;

	// mapping of colors to get canonical representation
	protected int mapColors[] = new int[4];

	// param cards*: 8bit representation of cards in color
	protected boolean colorIsLexicoLess(int count1, int count2, int cards1,
			int cards2) {
		return (false);
	}

	public TromfChooser() {
	}

	public void init() {
	}

	public void setHand(List<Integer> hand) {
	}

	public int chooseTromf() {
		return (0);
	}
}
