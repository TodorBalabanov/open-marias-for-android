package eu.veldsoft.marias;

import java.util.logging.Level;
import java.util.logging.Logger;

import android.provider.ContactsContract.CommonDataKinds.Event;

/**
 * ...
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 16 Aug 2013
 */
class SettingsDialog {
	// TODO It is only for project to compile.
	private static class Qt {
		public static final int Checked = 1;
		public static final int Unchecked = 0;
	}

	// TODO It is only for project to compile.
	private static class Widget {
	}

	// TODO It is only for project to compile.
	private static class Settings {
		public static final int IniFormat = 0;

		public Settings(String string, int value) {
		}

		public void setValue(String key, int value) {
		}

		public void setValue(String key, String value) {
		}

		public String value(String key, String value) {
			return "";
		}

		public int value(String key, int i) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	private void comboBox_3_setCurrentIndex(int i) {
	}

	private Object comboBox_3_itemText(int i) {
		return null;
	}

	private int comboBox_3_count() {
		return 0;
	}

	private void comboBox_2_setCurrentIndex(int i) {
	}

	private Object comboBox_2_itemText(int i) {
		return null;
	}

	private int comboBox_2_count() {
		return 0;
	}

	private void comboBox_setCurrentIndex(int i) {
	}

	private Object comboBox_itemText(int i) {
		return null;
	}

	private int comboBox_count() {
		return 0;
	}

	private void checkBox_setChecked(boolean b) {
	}

	private void lineEdit_4_setText(int value) {
	}

	private void spinBox_setValue(int value) {
	}

	private void setupUi(SettingsDialog settingsDialog) {
	}

	private void retranslateUi(SettingsDialog settingsDialog) {
	}

	private void lineEdit_setText(String comboBox_currentText) {
	}

	private void lineEdit_2_setText(String comboBox_2_currentText) {
	}

	private void lineEdit_3_setText(String comboBox_3_currentText) {
	}

	private void label_7_setVisible(boolean b) {
	}

	private void label_7_setEnabled(boolean b) {
	}

	private void lineEdit_4_setVisible(boolean b) {
	}

	private void lineEdit_4_setEnabled(boolean b) {
	}

	private void pushButton_setEnabled(boolean b) {
	}

	private void close() {
	}

	private String lineEdit_text() {
		return "";
	}

	private String comboBox_3_currentText() {
		return "";
	}

	private String comboBox_2_currentText() {
		return "";
	}

	private String comboBox_currentText() {
		return "";
	}

	private String lineEdit_2_text() {
		return "";
	}

	private String lineEdit_3_text() {
		return "";
	}

	private String lineEdit_4_text() {
		return "";
	}

	private int checkBox_checkState() {
		return 0;
	}

	private int spinBox_value() {
		return 0;
	}

