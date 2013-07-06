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
		// TODO To be done by Venci.
		return (null);
	}

	public static String valueA(int c) {
		// TODO TO be done by Pesho.
		return (null);
	}

	/**
	 * Convert integer constant of the color to string representation.
	 * 
	 * @param c
	 *            Integer constant.
	 * 
	 * @return String representation of the title.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 06 Jul 2013
	 */
	public static String title(int c) {
		String s = colorZ(c);

		if (c % 8 < 4) {
			s += "a ";
		}

		if (c % 8 >= 4 && c % 8 <= 6) {
			s += "y ";
		}

		if (c % 8 == 7) {
			s += "e ";
		}

		return s + value(c);
	}

	/**
	 * Convert integer constant of the color to string representation.
	 * 
	 * @param c
	 *            Integer constant.
	 * 
	 * @return String representation of the title.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 06 Jul 2013
	 */
	public static String titleA(int c) {
		return (colorZ(c) + valueA(c));
	}

	/**
	 * Check for fatty card.
	 * 
	 * @param c
	 *            Integer card constant.
	 * 
	 * @return True if the card is fatty card, false otherwise.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 06 Jul 2013
	 */
	public static boolean isFatty(int c) {
		return (value(c) == "eso" || value(c) == "10");
	}

	/**
	 * Check for ...
	 * 
	 * @param c
	 *            Card index constant.
	 * 
	 * @param hra
	 *            ...
	 * 
	 * @return True if it is ..., false otherwise.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 06 Jul 2013
	 */
	public static boolean isTromf(int c, Hra hra) {
		return equalColor(c, hra.tromf);
	}

	/**
	 * Check for equal cards by color.
	 * 
	 * @param c
	 *            First card index.
	 * 
	 * @param d
	 *            Second card index.
	 * 
	 * @return True if cards match, false otherwise.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 06 Jul 2013
	 */
	public static boolean equalColor(int c, int d) {
		return (c / 8 == d / 8);
	}

	/**
	 * Check is first card stronger than second one.
	 * 
	 * @param c
	 *            First card index.
	 * 
	 * @param d
	 *            Second card index.
	 * 
	 * @param hra
	 *            ...
	 * 
	 * @return True if first card is stronger than second one, false otherwise.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 06 Jul 2013
	 */
	public static boolean stronger(int c, int d, Hra hra) {
		if (hra.farba == false) {
			return (c % 8 > d % 8 && equalColor(c, d));
		}

		double cc = c % 8;
		double dd = d % 8;

		if (cc == 3) {
			cc += 3.5;
		}

		if (dd == 3) {
			dd += 3.5;
		}

		if (equalColor(c, d) && cc > dd) {
			return (true);
		}

		if (equalColor(c, d) && cc <= dd) {
			return (false);
		}

		return (isTromf(c, hra));
	}

	/**
	 * Check if first card is grater than second one. Used for sorting in hand.
	 * 
	 * @param c
	 *            First card index.
	 * 
	 * @param d
	 *            Second card index.
	 * 
	 * @return True if first card is grater than second one, false otherwise.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 06 Jul 2013
	 */
	public static boolean greater(int c, int d) {
		if (c / 8 > d / 8) {
			return (true);
		}

		if (c / 8 < d / 8) {
			return (false);
		}

		/*
		 * Third parameter can be wrong.
		 */
		return (stronger(c, d, new Hra()));
	}

	/**
	 * used for sorting in hand
	 */
	public static boolean less(int c, int d) {
		// TODO TO be done by Pesho.
		return (false);
	}

	/**
	 * Plus one controlled by color.
	 * 
	 * @param c
	 *            Card index.
	 * 
	 * @param farba
	 *            Color.
	 * 
	 * @return Value with the addition.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 06 Jul 2013
	 */
	public static int plus1(int c, boolean farba) {
		if (farba == false) {
			return (c + 1);
		}

		if (c % 8 == 2) {
			return (c + 2);
		}

		if (c % 8 == 6) {
			return (c - 3);
		}

		if (c % 8 == 3) {
			return (c + 4);
		}

		return (c + 1);
	}

	/**
	 * Plus one controlled by color.
	 * 
	 * @param c
	 *            Card index.
	 * 
	 * @return Value with the addition.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 06 Jul 2013
	 */
	public static int plus1(int c) {
		return (plus1(c, true));
	}

	/**
	 * Deprecated - use Stav::trick() instead (not a static function, but
	 * instance of Hra was required here also).
	 * 
	 * @param c First card index.
	 * 
	 * @param d Second card index.
	 * 
	 * @param e Third card index.
	 * 
	 * @param hra ...
	 * 
	 * @return Index ...
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 06 Jul 2013
	 */
	public static int stich(int c, int d, int e, Hra hra) {
		/*
		 * Check conditions carefully.
		 */
		if ((stronger(d, c, hra) || stronger(e, c, hra)) == false) {
			return (0);
		}

		/*
		 * Check conditions carefully.
		 */
		if (stronger(d, c, hra)==true && stronger(e, d, hra)==false) {
			return (1);
		}

		return (2);
	}
}
