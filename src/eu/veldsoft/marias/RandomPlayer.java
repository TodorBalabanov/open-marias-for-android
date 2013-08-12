package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		Random rg = new Random();
		type = "random";
		name = "Randomak";
		rg.setSeed(-1);
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
		Random rg = new Random();
		int bids = 0;
		if (hand.contains(stav.hra.tromf7())) {
			if (rg.nextInt((stav.hra.flekNaSedmu + 1) + 3) == 0) {
				bids |= 2;
			}
		}
		if (rg.nextInt((stav.hra.flekNaStovku + 1) + 3) == 0) {
			bids |= 1;
		}
		if (rg.nextInt(stav.hra.flekNaHru + 1) == 0) {
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
		Random rg = new Random();
		return (legal.indexOf(rg.nextInt(legal.size())));
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
		Random rg = new Random();
		int i = rg.nextInt(legal.size());
		int k = legal.get(i);
		legal.remove(i);
		int j = rg.nextInt(legal.size());
		return (k * 32 + legal.get(j));
	}
}
