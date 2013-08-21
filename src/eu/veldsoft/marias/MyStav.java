package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ...
 * 
 * @author Vencislav Medarov
 * @email venci932@gmail.com
 * @date 28 Jul 2013
 */
class MyStav {
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(MyStav.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * My own player.
	 */
	public Player me;

	/**
	 * ...
	 */
	public Stav stav;

	/**
	 * Players hands.
	 */
	public List hand[] = new List[3];
	{
		for(List element : hand){
			element = new ArrayList<Integer>();
		}
	}

	/**
	 * 
	 */
	public int talon[] = new int[2];

	/**
	 * Constructor.
	 * 
	 * @param p
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 28 Jul 2013
	 */
	public MyStav(Player p) {
		me = p;
	}

	/**
	 * ....
	 * 
	 * @param s
	 *            ...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 28 Jul 2013
	 */
	public MyStav(MyStav s) {
		me = s.me;
		stav = s.stav;
		// TODO Deep copy should be done.
		for (int i = 0; i < 3; i++) {
			hand[i] = s.hand[i];
		}
	}

	/**
	 * Heuristic evaluation of state.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 28 Jul 2013
	 */
	public double evaluate() {
		return (Evaluator.evaluate(this));
	}

	/**
	 * Simulates playing card c in current state and returns new state.
	 * 
	 * @param c
	 *            playing card
	 * 
	 * @return new state.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 28 Jul 2013
	 */
	public MyStav makeMove(int c) {
		MyStav s = (this);
		if (me.validate(c, stav, hand[stav.id]) != "") {
			LOGGER.info("Error in searching, card " + String.valueOf(c)
					+ "is not valid");
			return (s);
		}
		if (Card.value(c) == "hornik" && s.hand[s.stav.id].contains(c + 1)) {
			int hlaska = 20;
			if (Card.isTromf(c, s.stav.hra)) {
				hlaska = 40;
				stav.hlaska(hlaska);
			}
		}
		s.stav.cHist.add(c);
		for (int i = 0; i < 3; i++) {
			s.hand[i].remove(c);
			s.stav.kopa.add(c);
			s.stav.dalsi();
			if (s.stav.kopa.size() == 3) {
				s.stav.kolo++;
				s.stav.vysid = s.stav.trick();
				s.stav.id = s.stav.vysid;
				s.stav.pHist.add(s.stav.id);
				s.stav.kopa.clear();
			}
		}

		return (s);
	}

	/**
	 * Generates list of all possible states after one move.
	 * 
	 * @return new state.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 29 Jul 2013
	 */
	public List<MyStav> generate() {
		List<MyStav> qlms = new ArrayList<MyStav>();
		List<Integer> legal = me.getLegalList(stav, hand[stav.id]);

		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				// TODO Check for correct elements order.
				if (Card.less(a, b) == true) {
					return (-1);
				} else if (Card.greater(a, b) == true) {
					return (+1);
				}

				return (0);
			}
		};
		
		Collections.sort(legal, comparator);

		/*
		 * OREZAVANIE - NEMUSIM PREHLADAVAT VETVY, KDE DAM 8,9,D,H,K
		 */
		List<Integer> legal2 = new ArrayList<Integer>();

		/*
		 * ALE TO ROBIM, LEN KED MAM PRED SEBOU VELA ROBOTY - HLBKA ASPON 4
		 */
		if (hand[stav.id].size() < 2) {
			legal2 = legal;
		} else {
			/*
			 * Token that will always push the last legal card.
			 */
			legal.add(32);

			/*
			 * gulova 7
			 */
			int lastLegal = legal.get(0);
			for (int i = 1; i < legal.size(); i++) {
				if (Card.equalColor(lastLegal, legal.get(i)) == true) {
					if (Card.isFatty(legal.get(i)) == true) {
						legal2.add(lastLegal);
						lastLegal = legal.get(i);
					} else if (lastLegal % 8 == 0) {
						legal2.add(lastLegal);
						lastLegal = legal.get(i);
					} else {
						/*
						 * THE CASE OF AT LEAST TWO OF 8,9,D,H,K CONSECUTIVE
						 */
						for (int j = Card.plus1(lastLegal, true); j != legal
								.get(i); j = Card.plus1(j)) {
							if (hand[(stav.id + 1) % 3].contains(j)
									|| hand[(stav.id + 2) % 3].contains(j) == true) {
								/*
								 * EXCEPT IF OTHER PLAYER HAS A CARD BETWEEN
								 */
								legal2.add(lastLegal);
								lastLegal = legal.get(i);
								break;
							}
						}

					}
				} else {
					legal2.add(lastLegal);
					lastLegal = legal.get(i);
				}
			}
		}

		for (Integer c : legal2) {
			qlms.add(makeMove(c));
		}
		return (qlms);
	}

	/**
	 * Check for integrity.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 29 Jul 2013
	 */
	public void checkIntegrity() {
		int totalCards = stav.cHist.size() + 2 + hand[0].size()
				+ hand[1].size() + hand[2].size();
		String s = "hands: ";
		for (int i = 0; i < 3; i++) {
			s += "(";

			for (int j = 0; j < hand[i].size(); j++) {
				s += Card.title((Integer) hand[i].get(j)) + ", ";
			}

			s += ") ";
		}

		if (totalCards != 32) {
			LOGGER.info(s);
			LOGGER.info("talon: " + Card.title(talon[0]) + Card.title(talon[1]));
			LOGGER.info("INTEGRITY CHECK ERROR: total cards " + totalCards);
		}
	}

	/**
	 * Return number of turns to end of game.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 29 Jul 2013
	 */
	public int getHeight() {
		return (hand[0].size() + hand[1].size() + hand[2].size());
	}
}
