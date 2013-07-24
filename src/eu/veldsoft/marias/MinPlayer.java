package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for min player AI.
 * 
 * @author Vencislav Medarov
 * @email venci932@gmail.com
 * @date 17 Jul 2013
 */
class MinPlayer extends Player {
	/**
	 * Constructor without parameters.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 17 Jul 2013
	 */
	public MinPlayer() {
		type = "minimalista";
		name = "Minimalista";
	}

	/**
	 * Id of the card which should be played.
	 * 
	 * @return Card id.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 17 Jul 2013
	 */
	public int play() {
		return (pickMin(getLegalList()));
	}

	/**
	 * Talon selection procedure.
	 * 
	 * @return Card id from talon.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 17 Jul 2013
	 */
	public int talon() {
		List<Integer> legal = new ArrayList<Integer>();
		for (Integer c : hand) {
			if (Card.value(c) != "10" && Card.value(c) != "eso"
					&& c != stav.hra.tromf) {

				legal.add(c);
			}
		}
		return (pickMinTwo(legal));
	}

	/**
	 * Bid level selection.
	 * 
	 * @return Bid level.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 17 Jul 2013
	 */
	public int bid() {
		if (stav.hra.flekNaHru == 1) {
			return (4);
		} else {
			return (0);
		}
	}

	/**
	 * Tromf selection.
	 * 
	 * @return Selected tromf.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 17 Jul 2013
	 */
	public int tromf() {
		return (pickMin(hand));
	}

	/**
	 * Select minimum card.
	 * 
	 * @param legal
	 *            ...
	 * @return Min card selected.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 17 Jul 2013
	 */
	public int pickMin(List<Integer> legal) {
		int min = 7;
		for (Integer c : legal) {
			if (c % 8 <= min % 8) {
				min = c;
			}
		}
		return (min);
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
	 * @date 17 Jul 2013
	 */
	public int pickMinTwo(List<Integer> legal) {
		int min1 = pickMin(legal);
		int min2 = 7;
		for (Integer c : legal) {
			if (c != min1 && c % 8 <= min2 % 8) {
				min2 = c;
			}
		}
		return (min2 * 32 + min1);
	}
}
