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
public class CardItem /* extends QObject, QGraphicsPixmapItem */{
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(Card.class.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	// Q_OBJECT
	// Q_PROPERTY(QPointF pos READ pos WRITE setPos)
	// Q_PROPERTY(qreal x READ x WRITE setX)
	// Q_PROPERTY(qreal y READ y WRITE setY)
	// Q_PROPERTY(qreal opacity READ opacity WRITE setOpacity)
	// Q_PROPERTY(qreal z READ zValue WRITE setZValue)

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
			DeskView.getGame().marias.on_actionNew_game_triggered();
		}else if (kolo == -4) {
			DeskView.getGame().tromfClicked(cid);
		}else if (kolo == -3) {
			DeskView.print("pozhovej", 0);
		}else if (kolo == -2) {
			DeskView.getGame().talonClicked(cid);
		}else if (kolo == -1) {
			DeskView.getGame().bd.show();
			DeskView.getGame().bd.raise();
		} else {
			DeskView.getGame().cardClicked(cid);
		}
	}
}
