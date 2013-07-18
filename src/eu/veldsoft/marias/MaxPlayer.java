package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for max player AI.
 * 
 * @author Vencislav Medarov
 * @email venci932@gmail.com
 * @date 13 Jul 2013
 */
class MaxPlayer extends Player {
	/**
	 * Constructor without parameters.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 13 Jul 2013
	 */
	public MaxPlayer() {
		type = "maximalista";
		name = "Maximalista";
	}

	/**
	 * Id of the card which should be played.
	 * 
	 * @return Card id.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 13 Jul 2013
	 */
	public int play() {
		return (pickMax(getLegalList()));
	}

	/**
	 * Talon selection procedure.
	 * 
	 * @return Card id from talon.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 13 Jul 2013
	 */
	public int talon() {
		List<Integer> legal = new ArrayList<Integer>();

		for (Integer c : hand) {
			if (Card.value(c) != "10" && Card.value(c) != "eso"
					&& c != stav.hra.tromf) {
				legal.add(c);
			}
		}
		return (pickMaxTwo(legal));

	}

	/**
	 * Bid level selection.
	 * 
	 * @return Bid level.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 14 Jul 2013
	 */
	public int bid() {
		int bids = 0;

		if (hand.contains(stav.hra.tromf7()) && stav.hra.flekNaSedmu < 16) {
			bids |= 2;
		}

		if (stav.hra.flekNaHru < 4) {
			bids |= 4;
		}

		return (bids);

	}

	/**
	 * Tromf selection.
	 * 
	 * @return Selected tromf.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 14 Jul 2013
	 */
	public int tromf() {
		return (pickMax(hand));
	}

	/**
	 * Select maximum card.
	 * 
	 * @param legal
	 *            ...
	 * 
	 * @return Max card selected.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 14 Jul 2013
	 */
	public int pickMax(List<Integer> legal) {
		int max = 0;

		for (Integer c : legal) {
			if (c % 8 >= max % 8) {
				max = c;
			}
		}

		return (max);
	}

	/**
	 * ...
	 * 
	 * @param legal
	 *            ...
	 * 
	 * @return Calculated average value.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 14 Jul 2013
	 */
	public int pickMaxTwo(List<Integer> legal) {
		int max2 = 0;
		int max1 = pickMax(legal);

		for (Integer c : legal) {
			if (c != max1 && c % 8 >= max2 % 8) {
				max2 = c;
			}
		}

		return (max2 * 32 + max1);
	}
}
