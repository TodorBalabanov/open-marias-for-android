package eu.veldsoft.marias;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for playing card representation.
 * 
 * @author Todor Balabanov
 * 
 * @email tdb@tbsoft.eu
 * 
 * @date 04 Jul 2013
 */
class Card {
	private final static Logger LOGGER = Logger.getLogger(Card.class.getName());

	public Card() {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * Convert integer constant of the color to string representation.
	 * 
	 * @param c
	 *            Integer constant.
	 * 
	 * @return String representation of the suit.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 04 Jul 2013
	 */
	public static String color(int c) {
		if (c / 8 == 0) {
			return "gula";
		}

		if (c / 8 == 1) {
			return "zalud";
		}

		if (c / 8 == 2) {
			return "zelen";
		}

		if (c / 8 == 3) {
			return "cerven";
		}

		/*
		 * This line is used for debug only.
		 */
		LOGGER.info("zly kod karty: " + c);

		return "";
	}

	/**
	 * slovny zaklad
	 */
	public static String colorZ(int c) {
		// TODO To be done by Miro.
		return (null);
	}

	public static String value(int c) {
		return (null);
	}

	public static String valueA(int c) {
		return (null);
	}

	public static String title(int c) {
		return (null);
	}

	public static String titleA(int c) {
		return (null);
	}

	public static boolean isFatty(int c) {
		return (false);
	}

	public static boolean isTromf(int c, Hra hra) {
		return (false);
	}

	public static boolean equalColor(int c, int d) {
		return (false);
	}

	public static boolean stronger(int c, int d, Hra hra) {
		return (false);
	}

	/**
	 * used for sorting in hand
	 */
	public static boolean greater(int c, int d) {
		return (false);
	}

	/**
	 * used for sorting in hand
	 */
	public static boolean less(int c, int d) {
		return (false);
	}

	public static int plus1(int c, boolean... args) {
		boolean farba = true;

		if (args.length == 1) {
			farba = args[0];
		} else if (args.length > 1) {
			// TODO Report too many arguments exception.
		}

		return (0);
	}

	/**
	 * Deprecated - use Stav::trick() instead (not a static function, but
	 * instance of Hra was required here also)
	 * 
	 */
	public static int stich(int c, int d, int e, Hra hra) {
		return (0);
	}
}
