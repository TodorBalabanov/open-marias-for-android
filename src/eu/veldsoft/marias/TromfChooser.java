package eu.veldsoft.marias;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class TromfChooser {
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(TromfChooser.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * 
	 */
	protected Map<Integer, Integer> table;

	/**
	 * Co ma momentalne na ruke.
	 */
	protected int rozdanie;

	/**
	 * Mapping of colors to get canonical representation.
	 */
	protected int mapColors[] = new int[4];

	/**
	 * Param cards*: 8bit representation of cards in color.
	 * 
	 * @param count1
	 * 
	 * @param count2
	 * 
	 * @param cards1
	 * 
	 * @param cards2
	 * 
	 * @return
	 * 
	 */
	protected boolean colorIsLexicoLess(int count1, int count2, int cards1,
			int cards2) {
		// TODO To be done by Pesho.
		return (false);
	}

	public TromfChooser() {
	}

	public void init() {
	}

	/**
	 * Hand setter.
	 * 
	 * @param hand
	 *            Cards for hand.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 20 Jul 2013
	 */
	public void setHand(List<Integer> hand) {
		rozdanie = 0;
		mapColors[3] = 3;

		/*
		 * Convert list of cards to canonical representation of rozdanie.
		 */
		int pocty[] = new int[] { 0, 0, 0, 0 };

		/*
		 * 8 bits represent 8 cards whether are present in particular color.
		 */
		int kartyVoFarbe[] = new int[] { 0, 0, 0, 0 };

		for (int i = 0; i < hand.size(); i++) {
			pocty[hand.get(i) / 8]++;
			kartyVoFarbe[hand.get(i) / 8] |= (1 << (7 - hand.get(i) % 8));
		}

		if (colorIsLexicoLess(pocty[0], pocty[1], kartyVoFarbe[0],
				kartyVoFarbe[1])) {
			if (colorIsLexicoLess(pocty[1], pocty[2], kartyVoFarbe[1],
					kartyVoFarbe[2])) {
				mapColors[0] = 0;
				mapColors[1] = 1;
				mapColors[2] = 2;
			} else {
				if (colorIsLexicoLess(pocty[0], pocty[2], kartyVoFarbe[0],
						kartyVoFarbe[2])) {
					mapColors[0] = 0;
					mapColors[1] = 2;
					mapColors[2] = 1;
				} else {
					mapColors[0] = 1;
					mapColors[1] = 2;
					mapColors[2] = 0;
				}
			}
		} else {
			if (colorIsLexicoLess(pocty[0], pocty[2], kartyVoFarbe[0],
					kartyVoFarbe[2])) {
				mapColors[0] = 1;
				mapColors[1] = 0;
				mapColors[2] = 2;
			} else {
				if (colorIsLexicoLess(pocty[1], pocty[2], kartyVoFarbe[1],
						kartyVoFarbe[2])) {
					mapColors[0] = 2;
					mapColors[1] = 0;
					mapColors[2] = 1;
				} else {
					mapColors[0] = 2;
					mapColors[1] = 1;
					mapColors[2] = 0;
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			rozdanie |= (kartyVoFarbe[i] << (8 * (3 - mapColors[i])));
		}
	}

	/**
	 * Choose tromf.
	 * 
	 * @return ....
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 26 Jul 2013
	 */
	public int chooseTromf() {
		// qDebug() << "TromfChooser::chooseTromf(): rozdanie=" <<
		// this->rozdanie;

		/*
		 * Needs to be mapped back with mapColors.
		 */
		int chosenTromf = table.put(rozdanie, -1);

		for (int i = 0; i < 4; i++) {
			if (mapColors[i] == chosenTromf / 8) {
				return (i * 8 + chosenTromf % 8);
			}
		}

		LOGGER.info("TromfChooser Fatal: no color mapped to color"
				+ (chosenTromf / 8));

		return (-1);
	}
}
