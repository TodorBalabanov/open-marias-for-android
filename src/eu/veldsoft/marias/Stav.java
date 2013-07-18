package eu.veldsoft.marias;

import java.util.List;

/**
 * Class Stav keeps informations on game state. Also keeps information on game
 * goals in instance of Hra.
 */
class Stav {

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

	public Hra hra;

	/**
	 * kopa has 0-2 cards
	 */
	public List<Integer> kopa;

	/**
	 * cHist contains card history. Kopa is suffix of cHist.
	 */
	public List<Integer> cHist;

	/**
	 * pHist[i] is player that won i-th stich
	 */
	public List<Integer> pHist;

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

	public List<ResRow> res;

	public Stav() {
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
	 */
	public int dalsi() {
		return (0);
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
	 * Checks if this state is valid
	 * 
	 */
	public String validate() {
		return (null);
	}

	/**
	 * Returns id of player that takes the trick. Also modifies the state.
	 * 
	 */
	public int trick() {
		return (0);
	}

	/**
	 * The current players has a marriage call (hlaska)
	 * 
	 */
	public void hlaska(int points) {
	}

	/**
	 * Returns money for hra (including fleking and quiet hundred)
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
