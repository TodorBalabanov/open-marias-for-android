package eu.veldsoft.marias;

import android.provider.ContactsContract.CommonDataKinds.Event;

/**
 * 
 *
 */
class QuickGameDialog {

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
	 */
	private void on_pushButton_2_clicked() {
		// TODO To be done by ...
	}

	/**
	 * 
	 * @param state
	 */
	private void on_checkBox_stateChanged(int state) {
		// TODO To be done by ...
	}

	/**
	 * 
	 */
	private void on_pushButton_clicked() {
		// TODO To be done by ...
	}

	/**
	 * 
	 * @param value
	 */
	private void on_checkBox_2_stateChanged(int value) {
		// TODO To be done by ...
	}

	/**
	 * 
	 * @param event
	 */
	protected void changeEvent(Event event) {
		// TODO To be done by ...
	}

	/**
	 * 
	 * @param event
	 */
	protected void closeEvent(Event event) {
		// TODO To be done by ...
	}

	/**
	 * 
	 */
	protected void reject() {
		// TODO To be done by ...
	}

	// TODO Depends of Android GUI.
	// public QuickGameDialog(Game g, QWidget parent){
	// }

	public QuickGameDialog(Game g) {
		// this(g, null);
	}

	/**
	 * Updates the ui when heavy duty is on. -1 means the progress bar not to be
	 * updated
	 * 
	 * @param step
	 */
	public void progress(int step) {
		// TODO To be done by ...
	}

	/**
	 * 
	 */
	public void progress() {
		// TODO To be done by ...
		progress(-1);
	}

	/**
	 * 
	 */
	public void show() {
		// TODO To be done by ...
	}
}
