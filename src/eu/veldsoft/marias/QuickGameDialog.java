package eu.veldsoft.marias;

import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.SharedPreferences;
import android.provider.ContactsContract.CommonDataKinds.Event;

/**
 * ...
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 16 Aug 2013
 */
class QuickGameDialog {
	// TODO It is only for project to compile.
	private static class Qt {
		public static class WindowFlags {
			public WindowFlags() {
			}

			public WindowFlags(int i) {
			}
		}

		public static final int Checked = 1;
		public static final int Unchecked = 0;
		public static final int CustomizeWindowHint = 0;
		public static final int WindowMinimizeButtonHint = 0;
		public static final int WindowSystemMenuHint = 0;
		public static final int WindowCloseButtonHint = 0;
	}

	// TODO It is only for project to compile.
	private static class Widget {
	}

	private void setWindowFlags(Qt.WindowFlags flags) {
	}

	private Qt.WindowFlags windowFlags() {
		return null;
	}

	private void setupUi(QuickGameDialog quickGameDialog) {
	}

	private void checkBox_setChecked(boolean b) {
	}

	private void lineEdit_setText(String string) {
	}

	private void label_4_setText(String name) {
	}

	private void label_3_setText(String name) {
	}

	private void label_2_setText(String name) {
	}

	private void lcdNumber_3_display(int peniaze) {
	}

	private void lcdNumber_2_display(int peniaze) {
	}

	private void lcdNumber_display(int peniaze) {
	}

	private void label_6_setText(String string) {
	}

	private void progressBar_setValue(int i) {
	}

	private void label_5_setVisible(boolean b) {
	}

	private void label_5_setEnabled(boolean b) {
	}

	private void lineEdit_setVisible(boolean b) {
	}

	private void lineEdit_setEnabled(boolean b) {
	}

	public int checkBox_2_checkState() {
		return 0;
	}

	public int spinBox_value() {
		return 0;
	}

	public String lineEdit_text() {
		return "";
	}

	public int checkBox_checkState() {
		return 0;
	}

	private void retranslateUi(QuickGameDialog quickGameDialog) {
	}

	private static final int Event_LanguageChange = -1;

	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(QuickGameDialog.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * 
	 */
	private QuickGameDialog ui;

	/**
	 * 
	 */
	private Game game;

	/**
	 * 
	 */
	private QuickGameThread thread;

	/**
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void on_pushButton_2_clicked() {
		game.resetMoney();
		progress();
	}

	/**
	 * 
	 * @param state
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void on_checkBox_stateChanged(int state) {
		ui.lineEdit_setEnabled(state == Qt.Unchecked);
		ui.lineEdit_setVisible(state == Qt.Unchecked);
		ui.label_5_setEnabled(state == Qt.Unchecked);
		ui.label_5_setVisible(state == Qt.Unchecked);
	}

	/**
	 * 
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void on_pushButton_clicked() {
		LOGGER.info("quickGame start");
		thread.start();
	}

	/**
	 * 
	 * @param value
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void on_checkBox_2_stateChanged(int value) {
		/*
		 * Do nothing, quickgamethread do everything.
		 */
	}

	/**
	 * 
	 * @param event
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	protected void changeEvent(Event event) {
		switch (event.getTypeResource(0)) {
		case Event_LanguageChange:
			ui.retranslateUi(this);
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @param event
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	protected void closeEvent(Event event) {
		LOGGER.info("quickGame close event");
		thread.terminate();
		game.quickGame = false;
		ui.progressBar_setValue(0);

		SharedPreferences.Editor editor = game.gameActivity.getPreferences(
				game.gameActivity.MODE_PRIVATE).edit();
		editor.putBoolean("shuffling_random",
				(ui.checkBox_checkState() == Qt.Checked ? true : false));
		editor.putInt("shuffling_seed", Integer.valueOf(ui.lineEdit_text()));
		editor.commit();
	}

	/**
	 * 
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	protected void reject() {
		LOGGER.info("quickGame reject");
		thread.terminate();
		game.quickGame = false;
		ui.progressBar_setValue(0);
		
		SharedPreferences.Editor editor = game.gameActivity.getPreferences(
				game.gameActivity.MODE_PRIVATE).edit();
		editor.putBoolean("shuffling_random",
				(ui.checkBox_checkState() == Qt.Checked ? true : false));
		editor.putInt("shuffling_seed", Integer.valueOf(ui.lineEdit_text()));
		editor.commit();
	}

	/**
	 * 
	 * @param g
	 * @param parent
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public QuickGameDialog(Game g, Widget parent) {
		game = g;

		ui.setupUi(this);

		Qt.WindowFlags flags = windowFlags();

		flags = new Qt.WindowFlags(Qt.CustomizeWindowHint
				| Qt.WindowMinimizeButtonHint | Qt.WindowSystemMenuHint
				| Qt.WindowCloseButtonHint);

		setWindowFlags(flags);

		thread = new QuickGameThread(game, ui);

		// TODO Event handling is different in Android.
		// connect(thread,SIGNAL(progress(int)),this,SLOT(progress(int)));
	}

	/**
	 * 
	 * @param g
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public QuickGameDialog(Game g) {
		this(g, null);
	}

	/**
	 * Updates the ui when heavy duty is on. -1 means the progress bar not to be
	 * updated
	 * 
	 * @param step
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void progress(int step) {
		if (step > -1) {
			ui.progressBar_setValue(step);
		}
		ui.label_6_setText("" + step);
		ui.lcdNumber_display(game.players.get(0).peniaze);
		ui.lcdNumber_2_display(game.players.get(1).peniaze);
		ui.lcdNumber_3_display(game.players.get(2).peniaze);
	}

	/**
	 * 
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void progress() {
		progress(-1);
	}

	/**
	 * 
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void show() {
		ui.label_2_setText(game.players.get(0).name);
		ui.label_3_setText(game.players.get(1).name);
		ui.label_4_setText(game.players.get(2).name);
		
		SharedPreferences preferences = game.gameActivity
				.getPreferences(game.gameActivity.MODE_PRIVATE);
		
		ui.lineEdit_setText("" + preferences.getInt("shuffling_seed", 47));
		ui.checkBox_setChecked(preferences.getBoolean("shuffling_random", true));
	}
}
