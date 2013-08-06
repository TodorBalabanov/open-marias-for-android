package eu.veldsoft.marias;

import java.util.List;

class ChooseTromf {
	// i-th bit of the int is whether there is card i in first 7 cards
	public List<Integer> rozdania;

	public GameSimulator gs;

	public ChooseTromf() {
	}

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
	 * 
	 * @email tdb@tbsoft.eu
	 * 
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

	public void generujRecursive(int rozdanie, int farby[], int ff[]) {
	}

	public void generujRozdania() {
	}

	public String printRozdanie(int rozdanie, boolean detail) {
		return (null);
	}

	public String printRozdanie(int rozdanie) {
		return (printRozdanie(rozdanie, false));
	}

	public void ratajOdRozdania(int rozdanie) {
	}
}
