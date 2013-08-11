package eu.veldsoft.marias;

/**
 * ...
 * 
 * @author Vencislav Medarov
 * @email venci932@gmail.com
 * @date 24 Jul 2013
 */
class Evaluator {
	/**
	 * Evaluate of state.
	 * 
	 * @param ms
	 *            ...
	 * 
	 * @return ...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 24 Jul 2013
	 */
	private static double evaluateSedma(MyStav ms) {
		if (ms.stav.hra.flekNaSedmu == 0) {
			return (0);
		}

		int forhontTromfs = 0;
		for (Object c : ms.hand[ms.stav.forhont]) {
			if (Card.isTromf((Integer) c, ms.stav.hra) == true) {
				forhontTromfs++;
			}
		}

		int oppTromfs = 0;
		for (Object c : ms.hand[(ms.stav.forhont + 1) % 3]) {
			if (Card.isTromf((Integer) c, ms.stav.hra) == true) {
				oppTromfs++;
			}
		}

		int opp2Tromfs = 0;
		int oppCards = ms.hand[(ms.stav.forhont + 1) % 3].size();
		for (Object c : ms.hand[(ms.stav.forhont + 2) % 3]) {
			if (Card.isTromf((Integer) c, ms.stav.hra) == true) {
				opp2Tromfs++;
			}
		}

		// qDebug() << "sedma" << forhontTromfs << oppTromfs << opp2Tromfs <<
		// oppCards;

		int opp2Cards = ms.hand[(ms.stav.forhont + 2) % 3].size();
		if (opp2Tromfs > oppTromfs) {
			oppTromfs = opp2Tromfs;
			oppCards = opp2Cards;
		}

		/*
		 * Heuristic evaluating upon forhontTromfs, oppTromfs, oppCards
		 */
		if (ms.hand[ms.stav.forhont].contains(ms.stav.hra.tromf7()) == false) {
			return (0.0);
		}

		if (oppCards == 0) {
			return (1.0);
		}

		if (forhontTromfs >= 5) {
			return (1.0);
		}

		if (forhontTromfs - oppTromfs >= 3) {
			return (1.0);
		}

		if (oppTromfs == oppCards) {
			return (0.0);
		}

		if (forhontTromfs == 1 && oppTromfs == 0) {
			return (1.0 / Math.sqrt(oppCards));
		}

		if (forhontTromfs == 2 && oppTromfs == 0) {
			if (oppCards <= 2) {
				return (1.0);
			}
			return (1.0 / Math.sqrt(Math.sqrt(oppCards - 2)));
		}

		if (forhontTromfs == 3 && oppTromfs == 1) {
			if (oppCards <= 2) {
				return (1.0);
			}
			return (1.0 / Math.sqrt(Math.sqrt(Math.sqrt(oppCards - 2))));
		}

		if (forhontTromfs == 4 && oppTromfs == 2) {
			if (oppCards <= 2) {
				return (1.0);
			}
			return (1.0 / Math.sqrt(oppCards - 2));
		}

		if (forhontTromfs == 1 && oppTromfs == 1) {
			if (oppCards == 1) {
				return (0.0);
			}
			return (0.3);
		}

		if (forhontTromfs == 2 && oppTromfs == 1) {
			if (oppCards == 1) {
				return (0.0);
			}
			return (0.4);
		}

		if (forhontTromfs == 3 && oppTromfs == 2) {
			if (oppCards < 4) {
				return (0.2 * (oppCards - 2));
			}
			return (0.4);
		}

		if (forhontTromfs == 4 && oppTromfs == 3) {
			if (oppCards < 6) {
				return (0.0);
			}
			if (oppCards < 9) {
				return (0.1 * (oppCards - 5));
			}
			return (0.3);
		}

		if (forhontTromfs == oppTromfs) {
			if (oppCards - 2 * oppTromfs < 0) {
				return (0.0);
			}
			return (0.1 * Math.sqrt(oppCards - 2 * oppTromfs + 1));
		}

		if (forhontTromfs < oppTromfs) {
			return (0.0);
		}

		return (0.0);
	}

	/**
	 * Evaluation of state
	 * 
	 * @param ms
	 *            ...
	 * 
	 * @return ...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 24 Jul 2013
	 */
	private static double evaluateStovka(MyStav ms) {
		if (ms.stav.hra.flekNaSedmu == 0) {
			return (0);
		}

		return (0);
	}

