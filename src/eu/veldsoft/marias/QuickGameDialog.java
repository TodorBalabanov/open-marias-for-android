package eu.veldsoft.marias;

import android.provider.ContactsContract.CommonDataKinds.Event;

class QuickGameDialog {
	private QuickGameDialog ui;

	private Game game;

	private QuickGameThread thread;

	private void on_pushButton_2_clicked() {
	}

	private void on_checkBox_stateChanged(int state) {
	}

	private void on_pushButton_clicked() {
	}

	private void on_checkBox_2_stateChanged(int value) {
	}

	protected void changeEvent(Event event) {
	}

	protected void closeEvent(Event event) {
	}

	protected void reject() {
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
	 */
	public void progress(int step) {
	}

	public void progress() {
		progress(-1);
	}

	public void show() {
	}
}
