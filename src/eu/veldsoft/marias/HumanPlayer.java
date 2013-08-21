package eu.veldsoft.marias;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HumanPlayer is class which gives information during the game.
 * 
 * @author Vencislav Medarov
 * @email venci932@gmail.com
 * @date 13 Jul 2013
 */
class HumanPlayer extends Player {
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(HumanPlayer.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * Constructor without parameters.
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 13 July 2013
	 */
	public HumanPlayer() {
		type = "human";
		name = "Hrac";
	}

	/**
	 * Print information for preparing to play.
	 * 
	 * @return ...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 13 Jul 2013
	 */
	public int play() {
		LOGGER.info("ide human");
		LOGGER.info("waiting for click...");

		return (-1);
	}

	/**
	 * Print information for Bidding.
	 * 
	 * @return ...
	 * 
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 13 Jul 2013
	 */
	public int bid() {
		LOGGER.info("Bidding: ide human");
		return (-1);
	}
}