	private static final int Event_LanguageChange = -1;

	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(SettingsDialog.class.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * 
	 */
	private SettingsDialog ui;

	/**
	 * 
	 */
	private Settings settings;

	/**
	 * 
	 */
	private Game game;

	/**
	 * 
	 */
	private QuickGameDialog quick;

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void checkForQuickGame() {
		if (ui.comboBox_currentText().equals("HumanPlayer") == false
				&& ui.comboBox_2_currentText().equals("HumanPlayer") == false
				&& ui.comboBox_3_currentText().equals("HumanPlayer") == false) {
			ui.pushButton_setEnabled(true);
		} else {
			ui.pushButton_setEnabled(false);
		}
	}

	/**
	 * ...
	 * 
	 * @param state
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void on_checkBox_stateChanged(int state) {
		ui.lineEdit_4_setEnabled(state == Qt.Unchecked);
		ui.lineEdit_4_setVisible(state == Qt.Unchecked);
		ui.label_7_setEnabled(state == Qt.Unchecked);
		ui.label_7_setVisible(state == Qt.Unchecked);
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void on_pushButton_clicked() {
		DeskView.cleanGraphics(true);

		LOGGER.info("quickGame dialog");

		accept();

		game.init();
		game.quickGame = true;

		close();

		quick.show();
	}

	/**
	 * ...
	 * 
	 * @param index
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void on_comboBox_3_currentIndexChanged(int index) {
		ui.lineEdit_3_setText(ui.comboBox_3_currentText());
		checkForQuickGame();
	}

	/**
	 * ...
	 * 
	 * @param index
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void on_comboBox_2_currentIndexChanged(int index) {
		ui.lineEdit_2_setText(ui.comboBox_2_currentText());
		checkForQuickGame();
	}

	/**
	 * 
	 * @param index
	 */
	private void on_comboBox_currentIndexChanged(int index) {
		ui.lineEdit_setText(ui.comboBox_currentText());
		checkForQuickGame();
	}

	/**
	 * 
	 * @param e
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
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
	 * 
	 * @param g
	 * @param parent
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public SettingsDialog(Game g, Widget parent) {
		game = g;
		ui.setupUi(this);
		quick = new QuickGameDialog(game);
	}

	/**
	 * ...
	 * 
	 * @param g
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public SettingsDialog(Game g) {
		this(g, null);
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void show() {
		settings = new Settings("marias.ini", Settings.IniFormat);
		ui.spinBox_setValue(settings.value("graphics/delay", 100));
		ui.lineEdit_4_setText(settings.value("shuffling/seed", 47));
		ui.checkBox_setChecked(settings.value("shuffling/random", 1) == 1);
		for (int i = 0; i < ui.comboBox_count(); i++) {
			if (ui.comboBox_itemText(i) == settings.value("players/left",
					"RandomPlayer")) {
				ui.comboBox_setCurrentIndex(i);
			}
		}
		for (int i = 0; i < ui.comboBox_2_count(); i++) {
			if (ui.comboBox_2_itemText(i) == settings.value("players/right",
					"RandomPlayer")) {
				ui.comboBox_2_setCurrentIndex(i);
			}
		}
		for (int i = 0; i < ui.comboBox_3_count(); i++) {
			if (ui.comboBox_3_itemText(i) == settings.value("players/front",
					"HumanPlayer")) {
				ui.comboBox_3_setCurrentIndex(i);
			}
		}
		ui.lineEdit_3_setText(game.players.get(0).name);
		ui.lineEdit_2_setText(game.players.get(2).name);
		ui.lineEdit_setText(game.players.get(1).name);
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void accept() {
		settings.setValue("graphics/delay", ui.spinBox_value());
		settings.setValue("shuffling/random",
				ui.checkBox_checkState() == Qt.Checked ? 1 : 0);
		settings.setValue("shuffling/seed", ui.lineEdit_4_text());

		if (ui.checkBox_checkState() != Qt.Checked) {
			game.rg.setSeed(new Long(ui.lineEdit_4_text()));
		}

		if (settings.value("players/left", "RandomPlayer").equals(
				ui.comboBox_currentText()) == false) {
			game.changePlayer(1, ui.comboBox_currentText());
			settings.setValue("players/left", ui.comboBox_currentText());
		}

		if (settings.value("players/right", "RandomPlayer").equals(
				ui.comboBox_2_currentText()) == false) {
			game.changePlayer(2, ui.comboBox_2_currentText());
			settings.setValue("players/right", ui.comboBox_2_currentText());
		}

		if (settings.value("players/front", "HumanPlayer").equals(
				ui.comboBox_3_currentText()) == false) {
			game.changePlayer(0, ui.comboBox_3_currentText());
			settings.setValue("players/front", ui.comboBox_3_currentText());
		}

		game.players.get(0).name = ui.lineEdit_3_text();
		settings.setValue("players/front_name", game.players.get(0).name);

		game.players.get(1).name = ui.lineEdit_text();
		settings.setValue("players/left_name", game.players.get(1).name);

		game.players.get(2).name = ui.lineEdit_2_text();
		settings.setValue("players/right_name", game.players.get(2).name);

		close();
		DeskView.draw();
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void raise() {
	}
}
