package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for minmax player AI.
 * 
 * @author Vencislav Medarov
 * @email venci932@gmail.com
 * @date 01 Aug 2013
 */
class MinimaxPlayer extends Player {

	/**
	 *
	 */
	private final static int TIME_LIMIT_GENERATION = 100;

	/**
	 * 
	 */
	private final static int TIME_LIMIT_SEARCH = 200;

	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(MinimaxPlayer.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * 
	 */
	public Rozdania rozdania;

	/**
	 * My last card.
	 */
	public int myLastCardInHistory;

	/**
	 * 
	 */
	public List<Integer> states;

	/**
	 * Players rewards.
	 */
	public double rewards[] = new double[32];

	/**
	 * This flag is true, if the searching would take too much time.
	 */
	public boolean fail;

	/**
	 * 
	 */
	public int kolkoSaStihlo;

	/**
	 * 
	 */
	public int cutoffLevel;

	/**
	 * Tromf chooser reference.
	 */
	public TromfChooser tromfChooser;

	/**
	 * Constructor.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 01 Aug 2013
	 */
	public MinimaxPlayer() {
		type = "minimax";
		name = "Minimax player";
	}

	/**
	 * Constructor.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 01 Aug 2013
	 */
	public void init() {
		profiler.start("Minimax init");
		tromfChooser = new TromfChooser();
		tromfChooser.init();
		LOGGER.info("Minimax init: " + profiler.getTime("Minimax init"));

		/*
		 * Trochu paranoidne predpokladam, ze opozicia spolupracuje, iba ke
		 * somforhont. Akonahle uz nie som forhont, tak moj spoluhrac je
		 * zakerny.
		 */
	}

	/**
	 * ...
	 * 
	 * @param ms
	 *            ...
	 * @return...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 01 Aug 2013
	 */
	public boolean isMaximizing(MyStav ms) {
		return (somForhont() == (ms.stav.id == id));
	}

	/**
	 * ...
	 * 
	 * @param ms
	 * 
	 * @param firstLevel
	 * 
	 * @param alpha
	 * 
	 * @return...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 01 Aug 2013
	 */
	public int minimax(MyStav ms, boolean firstLevel, int alpha) {

		Comparator<MyStav> comparator = new Comparator<MyStav>() {
			@Override
			public int compare(MyStav a, MyStav b) {
				// TODO Check for correct elements order.
				if (Evaluator.less(a, b) == true) {
					return (-1);
				} else if (Evaluator.greater(a, b) == true) {
					return (+1);
				}

				return (0);
			}
		};

		if (firstLevel == true) {
			for (int i = 0; i < ms.getHeight() + 1; i++) {
				states.add(0);
				states.get(ms.getHeight() + 1);
			}
		}

		if (ms.getHeight() == cutoffLevel && cutoffLevel > 0) {
			return (int) (Evaluator.evaluate(ms));
		}

		if (profiler.getTime("minimaxSearch") > TIME_LIMIT_SEARCH) {
			fail = true;
			return (0);
		}

		ms.checkIntegrity();
		if (ms.getHeight() == 0) {
			int outcome = ms.stav.results(true);
			return (outcome);
		}

		List<MyStav> gen = ms.generate();
		if (gen.size() == 0 && quickGame == false) {
			LOGGER.info("Generated 0 further states");
		}

		boolean max = isMaximizing(ms);
		int best;
		if (max == true) {
			best = -999999;
		} else {
			best = 999999;
		}

		if (ms.getHeight() > cutoffLevel + 1 && ms.getHeight() % 3 == 1) {
			if (max == true) {
				Collections.sort(gen, comparator);
			} else {
				Collections.sort(gen, comparator);
			}
		}
		
		for (int i = 0; i < gen.size(); i++) {
			int outcome = minimax(gen.get(i), false, best);
			if (fail == true) {
				return (0);
			}

			if (firstLevel == true) {
				List<Integer> hist = gen.get(i).stav.cHist;
				rewards[hist.get(hist.size() - 1)] += outcome;
			}

			if ((outcome - alpha) * (max ? 1 : -1) >= 0) {
				return (outcome);
			}

			if ((outcome - best) * (max ? 1 : -1) > 0) {
				best = outcome;
			}
		}

		return (best);
	}

	/**
	 * A process that shows rewards.
	 * 
	 * @return rewards.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 01 Aug 2013
	 */
	public int processRewards() {
		for (int i = 0; i < 32; i++) {
			rewards[i] /= kolkoSaStihlo;
		}

		int chosen = -1;
		double max = 999999;

		if (somForhont()) {
			max = -999999;
		}

		for (int i = 0; i < hand.size(); i++) {
			if (validate(hand.get(i)) == "") {
				double rew = rewards[hand.get(i)];

				if ((-max > 0) == somForhont() == true) {
					max = rew;
					chosen = hand.get(i);
				}
			}
		}

		if (validate(chosen) == "") {
			String word = String.valueOf(cutoffLevel) + ":reward="
					+ String.valueOf(max) + " card=" + Card.title(chosen);

			if (quickGame == false) {
				LOGGER.info("Minimax time=" + profiler.getTime("minimaxSearch"));
			}
		} else {
			LOGGER.info("MINIMAX FAILED " + Card.title(chosen)
					+ " expected reward=" + String.valueOf(max));
		}

		return (chosen);
	}

	/**
	 * Id of the card which should be played.
	 * 
	 * @return Card id.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 01 Aug 2013
	 */
	public int play() {
		profiler.start("minimaxSearch");
		rozdania.stav = stav;
		rozdania.quickGame = quickGame;
		rozdania.profiler = profiler;

		/*
		 * Toto nastane na zaciatku hry, alebo ked sa zmeni hrac
		 */
		if (rozdania.positions.size() == 0 || stav.kolo == 0) {
			rozdania.hand = hand;
			rozdania.initPositions();
			rozdania.hand = hand;
			rozdania.talon[0] = talonCards[0];
			rozdania.talon[1] = talonCards[1];
		}

		if (stav.kolo > 0) {
			fail = false;
			rozdania.generuj(somForhont(), id, TIME_LIMIT_GENERATION);
			LOGGER.info("Vygenerovalo sa" + rozdania.r.size() + "rozdani"
					+ "za cas" + profiler.getTime("minimaxSearch"));
			if (rozdania.fail) {
				LOGGER.info("Nestihli sa vygenerovat vsetky");
			}

			MyStav ms = new MyStav(this);
			ms.stav = rozdania.stav;
			for (cutoffLevel = 3 * (hand.size() - 1); cutoffLevel >= 0; cutoffLevel -= 3) {
				if (quickGame == false) {
					LOGGER.info("Idem prehladavat, cutoff level=" + cutoffLevel);
				}

				for (int i = 0; i < 32; i++) {
					rewards[i] = 0;
					kolkoSaStihlo = 0;
				}

				for (int i = 0; i < rozdania.r.size(); i++) {
					Rozdania.Pair<List<Integer>, List<Integer>> handsLeftRight = rozdania
							.getCardsAtRozdanie(i);

					ms.hand[id] = hand;
					ms.hand[(id + 1) % 3] = handsLeftRight.getElement0();
					ms.hand[(id + 2) % 3] = handsLeftRight.getElement1();
					ms.talon[0] = rozdania.r.get(i) & 31;
					ms.talon[1] = (rozdania.r.get(i) & 992) / 32;

					states.clear();
					minimax(ms, true, somForhont() ? 999999 : -999999);
					kolkoSaStihlo = i + 1;
					if (fail == true) {
						if (quickGame == false) {
							LOGGER.info("Minimax failed - stihlo sa "
									+ kolkoSaStihlo + "/" + rozdania.r.size());
							break;
						}
					}
				}

				/*
				 * end for each rozdanie
				 */
				LOGGER.info("process rewards");
				int response = processRewards();
				if (response == -1) {
					break;
				}

				if (fail == true) {
					if (kolkoSaStihlo > 1) {
						return (response);
					}
					break;
				}

			}
			/*
			 * end for each cutoff level
			 */
		}
		
		/*
		 * end if stav->kolo>0
		 */
		profiler.stop("minimaxSearch");
		profiler.reset("minimaxSearch");
		return (pickSmart(getLegalList()));
	}

	/**
	 * Talon selection procedure.
	 * 
	 * @return Card id from talon.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 01 Aug 2013
	 */
	public int talon() {
		List<Integer> legal = new ArrayList<Integer>();

		int pocetKarietFarby[] = new int[] { 0, 0, 0, 0 };
		for (Integer c : hand) {
			pocetKarietFarby[c / 8]++;
			if (Card.value(c) != "10" && Card.value(c) != "eso"
					&& c != stav.hra.tromf) {
				legal.add(c);
			}
		}
		int esteDoTalonu = 2;
		List<Integer> vyber = new ArrayList<Integer>();

		for (int farba = 0; farba < 4; farba++) {
			if (Card.isTromf(farba * 8, stav.hra) == true) {
				continue;
			}

			/*
			 * ak mam eso
			 */
			if (hand.contains(farba * 8 + 7)) {
				/*
				 * ak mam aj 10, tak tuto farbu vobec nezhadzujem
				 */
				if (hand.contains(farba * 8 + 3)) {
					continue;
				}

				/*
				 * ak mam aj hlasku, tiez nezhadzujem tuto farbu
				 */
				if (hand.contains(farba * 8 + 5)
						&& hand.contains(farba * 8 + 6)) {
					continue;
				}

				if (pocetKarietFarby[farba] == 2) {
					/*
					 * do talonu dam tu jedinu kartu z farby, ktora nie je eso,
					 * takze mi ostane na ruke jedno eso
					 */
					for (int c = farba * 8; c < farba * 8 + 7; c++) {
						if (hand.contains(c)) {
							vyber.add(c);
							legal.remove(c);
						}
					}
				}
			}
		}

		for (int farba = 0; farba < 4; farba++) {
			if (Card.isTromf(farba * 8, stav.hra)) {
				continue;
			}

			if (!hand.contains(farba * 8 + 3)
					&& !hand.contains(farba * 8 + 7)
					&& !chcemHratSedmu()
					&& !(hand.contains(farba * 8 + 5) && hand
							.contains(farba * 8 + 6))) {

				if (esteDoTalonu >= pocetKarietFarby[farba]) {
					for (int c = farba * 8; c < farba * 8 + 7; c++) {
						if (hand.contains(c)) {
							vyber.add(c);
							legal.remove(c);
						}
					}
				}
			}
		}

		while (esteDoTalonu > 0) {
			int min = 9999;
			for (Integer c : legal) {
				if (c < min && !Card.isTromf(c, stav.hra) == true) {
					min = c;
				}
			}

			if (min != 9999) {
				vyber.add(min);
				legal.remove(min);
				esteDoTalonu--;
			} else {
				min = pickMin(legal);
				vyber.add(min);
				legal.remove(min);
				esteDoTalonu--;
			}
		}

		return (32 * vyber.get(0) + vyber.get(1));
	}

	/**
	 * Bid level selection.
	 * 
	 * @return Bid level.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 02 Aug 2013
	 */
	public int bid() {
		int bids = 0;
		int fatty = 0;
		int tromf = tromfCount();
		int esa = 0;

		List<Boolean> trhamHlasku = new ArrayList<Boolean>();
		trhamHlasku.add(false);
		trhamHlasku.add(false);
		trhamHlasku.add(false);
		trhamHlasku.add(false);

		List<Boolean> mamHlasku = new ArrayList<Boolean>(trhamHlasku);

		for (Integer c : hand) {
			if (Card.isFatty(c) == true) {
				fatty++;
			}

			if (Card.value(c) == "eso") {
				esa++;
			}

			if (Card.value(c) == "hornik" || Card.value(c) == "kral") {
				if (trhamHlasku.get(c / 8)) {
					mamHlasku.set(c / 8, true);
				} else {
					trhamHlasku.set(c / 8, true);
				}
			}
		}

		int bodyZaHlasky = 0;
		for (int i = 0; i < 4; i++) {
			if (mamHlasku.get(i) == true) {
				bodyZaHlasky += 20;
				if (i == stav.hra.tromf / 8) {
					bodyZaHlasky += 20;
				}
			}

			if (trhamHlasku.get(i) == false) {
				bodyZaHlasky -= 10;
				if (i == stav.hra.tromf / 8) {
					bodyZaHlasky -= 10;
				}
			}
		}

		if ((somForhont() ? 9 : 4) * stav.hra.flekNaHru <= bodyZaHlasky / 5
				+ fatty + tromf * tromf * tromf / 6 + 2 * esa) {
			bids |= 4;
		}

		if (hand.contains(stav.hra.tromf7()) == true) {
			/*
			 * Ak mam sedmu, zahlasim ju len ak mam este 2 dalsie tromfy.
			 */
			if (stav.hra.flekNaSedmu == 0 && chcemHratSedmu() == true) {
				bids |= 2;
			}
			/*
			 * Ak mam sedmu a hlasil ju niekto iny, flekujem do nemoty.
			 */
			if (stav.hra.flekNaSedmu >= 1
					&& (int) Math.log(stav.hra.flekNaSedmu) % 2 == 0) {
				bids |= 2;
			}
			/*
			 * Inak flekujem rozumne (toto je pripad, ked ju hram asi ja...ak
			 * nie, tak uz davno som dal flek) na RE potrebujem aspon 5 tromfov.
			 * Na BOTY aspon 7 tromfov.
			 */
			if (stav.hra.flekNaSedmu >= 2
					&& (int) Math.log(stav.hra.flekNaSedmu) + 4 <= tromf) {
				bids |= 2;
			}
		} else {
			/*
			 * Sedmu fleknem, ak mam aspon 4 tromfy Na tutti treba 5 tromfov.
			 */
			if (stav.hra.flekNaSedmu >= 1
					&& (int) Math.log(stav.hra.flekNaSedmu) + 8 <= 2 * tromf) {
				bids |= 2;
			}
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
	 * @date 02 Aug 2013
	 */
	public int tromf() {
		tromfChooser.setHand(hand);
		LOGGER.info("hand set");

		int tromf = tromfChooser.chooseTromf();
		LOGGER.info("chosen tromf: " + tromf);
		if (hand.contains(tromf) == true) {
			return (tromf);
		}

		return (pickMin(hand));
	}

	/**
	 * Select smart card.
	 * 
	 * @param legal
	 *            ...
	 * 
	 * @return Card id.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 02 Aug 2013
	 */
	public int pickSmart(List<Integer> legal) {
		if (legal.size() == 1) {
			return (legal.get(0));
		}

		if (stav.hra.farba == true) {
			/*
			 * Ked hram sedmu, nepustim ju len tak.
			 */
			if ((stav.hra.sedma && somForhont())
					|| (stav.hra.sedmaProti && !somForhont()) == true) {

				if (legal.contains(stav.hra.tromf7()) == true) {
					legal.remove(stav.hra.tromf7());
				}

				if (legal.size() == 1) {
					return (legal.get(0));
				}

				/*
				 * Zratam netromfove karty, ked vychadzam, tak ak mozem, tak nie
				 * tromfom.
				 */
				int countNoTromf = 0;
				for (Integer c : legal) {
					if (Card.isTromf(c, stav.hra) == false) {
						countNoTromf++;
					}
				}

				if (stav.kopa.size() == 0 && countNoTromf > 0) {
					for (Integer c : legal) {
						if (Card.isTromf(c, stav.hra) == true) {
							legal.remove(c);
						}
					}
				}

				return (pickMin(legal));
			} else {
				return (pickMin(legal));
			}
		}
		return (0);
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
	 * @date 02 Aug 2013
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
	 * Select talon.
	 * 
	 * @param legal
	 *            ...
	 * @return Calculated average value.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 02 Aug 2013
	 */
	public int pickTalon(List<Integer> legal) {
		int min1 = pickMin(legal);
		int min2 = 7;

		for (Integer c : legal) {
			if (c != min1 && c % 8 <= min2 % 8) {
				min2 = c;
			}
		}

		return (min2 * 32 + min1);
	}

	/**
	 * ...
	 * 
	 * @param legal
	 *            ...
	 * 
	 * @return Calculated boolean value.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 02 Aug 2013
	 */
	public boolean chcemHratSedmu() {
		int pocetKarietFarby[] = new int[] { 0, 0, 0, 0 };

		for (Integer c : hand) {
			pocetKarietFarby[c / 8]++;
		}

		if (hand.contains(stav.hra.tromf7()) == false) {
			return (false);
		}
		/*
		 * Ak mam3 a menej tromfov, nehram sedmu.
		 */
		if (pocetKarietFarby[stav.hra.tromf7() / 8] < 4) {
			return (false);
		}

		/*
		 * Ak mam 5 a viac tromfov, hram sedmu.
		 */
		if (pocetKarietFarby[stav.hra.tromf7() / 8] > 4) {
			return (true);
		}

		/*
		 * ak mam 4 tromfy, musim mat z kazdej inej farby aspon 1 kartu
		 */
		for (int farba = 0; farba < 4; farba++) {
			if (Card.isTromf(farba * 8, stav.hra) == true) {
				continue;
			}

			if (pocetKarietFarby[farba] == 0) {
				return (false);
			}
		}

		return (true);
	}
}
