package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for random player AI.
 * 
 * @author Vencislav Medarov
 * @email venci932@gmail.com
 * @date 18 Jul 2013
 */
class RandomPlayer extends Player {

	/**
	 * Constructor
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 18 Jul 2013
	 */
	public RandomPlayer() {
		type = "random";
		name = "Randomak";
		MainActivity.PRNG.setSeed(-1);
	}

	/**
	 * Id of the card which should be played.
	 * 
	 * @return Card id.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 18 Jul 2013
	 */
	@Override
	public int play() {
		return (pickRandom(getLegalList()));
	}

	/**
	 * Talon selection procedure.
	 * 
	 * @return Card id from talon.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 18 Jul 2013
	 */
	@Override
	public int talon() {
		List<Integer> legal = new ArrayList<Integer>();
		for (Integer c : hand) {
			if (Card.value(c) != "10" && Card.value(c) != "eso"
					&& c != stav.hra.tromf) {
				legal.add(c);
			}
		}
		return pickRandomTwo(legal);
	}

	/**
	 * Bid level selection.
	 * 
	 * @return Bid level.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 18 Jul 2013
	 */
	@Override
	public int bid() {
		int bids = 0;
		if (hand.contains(stav.hra.tromf7())) {
			if (MainActivity.PRNG.nextInt((stav.hra.flekNaSedmu + 1) + 3) == 0) {
				bids |= 2;
			}
		}
		if (MainActivity.PRNG.nextInt((stav.hra.flekNaStovku + 1) + 3) == 0) {
			bids |= 1;
		}
		if (MainActivity.PRNG.nextInt(stav.hra.flekNaHru + 1) == 0) {
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
	 * @date 17 Jul 2013
	 */
	@Override
	public int tromf() {
		return (pickRandom(hand));
	}

	/**
	 * Select Random card.
	 * 
	 * @param legal
	 *            ...
	 * @return Random card selected.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 17 Jul 2013
	 */
	public int pickRandom(List<Integer> legal) {
		return (legal.indexOf(MainActivity.PRNG.nextInt(legal.size())));
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
	public int pickRandomTwo(List<Integer> legal) {
		int i = MainActivity.PRNG.nextInt(legal.size());
		int k = legal.get(i);
		legal.remove(i);
		int j = MainActivity.PRNG.nextInt(legal.size());
		return (k * 32 + legal.get(j));
	}
}
