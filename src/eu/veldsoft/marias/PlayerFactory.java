package eu.veldsoft.marias;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Player factory class.
 * 
 * @author Kalin Vladimirov
 * @email kalin06_@abv.bg
 * @date 19 Aug 2013
 */
class PlayerFactory {
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(PlayerFactory.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * 
	 * @param type
	 * 
	 * @return
	 * 
	 * @author Kalin Vladimirov
	 * @email kalin06_@abv.bg
	 * @date 19 Aug 2013
	 */
	public static Player create(String type) {
		if (type.equals("HumanPlayer")) {
			return (new HumanPlayer());
		} else if (type.equals("RandomPlayer")) {
			return (new RandomPlayer());
		} else if (type.equals("MinPlayer")) {
			return (new MinPlayer());
		} else if (type.equals("MaxPlayer")) {
			return (new MaxPlayer());
		} else if (type.equals("SmartPlayer")) {
			return (new SmartPlayer());
		} else if (type.equals("MinimaxPlayer")) {
			return (new MinimaxPlayer());
		} else if (type.equals("TrustingMinimaxPlayer")) {
			return (new TrustingMinimaxPlayer());
		}

		LOGGER.info("Invalid player type:" + type);

		return (new RandomPlayer());
	}
}