	/**
	 * Evaluation of state during the game.
	 * 
	 * @param ms
	 *            ...
	 * 
	 * @return ...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 24 Jul 2013
	 */
	private static double evaluateHra(MyStav ms) {
		if (ms.hand[0].size() == 0) {
			return (ms.stav.hra.forhontPoints > ms.stav.hra.oppPoints ? 1 : 0);
		}

		/*
		 * Potencial ruk.
		 */
		int[] body = { 0, 0, 0 };

		/*
		 * Body forhonta.
		 */
		int forhontPoints = ms.stav.hra.forhontPoints;

		/*
		 * Body protihracov.
		 */
		int oppPoints = ms.stav.hra.oppPoints;

		int maxTromfov = 0;

		/*
		 * Za posledny stich.
		 */
		int daSaZiskatBodov = 10;

		for (int i = 0; i < 3; i++) {
			for (int farba = 0; farba < 4; farba++) {
				if (ms.hand[i].contains(5 + 8 * farba)
						&& ms.hand[i].contains(6 + 8 * farba) == true) {
					int bodyZaHlasku = 20;

					if (Card.isTromf(5 + 8 * farba, ms.stav.hra) == true) {
						bodyZaHlasku = 40;
					}

					if (i == 0) {
						forhontPoints += bodyZaHlasku;
					} else {
						oppPoints += bodyZaHlasku;
					}
				}
			}

			int pocetTromfov = 0;
			for (int j = 0; j < ms.hand[i].size(); j++) {
				int c = ms.hand[i].indexOf(j);
				if (Card.isTromf(c, ms.stav.hra) == true) {
					pocetTromfov++;
				}
				if (Card.isFatty(c) == true) {
					daSaZiskatBodov += 10;
				}
			}

			if (maxTromfov < pocetTromfov) {
				maxTromfov = pocetTromfov;
			}
		}

		double maxPomerTromfov = (double) maxTromfov
				/ (double) (ms.hand[0].size());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < ms.hand[i].size(); j++) {
				int c = ms.hand[i].indexOf(j);

				if (Card.isTromf(c, ms.stav.hra) == true) {
					body[i] += 8.0 + (double) Evaluator.silaKarty(c);
				} else {
					body[i] += (1.0 - maxPomerTromfov)
							* (double) Evaluator.silaKarty(c);
				}
			}
		}

		double potencialForhonta = (double) (body[0] + 1)
				/ (double) (body[0] + body[1] + body[2] + 1);

		double napinavost = (double) daSaZiskatBodov
				/ (double) (forhontPoints - oppPoints + 1);

		if (napinavost >= 0 && napinavost < 1) {
			/*
			 * Je to pre protihracov beznadejne.
			 */
			return (1);
		}

		if (napinavost > -1 && napinavost < 0) {
			/*
			 * Je to pre forhonta beznadejne.
			 */
			return (0);
		}

		// qDebug() << "(napinavost,  potencial forhonta) =" << napinavost <<
		// potencialForhonta;

		double exponent = (5.0 - 5.0 / napinavost) - 10 * potencialForhonta;
		double ret = 1.0 / (1.0 + Math.exp(exponent));

		// qDebug() << "ret" << ret;
		return (ret);

		/*
		 * Ten vychadza
		 */
		// ms->stav.vysid;
	}

	/**
	 * ...
	 * 
	 * @param ms
	 *            ...
	 * 
	 * @return ...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 24 Jul 2013
	 */
	private static int silaKarty(int c) {
		int sila = 0;
		int cPom = 0;

		while (c % 8 != cPom) {
			sila++;
			cPom = Card.plus1(cPom);
		}

		return (sila);
	}

	/**
	 * Heuristic evaluation of state.
	 * 
	 * @param ms
	 *            ...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 24 Jul 2013
	 */
	public static double evaluate(Stav s) {
		return (0);
	}

	/**
	 * Heuristic evaluation of state.
	 * 
	 * @param ms
	 * 
	 * 
	 * @return new state.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 24 Jul 2013
	 */
	public static double evaluate(MyStav ms) {
		double res = 0;

		if ((ms.stav.hra.flekNaSedmu) != 0) {
			res += ms.stav.hra.flekNaSedmu * (2.0 * evaluateSedma(ms) - 1.0);
		}

		if ((ms.stav.hra.flekNaStovku) != 0) {
			res += ms.stav.hra.flekNaStovku * evaluateStovka(ms);
		}

		if ((ms.stav.hra.flekNaHru) != 0) {
			res += ms.stav.hra.flekNaHru * (2.0 * evaluateHra(ms) - 1.0);
		}

		// qDebug() << res;

		return (res);
	}

	/**
	 * 
	 * @param ms1
	 * @param ms2
	 * 
	 * @return
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 24 Jul 2013
	 */
	public static boolean less(MyStav ms1, MyStav ms2) {
		return (evaluate(ms1) < evaluate(ms2));
	}

	/**
	 * 
	 * @param ms1
	 * @param ms2
	 * 
	 * @return
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 24 Jul 2013
	 */
	public static boolean greater(MyStav ms1, MyStav ms2) {
		return (evaluate(ms1) > evaluate(ms2));
	}
}
