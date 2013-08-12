package eu.veldsoft.marias;

import java.util.List;
import java.util.ArrayList;

/**
 * Class for smart player AI.
 * 
 * @author Vencislav Medarov
 * @email venci932@gmail.com
 * @date 19 Jul 2013
 */
class SmartPlayer extends Player {
	/**
	 * Constructor without parameters.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 19 Jul 2013
	 */
	public SmartPlayer() {
		type = "smart";
		name = "Smart player";
	}

	/**
	 * Id of the card which should be played.
	 * 
	 * @return Card id.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 19 Jul 2013
	 */
	@Override
	public int play() {
		return pickSmart((getLegalList()));
	}

	/**
	 * Talon selection procedure.
	 * 
	 * @return Card id from talon.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 20 Jul 2013
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
			if (Card.isTromf(farba * 8, stav.hra)) {
				continue;
			}
			/*
			 * Ak mam eso.
			 */
			if (hand.contains(farba * 8 + 7) == true) {
				/*
				 * Ak mam aj 10, tak tuto farbu vobec nezhadzujem.
				 */
				if (hand.contains(farba * 8 + 3) == true) {
					continue;
				}
				/*
				 * Ak mam aj hlasku, tiez nezhadzujem tuto farbu.
				 */
				if (hand.contains(farba * 8 + 5) == true
						&& hand.contains(farba * 8 + 6) == true) {
					continue;
				}
				if (pocetKarietFarby[farba] == 2) {
					/*
					 * Do talonu dam tu jedinu kartu z farby, ktora nie je eso,
					 * takze mi ostane na ruke jedno eso.
					 */
					for (int c = farba * 8; c < farba * 8 + 7; c++) {
						if (hand.contains(c) == true) {
							vyber.add(c);
							legal.remove(c);
						}
					}
				}
			}
		}
		for (int farba = 0; farba < 4; farba++) {
			if (Card.isTromf(farba * 8, stav.hra) == true) {
				continue;
			}
			if (hand.contains(farba * 8 + 3)
					&& hand.contains(farba * 8 + 7)
					&& this.chcemHratSedmu()
					&& (hand.contains(farba * 8 + 5) && hand
							.contains(farba * 8 + 6)) == false) {
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
				if (c < min && !Card.isTromf(c, stav.hra)) {
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
	 * @date 20 Jul 2013
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
				if (trhamHlasku.get(c / 8) == true) {
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
					&& Math.log(stav.hra.flekNaSedmu) % 2 == 0) {
				bids |= 2;
			}
			/*
			 * inak flekujem rozumne (toto je pripad, ked ju hram asi ja...ak
			 * nie, tak uz davno som dal flek) na RE potrebujem aspon 5 tromfov
			 * na BOTY aspon 7 tromfov
			 */
			if (stav.hra.flekNaSedmu >= 2
					&& Math.log(stav.hra.flekNaSedmu) + 4 <= tromf) {
				bids |= 2;
			}
		} else {
			/*
			 * sedmu fleknem, ak mam aspon 4 tromfy na tutti treba 5 tromfov
			 */
			if (stav.hra.flekNaSedmu >= 1
					&& Math.log(stav.hra.flekNaSedmu) + 8 <= 2 * tromf) {
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
	 * @date 19 Jul 2013
	 */
	public int tromf() {
		return (pickMax(hand));
	}

	/**
	 * Select smart card.
	 * 
	 * @param legal
	 *            ...
	 * @return ...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 19 Jul 2013
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
				if (stav.kopa.size() == 0) {
					if (countNoTromf > 0) {
						for (Integer c1 : legal) {
							if (Card.isTromf(c1, stav.hra)) {
								legal.remove(c1);
							}
						}
					}
					return (pickMin(legal));
				}
			}
		} else {
			return (pickMin(legal));

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
	 * @date 19 Jul 2013
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
	 * @date 19 Jul 2013
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
	 * @date 19 Jul 2013
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
	 * @date 19 Jul 2013
	 */
	public int pickMaxTwo(List<Integer> legal) {
		int max1 = pickMax(legal);
		int max2 = 0;
		for (Integer c : legal) {
			if (c != max1 && c % 8 >= max2 % 8) {
				max2 = c;
			}
		}
		return (max2 * 32 + max1);
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
	 * @date 19 Jul 2013
	 */
	public boolean chcemHratSedmu() {
		int[] pocetKarietFarby = { 0, 0, 0, 0 };
		for (Integer c : hand) {
			pocetKarietFarby[c / 8]++;
		}
		if (hand.contains(stav.hra.tromf7()) == false) {
			return (false);
		}
		/*
		 * If mam3 and less trumps, nehram Bingo.
		 */
		if (pocetKarietFarby[stav.hra.tromf7() / 8] < 4) {
			return (false);
		}
		/*
		 * If I have 5 or more trumps, I play Bingo.
		 */
		if (pocetKarietFarby[stav.hra.tromf7() / 8] > 4) {
			return (true);
		}
		/*
		 * If I have 4 trumps, we must have of each other color at least 1 card.
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
