package eu.veldsoft.marias;

import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Stav keeps informations on game state. Also keeps information on game
 * goals in instance of Hra.
 * 
 * @author Todor Balabanov
 * 
 * @email tdb@tbsoft.eu
 * 
 * @date 11 Jul 2013
 */
class Stav {
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(Stav.class.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * id of player, who's turn it is now. Id is to list of players Game
	 * instance stores.
	 */
	public int id;

	/**
	 * id of player who played first this round
	 */
	public int vysid;

	/**
	 * id of forhont
	 */
	public int forhont;

	/**
	 * round id: (round=kolo) -6: intro -5: from new game to start 1. round of
	 * card dealing -4:during 1.round of card dealing, waiting to select the
	 * tromf -3:tromf is already selected, during 2.round of card dealing to
	 * start of fixing hand -2:hand is fixed, waiting to select the talon
	 * -1:during animation of second card to talon, during bidding, until end of
	 * bidding 0..9:game rounds (new round is at selecting third card)
	 * 10:results
	 * 
	 * hra je 0 az 9, 10 je koniec, -1 je talon, -2 je volba tromfa
	 */
	public int kolo;

	/**
	 * 
	 */
	public Hra hra;

	/**
	 * kopa has 0-2 cards
	 */
	public List<Integer> kopa = new ArrayList<Integer>();

	/**
	 * cHist contains card history. Kopa is suffix of cHist.
	 */
	public List<Integer> cHist = new ArrayList<Integer>();

	/**
	 * pHist[i] is player that won i-th stich
	 */
	public List<Integer> pHist = new ArrayList<Integer>();

	/**
	 * results
	 */
	public class ResRow {
		private final String value1;
		private final String value2;
		private final boolean value3;

		public ResRow(String value1, String value2, boolean value3) {
			this.value1 = value1;
			this.value2 = value2;
			this.value3 = value3;
		}

		// TODO Implement getters.
	}

	/**
	 * ...
	 */
	public List<ResRow> res;

	/**
	 * Round id -1: during animation of second card to talon, during bidding,
	 * until end of bidding 0.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 11 Jul 2013
	 */
	public Stav() {
		forhont = -1;
	}

	/**
	 * Resetting some things, so new game can begin
	 * 
	 */
	public void newGame() {
	}

	/**
	 * Next player's turn. (dalsi=next)
	 * 
	 * Returns id of next players's turn.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 11 Jul 2013
	 */
	public int dalsi() {
		id = (id + 1) % 3;
		return (id);
	}

	/**
	 * Checks card history for strongest card in round kolo (veduca=leading,
	 * kolo=round)
	 * 
	 */
	public int veduca(int kolo) {
		return (0);
	}

	/**
	 * Checks if this state is valid.
	 * 
	 * @return Message for valid state.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 11 Jul 2013
	 */
	public String validate() {
		if (kopa.size() < 0 || kopa.size() > 3) {
			return ("Na kope je zly pocet kariet: " + kopa.size());
		}

		for (int c = 0; c < 32; c++) {
			/*
			 * Check if the card can be found more than once in the history.
			 */
			if (cHist.indexOf(c) != cHist.lastIndexOf(c)) {
				// TODO This kind of checking can be wrong.
				return ("Duplicitna karta v historii: " + c);
			}
		}

		if (kopa.size() > cHist.size()) {
			return ("Na kope je viac kariet ako v historii");
		}

		for (int i = 1; i <= kopa.size(); i++) {
			if (cHist.get(cHist.size() - i) != kopa.get(kopa.size() - i)) {
				return ("Karty na kope nesedia s kartami v historii");
			}
		}

		return ("");
	}

	/**
	 * Returns id of player that takes the trick. Also modifies the state.
	 * 
	 * @return Player id.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 11 Jul 2013
	 */
	public int trick() {
		if (kopa.size() != 3) {
			LOGGER.info("Stav::trick(): Kopa neni plna");
			return (-1);
		}

		if (pHist.isEmpty() && id != forhont) {
			LOGGER.info("Stav::trick(): Nesedi id (prvy stich)");
			return (-1);
		}

		// TODO This check for the last element can be wrong in Java.
		if (pHist.isEmpty() == false
				&& pHist.lastIndexOf(id) != (pHist.size() - 1)) {
			LOGGER.info("Stav::trick(): Nesedi id s pHist");
			return (-1);
		}

		if (id != vysid) {
			LOGGER.info("Stav::trick(): Nesedi id s vysid");
			return (-1);
		}

		int c = cHist.get(cHist.size() - 3);
		int d = cHist.get(cHist.size() - 2);
		int e = cHist.get(cHist.size() - 1);

		/*
		 * Player that won the trick.
		 */
		int p = -1;

		if (!Card.stronger(d, c, hra) && !Card.stronger(e, c, hra)) {
			p = vysid;
		} else if (Card.stronger(d, c, hra) && !Card.stronger(e, d, hra)) {
			p = (vysid + 1) % 3;
		} else {
			p = (vysid + 2) % 3;
		}

		int stichPoints = 0;

		if (Card.isFatty(c)) {
			stichPoints += 10;
		}

		if (Card.isFatty(d)) {
			stichPoints += 10;
		}

		if (Card.isFatty(e)) {
			stichPoints += 10;
		}

		if (kolo == 9) {
			stichPoints += 10;
		}

		if (p == forhont) {
			hra.forhontPoints += stichPoints;
		} else {
			hra.oppPoints += stichPoints;
		}

		return (p);
	}

	/**
	 * The current players has a marriage call (hlaska).
	 * 
	 * @param points
	 *            ...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 11 Jul 2013
	 */
	public void hlaska(int points) {
		if (id == forhont) {
			hra.forhontHlasky++;
			hra.forhontPoints += points;
		} else {
			hra.oppHlasky++;
			hra.oppPoints += points;
		}
	}

	/**
	 * Returns money for hra (including fleking and quiet hundred).
	 * 
	 */
	public int hraResults(boolean... args) {
		boolean storeData = false;

		if (args.length == 1) {
			storeData = args[0];
		} else if (args.length > 1) {
			// TODO Report too many arguments exception.
		}

		return (0);
	}

	public int sedmaResults(boolean... args) {
		boolean storeData = false;

		if (args.length == 1) {
			storeData = args[0];
		} else if (args.length > 1) {
			// TODO Report too many arguments exception.
		}

		return (0);
	}

	public int stovkaResults(boolean... args) {
		boolean storeData = false;

		if (args.length == 1) {
			storeData = args[0];
		} else if (args.length > 1) {
			// TODO Report too many arguments exception.
		}

		return (0);
	}

	public int results(boolean... args) {
		boolean storeData = false;

		if (args.length == 1) {
			storeData = args[0];
		} else if (args.length > 1) {
			// TODO Report too many arguments exception.
		}

		return (0);
	}
}
