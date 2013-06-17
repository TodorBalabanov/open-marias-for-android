package eu.veldsoft.marias;

class Card {
	public Card() {
	}

	public static String color(int c) {
		return (null);
	}

	/**
	 * slovny zaklad
	 */
	public static String colorZ(int c) {
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
