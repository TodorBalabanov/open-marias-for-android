package eu.veldsoft.marias;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.graphics.Rect;
import android.provider.ContactsContract.CommonDataKinds.Event;

/**
 * ...
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 19 Aug 2013
 */
class BiddingDialog {
	// TODO It is only for project to compile.
	private static class Qt {
		public static final int Checked = 1;
		public static final int Unchecked = 0;
	}

	// TODO It is only for project to compile.
	private static class Widget {
	}

	private static final int Event_LanguageChange = -1;

	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(BiddingDialog.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * 
	 */
	private BiddingDialog ui;

	/**
	 * 
	 */
	private Game game;

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	private List<Integer> history;

	private void checkBox_stovka_setEnabled(boolean b) {
	}

	private void checkBox_sedma_setEnabled(boolean b) {
	}

	private void checkBox_hra_setEnabled(boolean b) {
	}

	private void checkBox_stovka_setText(String string) {
	}

	private void checkBox_sedma_setText(String string) {
	}

	private void checkBox_hra_setText(String string) {
	}

	private void checkBox_stovka_setChecked(boolean b) {
	}

	private void checkBox_sedma_setChecked(boolean b) {
	}

	private void textEdit_append(String string) {
	}

	private double log2(int flekNaHru) {
		return (Math.log10(flekNaHru) / Math.log10(2));
	}

	private void setGeometry(int i, int j, int width, int height) {
	}

	private void textEdit_clear() {
	}

	private void checkBox_hra_setChecked(boolean b) {
	}

	private void setupUi(BiddingDialog biddingDialog) {
	}

	private void retranslateUi(BiddingDialog biddingDialog) {
	}

	private int checkBox_stovka_checkState() {
		return 0;
	}

	private int checkBox_sedma_checkState() {
		return 0;
	}

	private int checkBox_hra_checkState() {
		return 0;
	}

	private int width() {
		return (0);
	}

	private int height() {
		return (0);
	}

	public void show() {
	}

	public void raise() {
	}

	public boolean isVisible() {
		return false;
	}

	public void hide() {
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	private void on_pushButton_released() {
		int res = 0;

		if (ui.checkBox_hra_checkState() == Qt.Checked) {
			res |= 4;
		}

		if (ui.checkBox_sedma_checkState() == Qt.Checked) {
			res |= 2;
		}

		if (ui.checkBox_stovka_checkState() == Qt.Checked) {
			res |= 1;
		}

		bid(res);
	}

	/**
	 * ...
	 * 
	 * @param e
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	protected void changeEvent(Event e) {
		switch (e.getTypeResource(0)) {
		case Event_LanguageChange:
			ui.retranslateUi(this);
			break;
		default:
			break;
		}
	}

	/**
	 * ...
	 * 
	 * @param g
	 * @param parent
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public BiddingDialog(Game g, Widget parent) {
		game = g;
		ui.setupUi(this);
	}

	/**
	 * ...
	 * 
	 * @param g
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public BiddingDialog(Game g) {
		this(g, null);
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public void startBidding() {
		game.profiler.start("bidding");

		history.clear();

		if (game.quickGame == false) {
			class LocalRect {
				public LocalRect(Rect geomety) {
				}

				public int x() {
					return 0;
				}

				public int y() {
					return 0;
				}

				public int width() {
					return 0;
				}

				public int height() {
					return 0;
				}
			}

			// TODO LocalRect mainW = new
			// LocalRect(game.gameActivity.getGeomety());

			// TODO setGeometry(mainW.x() + 350 + (mainW.width() - 802) / 2,
			// mainW.y() + 280 + (mainW.height() - 641) / 2, width(), height());

			show();

			raise();

			ui.textEdit_clear();

			if (game.stav.hra.farba) {
				DeskView.revealCard(game.stav.hra.tromf);
			}
		}

		if (game.players.get(game.stav.id).type.equals("human") == false
				|| game.quickGame == true) {
			bid(game.players.get(game.stav.id).bid() | 4);
		} else {
			updateUi();
			ui.checkBox_hra_setChecked(true);
		}
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public void endBidding() {
		if (game.quickGame == false) {
			LOGGER.info("end bidding");
		}

		game.stav.vysid = game.stav.forhont;
		game.stav.id = game.stav.forhont;
		game.stav.kolo = 0;

		if (game.quickGame == false) {
			if (game.stav.hra.farba) {
				DeskView.ejectTromf(game.stav.hra.tromf, true);
			}

			hide();
		}

		game.profiler.stop("bidding");
		game.profiler.stop("dealing and bidding");
		game.profiler.stop("game - prepare");
		game.profiler.start("game - trick taking");

		game.animationFinished(0);
	}

	/**
	 * ...
	 * 
	 * @param res
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public void bid(int res) {
		/*
		 * spracuj bid
		 */

		if (game.quickGame == false) {
			LOGGER.info(game.players.get(game.stav.id).name + ": " + res);
		}

		String text = "";

		boolean ideForhont = game.stav.id == game.stav.forhont;

		if ((res & 4) != 0 && game.stav.hra.flekNaHru < 128) {
			if (game.stav.hra.flekNaHru == 0) {
				if (ideForhont == true) {
					text = "Hra!";
					game.stav.hra.flekNaHru = 1;
				} else {
					LOGGER.info("Opponent " + game.stav.id
							+ " hlasi hru skor ako forhont!");
				}
			} else {
				if (ideForhont ^ ((int) log2(game.stav.hra.flekNaHru) % 2 == 0)) {
					game.stav.hra.flekNaHru *= 2;
					text += ("" + GlobalStrings.flek(game.stav.hra.flekNaHru) + " na hru!");
				}
			}
		}

		if ((res & 2) != 0 && game.stav.hra.flekNaSedmu < 128) {
			if (game.stav.hra.sedma) {
				if (ideForhont
						^ ((int) log2(game.stav.hra.flekNaSedmu) % 2 == 0)) {
					game.stav.hra.flekNaSedmu *= 2;
					text += ("" + GlobalStrings.flek(game.stav.hra.flekNaSedmu) + " na sedmu!");
				}
			} else if (game.stav.hra.sedmaProti) {
				if (ideForhont
						^ ((int) log2(game.stav.hra.flekNaSedmu) % 2 == 1)) {
					game.stav.hra.flekNaSedmu *= 2;
					text += ("" + GlobalStrings.flek(game.stav.hra.flekNaSedmu) + " na sedmu proti!");
				}
			} else {
				if (ideForhont) {
					game.stav.hra.sedma = true;
					text += "Sedma!";
				} else {
					game.stav.hra.sedmaProti = true;
					text += "Sedma proti!";
				}
				game.stav.hra.flekNaSedmu = 1;
			}
		}

		if ((res & 1) != 0 && game.stav.hra.flekNaStovku < 128) {
			if (game.stav.hra.stovka) {
				if (ideForhont
						^ ((int) log2(game.stav.hra.flekNaStovku) % 2 == 0)) {
					game.stav.hra.flekNaStovku *= 2;
					text += (""
							+ GlobalStrings.flek(game.stav.hra.flekNaStovku) + " na stovku!");
				}
			} else if (game.stav.hra.stovkaProti) {
				if (ideForhont
						^ ((int) log2(game.stav.hra.flekNaStovku) % 2 == 1)) {
					game.stav.hra.flekNaStovku *= 2;
					text += (""
							+ GlobalStrings.flek(game.stav.hra.flekNaStovku) + " na sto proti!");
				}
			} else {
				if (ideForhont == true) {
					game.stav.hra.stovka = true;
					text += "Stovka!";
				} else {
					game.stav.hra.stovkaProti = true;
					text += "Sto proti!";
				}
				game.stav.hra.flekNaStovku = 1;
			}
		}

		boolean endBid = false;
		if (text == "") {
			if (ideForhont == true) {
				endBid = true;
			} else if (history.get(history.size() - 1) == 0) {
				/*
				 * pred nim nebol forhont, lebo by to skoncilo uz predtym
				 */
				endBid = true;
			}
			history.add(0);
			text = "Mlcim.";
		} else {
			history.add(res);
		}

		if (game.quickGame == false) {
			DeskView.print(text, game.stav.id);
		}

		if (game.quickGame == false) {
			ui.textEdit_append(game.players.get(game.stav.id).name + ": "
					+ text);
		}

		if (endBid == true) {
			endBidding();
			return;
		}

		game.stav.dalsi();

		if (game.players.get(game.stav.id).type.equals("human")
				&& game.quickGame == false) {
			updateUi();
			return;
		}

		bid(game.players.get(game.stav.id).bid());
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public void updateUi() {
		boolean ideForhont = game.stav.id == game.stav.forhont;

		ui.checkBox_hra_setChecked(false);
		ui.checkBox_sedma_setChecked(false);
		ui.checkBox_stovka_setChecked(false);

		if (ideForhont && game.stav.hra.flekNaHru == 0) {
			ui.checkBox_hra_setText("Hra");
		} else {
			ui.checkBox_hra_setText(""
					+ GlobalStrings.flek(game.stav.hra.flekNaHru * 2)
					+ " na hru");
		}

		if (game.stav.hra.sedma == false && game.stav.hra.sedmaProti == false) {
			if (ideForhont == true) {
				ui.checkBox_sedma_setText("Sedma");
			} else {
				ui.checkBox_sedma_setText("Sedma proti");
			}
		} else
			ui.checkBox_sedma_setText(""
					+ GlobalStrings.flek(game.stav.hra.flekNaSedmu * 2)
					+ " na sedmu" + (game.stav.hra.sedmaProti ? " proti" : ""));

		if (game.stav.hra.stovka == false && game.stav.hra.stovkaProti == false) {
			if (ideForhont == true) {
				ui.checkBox_stovka_setText("Stovka");
			} else {
				ui.checkBox_stovka_setText("Stovka proti");
			}
		} else {
			ui.checkBox_stovka_setText(""
					+ GlobalStrings.flek(game.stav.hra.flekNaStovku * 2)
					+ " na stovku"
					+ (game.stav.hra.stovkaProti ? " proti" : ""));
		}

		int bids = getAvailableBids();
		ui.checkBox_hra_setEnabled((bids & 4) != 0);
		ui.checkBox_sedma_setEnabled((bids & 2) != 0);
		ui.checkBox_stovka_setEnabled((bids & 1) != 0);

		if (ideForhont && game.stav.hra.flekNaHru == 0) {
			ui.checkBox_hra_setEnabled(false);
		}
	}

	/**
	 * ...
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public int getAvailableBids() {
		int bids = 0;

		boolean ideForhont = game.stav.id == game.stav.forhont;

		if (game.stav.hra.flekNaHru == 0
				|| (game.stav.hra.flekNaHru < 128 && (((int) log2(game.stav.hra.flekNaHru) % 2 == 0) ^ ideForhont))) {
			bids |= 4;
		}

		if (game.stav.hra.flekNaSedmu < 128) {
			if (game.stav.hra.flekNaSedmu == 0) {
				bids |= 2;
			} else if ((((int) log2(game.stav.hra.flekNaSedmu) % 2 == 0) ^ ideForhont)
					&& game.stav.hra.sedma) {
				bids |= 2;
			} else if ((((int) log2(game.stav.hra.flekNaSedmu) % 2 == 1) ^ ideForhont)
					&& game.stav.hra.sedmaProti) {
				bids |= 2;
			}
		}

		if (game.stav.hra.flekNaStovku < 128) {
			if (game.stav.hra.flekNaStovku == 0) {
				bids |= 1;
			} else if ((((int) log2(game.stav.hra.flekNaStovku) % 2 == 0) ^ ideForhont)
					&& game.stav.hra.stovka) {
				bids |= 1;
			} else if ((((int) log2(game.stav.hra.flekNaStovku) % 2 == 1) ^ ideForhont)
					&& game.stav.hra.stovkaProti) {
				bids |= 1;
			}
		}

		return bids;
	}
}
