package eu.veldsoft.marias;

/**
 * Class for min-max logic check.
 * 
 * @author Vencislav Medarov
 * @email venci932@gmail.com
 * @date 17 Jul 2013
 */
class TrustingMinimaxPlayer extends MinimaxPlayer {

	/**
	 * Maximizes only when it is your turn forhont. If not, it is assumed that
	 * the opposition works.
	 * 
	 * @return Stav id.
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 17 Jul 2013
	 */
	@Override
	public boolean isMaximizing(MyStav ms) {
		return (ms.stav.id == ms.stav.forhont);
	}
}