package eu.veldsoft.marias;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Selection of trump AI.
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 09 Aug 2013
 */
class ChooseTromf {
	/**
	 * 
	 */
	private static final int TIME_LIMIT_CASE = 5000;

	/**
	 * 
	 */
	private static final boolean PRETTY = false;

	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(ChooseTromf.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * i-th bit of the int is whether there is card i in first 7 cards
	 */
	public List<Integer> rozdania;

	/**
	 * Game simulator reference.
	 */
	public GameSimulator gs;

	public int getCardMask(int c) {
		// TODO To be done by Miro.
		return (0);
	}

	/**
	 * Heuristicky zisti, ci je zrejme, co sa ma zvolit ako tromf uz len z poctu
	 * farieb.
	 * 
	 */
	public boolean jeTromfJasny(int ff[]) {
		// TODO To be done by Miro.
		return (false);
	}

	/**
	 * Heuristicky zisti, ktore karty su vhodne na volbu tromfa. Potom sa bude
	 * prehladavat strom pre kazdu takuto volbu.
	 * 
	 * @return int obsahuje jednotky pri tych kartach, ktore su vhodne na volbu
	 *         tromfa.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 06 Aug 2013
	 */
	public int kandidatiNaTromfa(int rozdanie) {
		int bodyZaFarbu[] = new int[] { 0, 0, 0, 0 };
		int maxFarba = 0;

		for (int i = 0; i < 4; i++) {
			int pocet = 0;

			for (int j = 0; j < 8; j++) {
				if ((rozdanie & getCardMask(i * 8 + j)) != 0)
					pocet++;
			}

			bodyZaFarbu[i] += pocet * pocet * pocet;

			/*
			 * mam eso
			 */
			if ((rozdanie & getCardMask(i * 8 + 7)) != 0) {
				bodyZaFarbu[i] += 10;
			}

			/*
			 * mam desiatku
			 */
			if ((rozdanie & getCardMask(i * 8 + 3)) != 0) {
				bodyZaFarbu[i] += 8;
			}

			/*
			 * mam eso aj desiatku
			 */
			if ((rozdanie & getCardMask(i * 8 + 3)) != 0
					&& (rozdanie & getCardMask(i * 8 + 7)) != 0) {
				bodyZaFarbu[i] += 2;
			}

			/*
			 * mam hlasku
			 */
			if ((rozdanie & getCardMask(i * 8 + 5)) != 0
					&& (rozdanie & getCardMask(i * 8 + 6)) != 0) {
				bodyZaFarbu[i] += 53;
			}

			/*
			 * trham hlasku
			 */
			if (((rozdanie & getCardMask(i * 8 + 5)) ^ (rozdanie & getCardMask(i * 8 + 6))) != 0) {
				bodyZaFarbu[i] += 13;
			}

			if (bodyZaFarbu[i] >= bodyZaFarbu[maxFarba]) {
				maxFarba = i;
			}
		}

		int kandidati = 0;

		for (int i = 0; i < 4; i++) {
			/*
			 * je to uplne prehrata hra, ani netrham ziadnu hlasku
			 */
			if (bodyZaFarbu[maxFarba] < 13) {
				if (i != maxFarba) {
					continue;
				}
			}

			if (bodyZaFarbu[i] * 2 >= bodyZaFarbu[maxFarba]) {
				/*
				 * farba i je hodna byt zvolena za tromfa, kandidatska karta
				 * bude minimum z tej farby ale este skontrolujem, ci to nie je
				 * podmnozina
				 */
				boolean[] jePodmnozina = new boolean[] { true, true, true, true };
				boolean[] jeTotozna = new boolean[] { true, true, true, true };

				/*
				 * zaujimaju ma len porovnania s inymi farbami
				 */
				jePodmnozina[i] = false;

				/*
				 * zaujimaju ma len porovnania s inymi farbami
				 */
				jeTotozna[i] = false;

				for (int j = 0; j < 8; j++) {
					for (int k = 0; k < 4; k++) {
						if (k == i)
							continue;
						/*
						 * ak nieco je v i a nie je vo farbe k, tak i nie je
						 * podmnozina k
						 */
						if ((rozdanie & getCardMask(i * 8 + j)) != 0
								&& ((rozdanie & getCardMask(k * 8 + j)) == 0))
							jePodmnozina[k] = false;
					}
				}

				for (int k = 0; k < 4; k++) {
					int kartyVI = (rozdanie >> (8 * (3 - i))) % 256;
					int kartyVK = (rozdanie >> (8 * (3 - k))) % 256;
					jeTotozna[k] = (kartyVI == kartyVK);
				}

				boolean jePodm = false;

				for (int k = 0; k < 4; k++) {
					if (jePodmnozina[k] == true && jeTotozna[k] == false) {
						jePodm = true;
					}
					/*
					 * ak maju dve farby totozne karty, potom staci testovat
					 * jednu z nich
					 */
					if (jeTotozna[k] && i < k) {
						jePodm = true;
					}
				}

				if (jePodm == true) {
					/*
					 * i nebude kandidat, kedze je podmnozina k
					 */
					continue;
				}

				for (int j = 0; j < 8; j++) {
					if ((rozdanie & getCardMask(i * 8 + j)) != 0) {
						kandidati |= getCardMask(i * 8 + j);
						break;
					}
				}
			}
		}

		return (kandidati);
	}

	/**
	 * ...
	 * 
	 * @param rozdanie
	 *            ...
	 * @param farby
	 *            ...
	 * @param ff
	 *            ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 09 Aug 2013
	 */
	public void generujRecursive(int rozdanie, int farby[], int ff[]) {
		if (farby[0] + farby[1] + farby[2] + farby[3] == 7) {
			/*
			 * mame vygenerovane rozdanie ak su dve farby s rovnakym poctom
			 * kariet, dalsia musi byt lexikograficky dalej
			 */
			int kartyVoFarbach[] = new int[4];
			for (int i = 0; i < 4; i++) {
				kartyVoFarbach[i] = (rozdanie >> ((3 - i) * 8)) % 256;
			}

			if (kartyVoFarbach[0] > kartyVoFarbach[1] && farby[0] == farby[1]) {
				return;
			}

			if (kartyVoFarbach[1] > kartyVoFarbach[2] && farby[1] == farby[2]) {
				return;
			}

			// assert kartyVoFarbach[0] <= kartyVoFarbach[1] <=
			// kartyVoFarbach[2]
			rozdania.add(rozdanie);
			return;
		}

		/*
		 * nieco chyba z nejakej farby
		 */
		for (int ii = 0; ii < 4; ii++) {
			/*
			 * poradie 3,0,1,2 = cerven, gula, zalud, zelen
			 */
			int i = (ii + 3) % 4;
			if (farby[i] < ff[i]) {
				/*
				 * idem generovat nejaku moznu kartu z i-tej farby
				 */
				farby[i]++;
				int from = i * 8;
				for (int j = i * 8; j < i * 8 + 8; j++)
					if ((rozdanie & getCardMask(j)) != 0) {
						/*
						 * prva medzera za poslednym pouzitym
						 */
						from = j + 1;
					}

				for (int j = from; j < i * 8 + 8; j++) {
					if ((rozdanie & getCardMask(j)) != 0) {
						continue;
					}

					generujRecursive(rozdanie | getCardMask(j), farby, ff);
				}
				farby[i]--;
				return;
			}
		}
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 09 Aug 2013
	 */
	public void generujRozdania() {
		/*
		 * pocet cervenov chceme generovat rozdania len take, kde je viac gul
		 * ako zaludov a viac zaludov ako zelenov moze byt aj rovnako, ale potom
		 * lexikograficky zapis gul nesmie byt dalej v abecede
		 */
		for (int r = 7; r >= 0; r--) {
			/*
			 * minimalny pocet gul
			 */
			int minGul = (9 - r) / 3;

			/*
			 * maximalny pocet gul
			 */
			int maxGul = 7 - r;

			for (int g = minGul; g <= maxGul; g++) {
				int minZal = (8 - r - g) / 2;
				int maxZal = 7 - r - g;
				if (maxZal > g)
					maxZal = g;
				for (int zal = minZal; zal <= maxZal; zal++) {
					/*
					 * pocet zelenov
					 */
					int zel = 7 - r - g - zal;

					LOGGER.info("" + r + g + zal + zel);

					int farby[] = new int[] { 0, 0, 0, 0 };
					int ff[] = new int[] { g, zal, zel, r };
					// if(!jeTromfJasny(ff))
					generujRecursive(0, farby, ff);
					LOGGER.info("" + rozdania.size());
				}
			}
		}
	}

	public String printRozdanie(int rozdanie, boolean detail) {
		// TODO To be done by ...
		return (null);
	}

	/**
	 * Print all cards after deal.
	 * 
	 * @param rozdanie
	 *            ...
	 * 
	 * @return Srting representation of delt cards.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 09 Aug 2013
	 */
	public String printRozdanie(int rozdanie) {
		return (printRozdanie(rozdanie, false));
	}

	/**
	 * 
	 * @param rozdanie
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 09 Aug 2013
	 */
	public void ratajOdRozdania(int rozdanie) {
		/*
		 * ak neni zadane rozdanie, tak sa rata od zaciatku
		 */
		boolean rataj = (rozdanie == 0);

		LOGGER.info("Pocet vsetkych rozdani: " + rozdania.size());

		DataOutputStream out;
		try {
			out = new DataOutputStream(new FileOutputStream("chooseTromf.txt"));
		} catch (FileNotFoundException e) {
			LOGGER.info("Nejde otvorit subor");
			return;
		}

		for (int i = 0; i < rozdania.size(); i++) {
			if (rataj == true) {
				if (out == null) {
					LOGGER.info("Nejde otvorit subor");
					return;
				}

				int kandidati = kandidatiNaTromfa(rozdania.get(i));
				int poc = 0;
				for (int k = 0; k < 32; k++) {
					if ((kandidati & (1 << k)) != 0) {
						poc++;
					}
				}

				try {
					out.writeInt(rozdania.get(i));
				} catch (IOException e) {
				}
				if (PRETTY == true) {
					try {
						out.writeUTF("\n"
								+ printRozdanie(rozdania.get(i), true) + "\n");
					} catch (IOException e) {
					}
				}

				/*
				 * local loop pointer to tromf
				 */
				int tromf = 31;
				double maxResult = -9999999;
				int bestTromf = -1;
				while (kandidati > 0) {
					if ((kandidati & 1) != 0) {
						double result;
						if (poc > 1) {
							result = gs.expectedResult(rozdania.get(i), tromf,
									TIME_LIMIT_CASE);
							if (result > maxResult) {
								maxResult = result;
								bestTromf = tromf;
							}
						} else {
							/*
							 * ked je len jeden kandidat, tak je najlespi
							 * bezkonkurencne
							 */
							bestTromf = tromf;
						}
						if (PRETTY && poc > 1) {
							try {
								out.writeUTF(Card.title(tromf) + ": " + result
										+ "\n");
							} catch (IOException e) {
							}
						}
					}
					tromf--;
					kandidati /= 2;
				}
				if (PRETTY == false) {
					try {
						out.writeUTF(" " + bestTromf + "\n");
					} catch (IOException e) {
					}
				}
			}
			try {
				out.close();
			} catch (IOException e) {
			}

			if (rozdania.get(i) == rozdanie) {
				LOGGER.info("Skipol som prvych " + i);
				rataj = true;
			}
		}
	}
}
