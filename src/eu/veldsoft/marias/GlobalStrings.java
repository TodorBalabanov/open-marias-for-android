package eu.veldsoft.marias;

/**
 * Class for global strings usage.
 * 
 * @author Kalin Vladimirov
 * @email kalin06_@abv.bg
 * @date 19 Aug 2013
 */
class GlobalStrings {

	/**
	 * 
	 * @param f
	 * @return
	 * 
	 * @author Kalin Vladimirov
	 * @email kalin06_@abv.bg
	 * @date 19 Aug 2013
	 */
	public static String flek(int f) {
		switch (f) {
		case 1:
			return ("");
		case 2:
			return ("Flek");
		case 4:
			return ("Re");
		case 8:
			return ("Tutti");
		case 16:
			return ("Boty");
		case 32:
			return ("Kalhoty");
		case 64:
			return ("Kaiser");
		case 128:
			return ("Rekaiser");
		default:
			return ("?");
		}
	}
}
