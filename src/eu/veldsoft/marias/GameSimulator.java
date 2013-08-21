package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.OutputStream;

/**
 * ....
 * 
 * @author Vencislav Medarov
 * @email venci932@gmail.com
 * @date 07 Aug 2013
 */
class GameSimulator {
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(GameSimulator.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * Profiler reference.
	 */
	public Profiler profiler;

	/**
	 * Stav reference.
	 */
	public Stav stav;

	/**
	 * Player array.
	 */
	public Player p[] = new Player[3];

	/**
	 * Bidding history container.
	 */
	public List<Integer> biddingHistory;

	/**
	 * Output stream reference.
	 */
	public OutputStream out;

	private double log2(int value) {
		return (Math.log10(value) / Math.log10(2));
	}

	/**
	 * Constructor.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 07 Aug 2013
	 */
	public GameSimulator() {
		profiler = new Profiler();

		MainActivity.PRNG.setSeed(-1);

		stav = new Stav();

		for (int i = 0; i < 3; i++) {
			p[i] = new SmartPlayer();
			p[i].id = i;
			p[i].quickGame = true;
			p[i].stav = stav;
		}
	}

	/**
	 * Get card mask at specific position.
	 * 
	 * @param c
	 *            Card position which is different than card index.
	 * 
	 * @return Card mask.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 08 Aug 2013
	 */
	public int getCardMask(int c) {
		return (1 << (31 - c));
	}

	/**
	 * Vrati ocakavany result pri danom rozdani pri danom zvolenom tromfe.
	 * Nahodne rozda zvysne karty a odohra hru.
	 * 
	 * @param rozdanie
	 *            ...
	 * @param tromf
	 *            ...
	 * @param timeLimit
	 *            ...
	 * 
	 * @return Calculated average value.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 08 Aug 2013
	 */
	public double expectedResult(int rozdanie, int tromf, int timeLimit) {
		profiler.start(String.valueOf(rozdanie) + Card.title(tromf));

		int sum = 0;
		int count = 0;

		while (profiler.getTime(String.valueOf(rozdanie) + Card.title(tromf)) < timeLimit) {
			count++;
			stav.newGame();
			stav.forhont = 0;
			stav.hra.farba = true;
			stav.hra.tromf = tromf;
			rozdaj(rozdanie);
			talon();
			stav.id = 0;
			bid(p[stav.id].bid());
			stav.vysid = 0;
			stav.id = 0;
			stav.kolo = 0;
			stav.kopa.clear();

			while (stav.kolo < 10) {
				if (stav.kopa.size() < 3) {
					int k = p[stav.id].play();

					if (Card.value(k) == "hornik"
							&& p[stav.id].hand.contains(k + 1) == true) {
						int hlaska = 20;
						if (Card.color(k) == Card.color(stav.hra.tromf) == true) {
							hlaska = 40;
							p[stav.id].body += hlaska;
							p[stav.id].hlasky++;
							stav.hlaska(hlaska);
						}
					}

					stav.cHist.add(k);
					stav.kopa.add(k);
					p[stav.id].removeCard(k);
					stav.dalsi();
				} else {
					int winner = stav.trick();

					for (Integer c : stav.kopa) {
						if (Card.value(c) == "10" || Card.value(c) == "eso") {
							p[winner].body += 10;
						}
					}

					if (stav.kolo == 9) {
						p[winner].body += 10;
					}

					stav.id = winner;
					stav.kolo++;
					stav.pHist.add(winner);
					stav.vysid = winner;
					stav.kopa.clear();
				}
			}

			int res = stav.results(true);

			sum += res;
			stav.hra.tromf = -1;
		}

		profiler.reset();

		LOGGER.info("stihlo sa " + count + " hier");

		if (count == 0) {
			count = 1;
		}

		return ((double) sum / (double) count);
	}

	/**
	 * Rozda karty nahodne hracom tak, ze pouzitych 7 sa tam nenachadza.
	 * 
	 * @param rozdanie
	 *            ...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 09 Aug 2013
	 */
	public void rozdaj(int rozdanie) {
		for (int i = 0; i < 3; i++) {
			p[i].hand.clear();
		}

		List<Integer> deck = new ArrayList<Integer>();

		for (int i = 0; i < 32; i++) {
			if ((rozdanie & getCardMask(i)) != 0) {
				(p[0].hand).add(i);
				continue;
			}

			deck.add(i);
		}

		if (deck.size() != 25) {
			LOGGER.info("Deck size bug: " + deck.size());
		}

		int swapCount = 1000 + MainActivity.PRNG.nextInt(1000);

		for (int i = 0; i < swapCount; i++) {
			deck.set(MainActivity.PRNG.nextInt(25),
					MainActivity.PRNG.nextInt(25));
		}

		for (int i = 0, j = 0; i < 25; i++) {
			if (i < 5) {
				j = 0;
			} else if (i < 15) {
				j = 1;
			} else if (i < 25) {
				j = 2;
			}
			
			p[j].hand.add(deck.get(i));
		}
	}

	/**
	 * Talon selection procedure.
	 * 
	 * @return Card id from talon.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 09 Aug 2013
	 */
	public void talon() {
		int talon = p[0].talon();
		int tal1 = talon / 32;
		int tal2 = talon % 32;
		p[0].talonCards[0] = tal1;
		p[0].talonCards[1] = tal2;
		p[0].removeCard(tal1);
		p[0].removeCard(tal2);
	}

	/**
	 * Manazuje bidding.
	 * 
	 * @param res
	 *            response from player
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 09 Aug 2013
	 */
	public void bid(int res) {
		boolean ideForhont = (stav.id == stav.forhont);

		/*
		 * if the state of bid has changed
		 */
		boolean bidd = false;

		/*
		 * hra
		 */
		if ((res & 4) != 0 && stav.hra.flekNaHru < 128) {
			if (stav.hra.flekNaHru == 0) {
				if (ideForhont == true) {
					stav.hra.flekNaHru = 1;
					bidd = true;
				} else {
					LOGGER.info("Opponent " + stav.id
							+ " hlasi hru skor ako forhont!");
				}
			} else {
				if (ideForhont ^ ((int) log2(stav.hra.flekNaHru) % 2 == 0)) {
					stav.hra.flekNaHru *= 2;
					bidd = true;
				}
			}
		}

		/*
		 * sedma
		 */
		if ((res & 2) != 0 && stav.hra.flekNaSedmu < 128) {
			if (stav.hra.sedma == true) {
				if (ideForhont ^ ((int) log2(stav.hra.flekNaSedmu) % 2 == 0)) {
					stav.hra.flekNaSedmu *= 2;
					bidd = true;
				}
			} else if (stav.hra.sedmaProti == true) {
				if (ideForhont ^ ((int) log2(stav.hra.flekNaSedmu) % 2 == 1)) {
					stav.hra.flekNaSedmu *= 2;
					bidd = true;
				}
			} else {
				if (ideForhont == true) {
					stav.hra.sedma = true;
				} else {
					stav.hra.sedmaProti = true;
				}
				stav.hra.flekNaSedmu = 1;
				bidd = true;
			}
		}

		/*
		 * stovka
		 */
		if ((res & 1) != 0 && stav.hra.flekNaStovku < 128) {
			if (stav.hra.stovka == true) {
				if (ideForhont ^ ((int) log2(stav.hra.flekNaStovku) % 2 == 0)) {
					stav.hra.flekNaStovku *= 2;
					bidd = true;
				}
			} else if (stav.hra.stovkaProti == true) {
				if (ideForhont ^ ((int) log2(stav.hra.flekNaStovku) % 2 == 1)) {
					stav.hra.flekNaStovku *= 2;
					bidd = true;
				}
			} else {
				if (ideForhont == true) {
					stav.hra.stovka = true;
				} else {
					stav.hra.stovkaProti = true;
				}

				stav.hra.flekNaStovku = 1;
				bidd = true;
			}
		}

		boolean endBidding = false;

		if (bidd == false) {
			if (ideForhont) {
				endBidding = true;
			} else {
				if (biddingHistory.get(biddingHistory.size() - 1) == 0) {
					endBidding = true;
				}
			}
			biddingHistory.add(0);
		} else {
			biddingHistory.add(res);
		}

		if (endBidding == false) {
			bid(p[stav.id].bid());
		}

		biddingHistory.clear();
	}
}
