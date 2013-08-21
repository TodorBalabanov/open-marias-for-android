package eu.veldsoft.marias;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ...
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 16 Aug 2013
 */
class QuickGameThread extends Thread {
	// TODO It is only for project to compile.
	private static class Qt {
		public static final int Checked = 1;
		public static final int Unchecked = 0;
	}

	public void terminate() {
	}

	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(QuickGameThread.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * 
	 */
	public QuickGameDialog ui;

	/**
	 * 
	 */
	public Game game;

	/**
	 * ...
	 * 
	 * @param g
	 * @param qui
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public QuickGameThread(Game g, QuickGameDialog qui) {
		game = g;
		ui = qui;
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void run() {
		game.profiler.reset();

		if (ui.checkBox_checkState() == Qt.Checked) {
			MainActivity.PRNG.setSeed(-1);
		} else {
			MainActivity.PRNG.setSeed(new Integer(ui.lineEdit_text()));
		}

		// TODO Android specific implementation.
		// ui.progressBar.setMaximum(ui.spinBox.value());

		for (int i = 0; i < ui.spinBox_value(); i++) {
			game.profiler.start("game");
			game.profiler.start("game - prepare");

			if (ui.checkBox_2_checkState() == Qt.Unchecked) {
				/*
				 * so the next in the new game is 0=front player
				 */
				game.stav.forhont = 2;
			}

			game.newGame();

			game.profiler.stop("game - after results");
			game.profiler.start("game - update ui");

			if (ui.spinBox_value() < 100000 || i % 100 == 0) {
				// TODO Do it in UI thread as it is needed in Android.
				progress(i + 1);
			}

			game.profiler.stop("game - update ui");
			game.profiler.stop("game");
		}

		Map<String, Double> map = game.profiler.totals;

		for (Map.Entry<String, Double> entry : map.entrySet()) {
			LOGGER.info(entry.getKey() + ": " + entry.getValue());
		}
	}

	/**
	 * Report progress.
	 * 
	 * @param step
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void progress(int step) {
		// TODO Android specific implementation.
	}
}
