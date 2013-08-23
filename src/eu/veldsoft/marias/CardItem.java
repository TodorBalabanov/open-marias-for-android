package eu.veldsoft.marias;

import java.util.logging.Level;
import java.util.logging.Logger;

import android.view.MotionEvent;

/**
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 14 Aug 2013
 */
public class CardItem {

	public int zValue() {
		return 0;
	}

	public void setX(double d) {
	}

	public void setY(double d) {
	}

	public double x() {
		return 0;
	}

	public double y() {
		return 0;
	}

	public void setPos(Double double1, Double double2) {
	}

	public void setPixmap(eu.veldsoft.marias.DeskView.Pixmap rub) {
	}

	public void setPos(eu.veldsoft.marias.DeskView.Point poziciaBase) {
	}

	public void setOpacity(int i) {
	}

	public void setData(int i, String string) {
	}

	public void setData(int i, int i2) {
	}

	public void setZValue(int i) {
	}
	
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(Card.class.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	public int cid;

	/**
	 * 
	 * @param event
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 14 Aug 2013
	 */
	public void mousePressEvent(MotionEvent event) {
		int kolo = DeskView.getGame().stav.kolo;

		LOGGER.info("click in round " + kolo);

		if (kolo == -6) {
			DeskView.getGame().animationFinished(kolo);
		} else if (kolo == -5) {
			//TODO DeskView.getGame().marias.on_actionNew_game_triggered();
		}else if (kolo == -4) {
			DeskView.getGame().tromfClicked(cid);
		}else if (kolo == -3) {
			DeskView.print("pozhovej", 0);
		}else if (kolo == -2) {
			DeskView.getGame().talonClicked(cid);
		}else if (kolo == -1) {
			//TODO DeskView.getGame().bd.show();
			//TODO DeskView.getGame().bd.raise();
		} else {
			DeskView.getGame().cardClicked(cid);
		}
	}
}
