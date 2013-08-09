package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Card deal.
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 04 Jul 2013
 */
class Rozdania {
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(Rozdania.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * 
	 */
	public Stav stav;

	/**
	 * 
	 */
	public boolean quickGame;

	/**
	 * this flag is true if the time is up
	 */
	public boolean fail;

	/**
	 * 
	 */
	public Profiler profiler;

	/**
	 * list of possible distributions format is: 32 bit string of 0s and 1s
	 * first 22 bits tells about which cards has which player
	 * r[getCardPosInRozdanie(i)] = 1 => hrac vpravo ma kartu i
	 * r[getCardPosInRozdanie(i)] = 0 => hrac vlavo ma kartu i alebo je v
	 * talone, alebo uz bola, alebo ju mam ja getCardPosInRozdanie je surjekcia
	 * 32 . 22, pre 22 neznamych kariet je to bijekcia zvysnych 10 bitov koduje
	 * 2 karty v talone
	 */
	public List<Integer> r;

	/**
	 * 
	 */
	public List<Integer> positions;

	/**
	 * 
	 */
	public List<Integer> hand;

	/**
	 * 
	 */
	public int talon[] = new int[2];

	/**
	 * Rozdanie ma 32 bitov. Prvych 22 hovoria o tom, kto vlastni jednotlive
	 * karty. Na i-tej pozicii je 0 ak ma danu kartu hrac vlavo odo mna, 1, ak
	 * vpravo. 22 bitov je preto, ze sa preskakuju karty, o ktorych viem, ze ich
	 * mam ja. Poslednych 10 bitov koduje 2 karty v talone.
	 */
	public void initPositions() {
		// TODO To be done by ...
	}

	/**
	 * Recursive generation of ...
	 * 
	 * @param universe
	 *            ...
	 * @param kolko
	 *            ...
	 * @param timeLimit
	 *            ...
	 * 
	 * @return List of ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 26 Jul 2013
	 */
	public List<Integer> generujRecursive(List<Integer> universe, int kolko,
			int timeLimit) {
		List<Integer> result = new ArrayList<Integer>();

		if (kolko == 0) {
			result.add(0);

			return (result);
		}

		if (profiler.getTime("minimaxSearch") > timeLimit) {
			fail = true;
			return (result);
		}

		List<Integer> universe2 = universe;
		for (int i = 0; i < universe.size() - kolko + 1; i++) {
			universe2.remove(0);

			List<Integer> result2 = generujRecursive(universe2, kolko - 1,
					timeLimit);

			if (fail == true) {
				return (result);
			}

			for (int j = 0; j < result2.size(); j++) {
				result2.set(j, result2.get(j) | getCardMask(universe.get(i)));
			}

			result.addAll(result2);
		}

		return result;
	}

	/**
	 * Generate ...
	 * 
	 * @param somForhont
	 *            ...
	 * @param myID
	 *            ...
	 * @param timeLimit
	 *            ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 26 Jul 2013
	 */
	public void generuj(boolean somForhont, int myID, int timeLimit) {
		/*
		 * Kolko kariet ma hrac vpravo = kolko jednotiek ma byt v rozdani.
		 */
		int rightCards = 10 - ((stav.cHist.size() + 2) / 3);

		List<Integer> isOne = new ArrayList<Integer>();
		List<Integer> forceIsOne = new ArrayList<Integer>();
		List<Integer> isZero = new ArrayList<Integer>();

		/*
		 * SPRACOVAVAM TROJICE KARIET V STICHOCH, ABY SOM ZISTIL INFORMACIE TYPU
		 * "HRAC VPRAVO NEMA KARTU x"
		 */
		for (int kolo = 0; 3 * kolo < stav.cHist.size() - 1; kolo++) {
			// qDebug() << "analyzing round " << kolo << "/" <<
			// stav.cHist.size();

			int A = stav.cHist.get(kolo * 3);
			int B = stav.cHist.get(kolo * 3 + 1);
			int C;

			if (stav.cHist.size() > kolo * 3 + 2) {
				C = stav.cHist.get(kolo * 3 + 2);
			} else {
				C = -1;
			}

			int P1;
			if (kolo != 0) {
				P1 = stav.pHist.get(kolo - 1);
			} else {
				P1 = stav.forhont;
			}

			int P2 = (P1 + 1) % 3;
			int P3 = (P2 + 1) % 3;
			// qDebug() << "A=" << A << "B=" << B << "C=" << C;
			// qDebug() << "P1=" << P1 << "P2=" << P2 << "P3=" << P3;

			if (myID != P2) {
				// qDebug() << "myID!=P2";

				int P2Bit;
				if ((3 + myID - P2) % 3 == 1) {
					/*
					 * P2 je vpravo odo mna.
					 */
					P2Bit = 1;
				} else {
					P2Bit = 0;
				}

				if (Card.equalColor(B, A)) {
					// qDebug() << "Card.equalColor(B,A)";

					if (Card.stronger(B, A, stav.hra) == false) {
						// qDebug() << "!Card.stronger(B,A,stav.hra)";

						/*
						 * P2 nema A+1..<(A/8)*8+8;
						 */
						for (int i = Card.plus1(A); i % 8 != 0; i = Card
								.plus1(i)) {
							p2nemaC(isOne, isZero, P2Bit, i);
						}
					}

				} else {
					// qDebug() << "vetva 3";

					if (Card.isTromf(B, stav.hra)) {
						// qDebug() << "vetva 4";
						/*
						 * P2 nema A/8*8...<A/8*8+8;
						 */
						for (int i = A / 8 * 8; i < A / 8 * 8 + 8; i = Card
								.plus1(i)) {
							p2nemaC(isOne, isZero, P2Bit, i);
						}
					} else {
						// qDebug() << "vetva 5";
						/*
						 * P2 nema A/8*8..<A/8*8+8;
						 */
						for (int i = A / 8 * 8; i < A / 8 * 8 + 8; i = Card
								.plus1(i)) {
							p2nemaC(isOne, isZero, P2Bit, i);
						}

						// P2 nema tromf stav.hra.tromf7()
						// ..stav.hra.tromf7()+7
						for (int i = stav.hra.tromf7(); i < stav.hra.tromf7() + 8; i = Card
								.plus1(i)) {
							p2nemaC(isOne, isZero, P2Bit, i);
						}
					}
				}
			}

			// else qDebug() << "myID==P2";

			if (myID != P3 && C != -1) {
				// qDebug() << "myID!=P3 && C!=-1";

				int P3Bit;
				if ((3 + myID - P3) % 3 == 1) {
					/*
					 * P3 je vpravo odo mna.
					 */
					P3Bit = 1;
				} else {
					P3Bit = 0;
				}

				if (Card.equalColor(C, A)) {
					// qDebug() << "vetva 6";
					if (Card.equalColor(B, A)) {
						if (stav.veduca(kolo) != C) {
							// qDebug() << "vetva 6";
							/*
							 * P3 nema stav.veduca(kolo)..%8!=0;
							 */
							for (int i = Card.plus1(stav.veduca(kolo)); i % 8 != 0; i = Card
									.plus1(i)) {
								this.p2nemaC(isOne, isZero, P3Bit, i);
							}
						}
						// else qDebug() << "veduca=" << stav.veduca(kolo);
					}
				} else {
					// qDebug() << "vetva 7";
					if (Card.isTromf(C, stav.hra)) {
						if (stav.veduca(kolo) == C) {
							// P3 nema A/8*8...<A/8*8+8
							for (int i = A / 8 * 8; i < A / 8 * 8 + 8; i = Card
									.plus1(i)) {
								this.p2nemaC(isOne, isZero, P3Bit, i);
							}
						} else {
							// qDebug() << "vetva 8";
							/*
							 * A nie je veduca, lebo A nie je tromf a C je tromf
							 * B je veduca P3 nema A/8*8...<A/8*8+8 P3 nema
							 * B+1..%8!=0
							 */
							for (int i = A / 8 * 8; i < A / 8 * 8 + 8; i = Card
									.plus1(i)) {
								this.p2nemaC(isOne, isZero, P3Bit, i);
							}
							for (int i = Card.plus1(B); i % 8 != 0; i = Card
									.plus1(i)) {
								this.p2nemaC(isOne, isZero, P3Bit, i);
							}
						}
					} else {
						// qDebug() << "vetva 9";
						/*
						 * P3 nema A/8*8..<A/8*8+8 P3 nema tromf
						 * stav.hra.tromf7() ..stav.hra.tromf7()+7
						 */
						for (int i = A / 8 * 8; i < A / 8 * 8 + 8; i = Card
								.plus1(i)) {
							this.p2nemaC(isOne, isZero, P3Bit, i);
						}
						for (int i = stav.hra.tromf7(); i < stav.hra.tromf7() + 8; i = Card
								.plus1(i)) {
							this.p2nemaC(isOne, isZero, P3Bit, i);
						}
					}
				}
			}

			// else qDebug() << "! myID!=P3 && C!=-1 ///" << myID << " " << P3
			// << " " << C;
			// qDebug() << isOne << isZero;
		}

		/*
		 * PRIPRAVA UNIVERZA, DAVAM TAM LEN TIE KARTY, O KTORYCH NEVIEM POVEDAT
		 * NIC ROZHODOVACI STROM V PRILOZENOM OBRAZKU
		 */
		List<Integer> universe = new ArrayList<Integer>();

		/*
		 * Kolko sme usetrili tym, ze sme pozreli historiu co kto daval.
		 */
		int improved = 0;

		/*
		 * kolko kariet prisudim dodatocne hracovi vpravo (potom sa to odrata od
		 * rightCards)
		 */
		int addedToRight = 0;

		for (int i = 0; i < 32; i++) {
			if (hand.contains(i)) {
				continue;
			}

			if (stav.cHist.contains(i)) {
				continue;
			}

			if (somForhont) {
				if (talon[0] == i || talon[1] == i) {
					continue;
				} else if (isOne.contains(i)) {
					addedToRight++;
				}
			}

			/*
			 * Ten co ukazal tromfa ho naozaj ma.
			 */
			if (i == stav.hra.tromf) {
				if (somForhont == false) {
					if ((myID + 1) % 3 == stav.forhont) {
						/*
						 * Forhont je vlavo.
						 */
						continue;
					} else {
						/*
						 * Forhont je vpravo.
						 */
						isOne.add(i);
						forceIsOne.add(i);
						addedToRight++;
						continue;
					}
				}
			}

			if (i == stav.hra.tromf7() && somForhont == false) {
				if ((stav.hra.sedma && ((myID + 1) % 3 == stav.forhont))
						|| (stav.hra.sedmaProti && ((myID + 2) % 3 == stav.forhont))) {
					/*
					 * Ten co je vlavo odo mna hlasil sedmu => ma ju na ruke.
					 */
					continue;
				} else if ((stav.hra.sedmaProti && ((myID + 1) % 3 == stav.forhont))
						|| (stav.hra.sedma && ((myID + 2) % 3 == stav.forhont))) {
					/*
					 * Sedmu hlasil ten vpravo odo mna.
					 */
					isOne.add(i);
					forceIsOne.add(i);
					addedToRight++;
					continue;
				}
			}

			if ((isOne.contains(i) || isZero.contains(i)) == false) {
				/*
				 * Ak neviem, kde ta karta je, alebo ak viem ze ju nema ten
				 * vlavo a moze byt v talone.
				 */
				universe.add(i);
			} else if (isOne.contains(i) == true && somForhont == false
					&& Card.isFatty(i) == false) {
				/*
				 * Ak neviem, kde ta karta je, alebo ak viem ze ju nema ten
				 * vlavo a moze byt v talone.
				 */
				universe.add(i);
			} else {
				if (isOne.contains(i) == true) {
					if (somForhont == false && Card.isFatty(i) == true) {
						addedToRight++;
					}
				}
				improved++;
				continue;
			}
		}

		if (quickGame == false) {
			LOGGER.info("isOne: " + isOne);
			LOGGER.info("forceIsOne: " + forceIsOne);
			LOGGER.info("isZero: " + isZero);
			LOGGER.info("Universe: " + universe);
			LOGGER.info("Odstranilo sa: " + improved);
			LOGGER.info("RightCards: " + rightCards);
			LOGGER.info("Added to right: " + addedToRight);
		}

		fail = false;
		if (rightCards >= addedToRight) {
			r = generujRecursive(universe, rightCards - addedToRight, timeLimit);
			if (!quickGame) {
				if (fail)
					LOGGER.info("recursive generation fail - TLE");
				else
					LOGGER.info("recursive generated: " + r.size() + ", time="
							+ profiler.getTime("minimaxSearch"));
			}
		} else {
			if (quickGame == false) {
				LOGGER.info("recursive generation fail - bug 226");
				LOGGER.info("cHist: " + stav.cHist);
				LOGGER.info("pHist: " + stav.pHist);
				LOGGER.info("my ID: " + stav.id);
				LOGGER.info("hand: " + hand);
				LOGGER.info("isOne: " + isOne);
				LOGGER.info("isZero: " + isZero);
				if (somForhont) {
					LOGGER.info("talon: " + talon[0] + talon[1]);
				}
			}
			fail = true;
		}

		/*
		 * Povolim aj dlhsie prehladavanie, dolezite je, aby vsetky vygenerovane
		 * boli uplne aj s talonmi.
		 */
		// if(fail)return;

		/*
		 * MAM VYGENEROVANE ROZDANIA, DOPLNIM TAM INFORMACIE, KED SOM VEDEL, ZE
		 * KARTU MA HRAC VPRAVO
		 */
		for (int j = 0; j < isOne.size(); j++) {
			/*
			 * Pre vsetky karty, ktore urcite nema zero, ale one.
			 */
			if (forceIsOne.contains(j) == false) {
				if (hand.contains(isOne.get(j))) {
					continue;
				}

				if (stav.cHist.contains(isOne.get(j)) == true) {
					continue;
				}

				if (somForhont == true) {
					if (talon[0] == j || talon[1] == j) {
						continue;
					}
				}

				/*
				 * Nemastne nepridavam, lebo mozu byt v talone.
				 */
				if ((somForhont == false)
						&& (Card.isFatty(isOne.get(j)) == false)) {
					continue;
				}
			}

			for (int i = 0; i < r.size(); i++) {
				r.set(i, r.get(i) | getCardMask(isOne.get(j)));
			}
		}

		/*
		 * AK SOM FORHONT, PRIDAM K ROZDANIAM ZNAMY TALON
		 */
		if (somForhont == true) {
			int b = talon[0] * 32 + talon[1];
			for (int i = 0; i < r.size(); i++) {
				r.set(i, r.get(i) | b);
			}
		} else {
			/*
			 * AK NIE SOM FORHONT, MUSIM VYGENEROVAT VSETKY MOZNE TALONY
			 */

			/*
			 * Temp list with talons.
			 */
			List<Integer> rr = new ArrayList<Integer>();
			for (int i = 0; i < r.size(); i++) {
				for (int j = 0; j < 30; j++) {
					/*
					 * Vsetky moznosti, prvej karty v talone, posledne dve su
					 * mastne.
					 */
					if (hand.contains(j) == true) {
						continue;
					}

					if (stav.cHist.contains(j) == true) {
						continue;
					}

					/*
					 * Ma ju hrac napravo.
					 */
					if ((r.get(i) & getCardMask(j)) != 0) {
						continue;
					}

					/*
					 * Je mastna.
					 */
					if (j % 4 == 3) {
						continue;
					}

					// if(profiler.getTime("minimaxSearch")>timeLimit){fail=true;return;}

					/*
					 * 31 je mastna.
					 */
					for (int k = j + 1; k < 31; k++) {
						if (hand.contains(k) == true) {
							continue;
						}

						if (stav.cHist.contains(k) == true) {
							continue;
						}

						/*
						 * Ma ju hrac napravo.
						 */
						if ((r.get(i) & getCardMask(k)) != 0) {
							continue;
						}

						/*
						 * Je mastna.
						 */
						if (k % 4 == 3) {
							continue;
						}

						/*
						 * V talone je j a k.
						 */
						rr.add(r.get(i) | ((j % 32) * 32 + k % 32));
					}
				}
			}

			r.clear();
			r.addAll(rr);
			rr.clear();
		}
	}

	/**
	 * Get position of the card in the deck.
	 * 
	 * @param c
	 *            Card index.
	 * 
	 * @return Card postion.
	 * 
	 * @author Kalin VLadimirov
	 * @email kalin06_@abv.bg
	 * @date 27 Jul 2013
	 */
	public int getCardPosInRozdanie(int c) {
		return positions.get(c);
	}

	/**
	 * Get card mask.
	 * 
	 * @param c
	 *            Card index.
	 * 
	 * @return Card mask.
	 * 
	 * @author Kalin VLadimirov
	 * 
	 * @email kalin06_@abv.bg
	 * 
	 * @date 27 Jul 2013
	 */
	public int getCardMask(int c) {
		return (1 << (31 - getCardPosInRozdanie(c)));
	}

	/**
	 * Get card mask at specific postion.
	 * 
	 * @param pos
	 *            Card position which is different than card index.
	 * 
	 * @return Card mask.
	 * 
	 * @author Kalin VLadimirov
	 * 
	 * @email kalin06_@abv.bg
	 * 
	 * @date 27 Jul 2013
	 */
	public int getMask(int pos) {
		return (1 << (31 - pos));
	}

	/**
	 * 
	 * @param <K>
	 * 
	 * @param <V>
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 27 Jul 2013
	 */
	public static class Pair<K, V> {
		private final K element0;
		private final V element1;

		public static <K, V> Pair<K, V> createPair(K element0, V element1) {
			return new Pair<K, V>(element0, element1);
		}

		public Pair(K element0, V element1) {
			this.element0 = element0;
			this.element1 = element1;
		}

		public K getElement0() {
			return element0;
		}

		public V getElement1() {
			return element1;
		}
	}

	/**
	 * Returns pair (left,right) from rozdanie at por-th position in list r left
	 * is cards that has left player right is cards that has right player
	 * 
	 * 
	 */
	public Pair<List<Integer>, List<Integer>> getCardsAtRozdanie(int por) {
		// TODO To be done by Kalin.
		return (null);
	}

	/**
	 * Helper function to update isOne and isZero arrays.
	 * 
	 */
	public void p2nemaC(List<Integer> isOne, List<Integer> isZero, int bit,
			int c) {
		// TODO To be done by Miro.
	}
}
