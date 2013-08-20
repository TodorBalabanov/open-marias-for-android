package eu.veldsoft.marias;

import java.util.logging.Level;
import java.util.logging.Logger;

import android.graphics.Rect;
import android.widget.Toast;

/**
 * Main window class. Executes the GUI actions. Manages Game instance.
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 16 Aug 2013
 */
class Marias {
	// TODO It is only for project to compile.
	private static class Settings {
		public static final int IniFormat = 0;

		public Settings(String string, int format) {
		}

		public String value(String string, int i) {
			return "";
		}
	}

	// TODO It is only for project to compile.
	private static class Widget {
		public static void show() {
		}
	}

	public eu.veldsoft.marias.DeskView.GraphicsView graphicsView;

	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(Marias.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * 
	 */
	private Marias ui;

	/**
	 * 
	 */
	private Game game;

	/**
	 * 
	 */
	private SettingsDialog sd;

	private void setupUi(Marias marias) {
	}

	public Rect getGeomety() {
		return null;
	}

	private Rect geometry() {
		return null;
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void on_actionSettings_triggered() {
		sd.show();

		/*
		 * if sd is showed, but covered by other windows, then bring it to front
		 */
		sd.raise();
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void on_actionAbout_triggered() {
		Settings settings = new Settings("marias.ini", Settings.IniFormat);

		Toast.makeText(
				null,
				"<b>OpenMarias</b>, free OPENsource Marias<br/>"
						+ "2009-2011 by Miso Kovac<br/>"
						+ "See <a href='http://sourceforge.net/projects/openmarias'>http://sourceforge.net/projects/openmarias</a>"
						+ "<br/>"
						+ "Version: <b>"
						+ settings.value("version/major", 0).toString()
						+ "."
						+ settings.value("version/minor", 0).toString()
						+ (settings.value("version/revision", 0).equals("0") ? ""
								: "." + settings.value("version/revision", 0))
						+ "</b>", Toast.LENGTH_SHORT).show();
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	private void on_actionQuit_triggered() {
		System.exit(0);
	}

	/**
	 * ...
	 * 
	 * @param parent
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public Marias(Widget parent) {
		ui.setupUi(this);
		game = new Game(this);
		game.init();
		DeskView.createInstance(ui, game);
		sd = new SettingsDialog(game);
	}

	/**
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public Rect getGeometry() {
		return (geometry());
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void show() {
		Widget.show();
		DeskView.intro();
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void on_actionNew_game_triggered() {
		LOGGER.info("new game");
		game.newGame();
		LOGGER.info("new game end");
	}
}
