package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ...
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 15 Aug 2013
 */
public class DeskView {
	// TODO It is only for project to compile.
	private static class Marias {
		public GraphicsView graphicsView;
	}

	// TODO It is only for project to compile.
	private static class GraphicsPixmapItem {

		public void setPixmap(Pixmap pixmap) {
		}

		public void setPos(Point point) {
		}

		public void setZValue(int i) {
		}
	}

	// TODO It is only for project to compile.
	private static class GraphicsTextItem {

		public GraphicsTextItem(String string) {
		}

		public GraphicsTextItem() {
		}

		public void setDefaultTextColor(int white) {
		}

		public void setPos(Point point) {
		}

		public void setFont(Font font) {
		}

		public void setPos(int i, int j) {
		}

		public void setHtml(String string) {
		}

		public void setTextInteractionFlags(int textbrowserinteraction) {
		}

		public void setCursor(int pointinghandcursor) {
		}
	}

	// TODO It is only for project to compile.
	private static class SequentialAnimationGroup {

		public void addAnimation(PropertyAnimation ax) {
		}

		public void start(String deletewhenstopped) {
		}

		public void addPause(int i) {
		}

		public void addAnimation(ParallelAnimationGroup berStich) {
		}
	}

	// TODO It is only for project to compile.
	private static class ParallelAnimationGroup {

		public void addAnimation(SequentialAnimationGroup xs) {
		}

		public void start(String deletewhenstopped) {
		}

		public void addAnimation(PropertyAnimation anim) {
		}
	}

	// TODO It is only for project to compile.
	private static class FileInfo {
		public String baseName() {
			return null;
		}

		public boolean isFile() {
			return false;
		}
	}

	// TODO It is only for project to compile.
	private static class DirIterator {

		public DirIterator(Dir dir) {
		}

		public boolean hasNext() {
			return false;
		}

		public FileInfo fileInfo() {
			return null;
		}

		public String filePath() {
			return "";
		}
	}

	// TODO It is only for project to compile.
	private static class Qt {
		public static final int Checked = 1;
		public static final int Unchecked = 0;
		public static final int SolidPattern = 2;
		public static final int TextBrowserInteraction = 4;
		public static final int PointingHandCursor = 8;
	}

	// TODO It is only for project to compile.
	private static class Brush {
		public Brush(RadialGradient gradient) {
		}

		public Brush(Object fromRgb, int solidpattern) {
		}
	}

	// TODO It is only for project to compile.
	private static class Color {
		public static final int YELLOW = 0;
		public static final int WHITE = 0;

		public static Object fromRgbF(int i, int j, int k, int l) {
			return null;
		}

		public static Object fromRgb(int i, int j, int k, int l) {
			return null;
		}
	}

	// TODO It is only for project to compile.
	private static class RadialGradient {
		public RadialGradient(int i, int j, int k) {
		}

		public void setColorAt(int i, Object fromRgbF) {
		}
	}

	// TODO It is only for project to compile.
	private static class Dir {
		public Dir(String fName) {
		}

		public boolean exists() {
			return false;
		}

		public static int current() {
			return 0;
		}
	}

	// TODO It is only for project to compile.
	private static class GraphicsScene {
		public GraphicsScene(Object sceneRect) {
		}

		public void addItem(SuperText sText) {
		}

		public void addItem(GraphicsEllipseItem graphicsEllipseItem) {
		}

		public void addItem(GraphicsTextItem text) {
		}
	}

	// TODO It is only for project to compile.
	private static class AbstractAnimation {
		public static final String DeleteWhenStopped = "";
	}

	// TODO It is only for project to compile.
	private static class EasingCurve {
		public static final int InQuad = 1;
		public static final int InOutSine = 2;
	}

	// TODO It is only for project to compile.
	static class Point {
		public Point(int i, int j) {
		}

		public Double x() {
			return 0.0D;
		}

		public Double y() {
			return 0.0D;
		}
	}

	// TODO It is only for project to compile.
	private static class PropertyAnimation {

		public PropertyAnimation(SuperText sText, String string) {
		}

		public PropertyAnimation(CardItem cardItem, String string) {
		}

		public void setDuration(int i) {
		}

		public void setEndValue(int i) {
		}

		public void setEasingCurve(int inoutsine) {
		}

		public void start(String deletewhenstopped) {
		}

		public void setStartValue(Double double1) {
		}

		public void setEndValue(Double double1) {
		}

		public void setEndValue(Point poziciaBase) {
		}
	}

	// TODO It is only for project to compile.
	public static class Scene {
		public List<GraphicsItem> items() {
			return null;
		}

		public void removeItem(GraphicsItem git) {
		}

		public void addItem(CardItem ci) {
		}

		public void addItem(GraphicsPixmapItem pi) {
		}
	}

	// TODO It is only for project to compile.
	public static class GraphicsView {
		public Scene scene() {
			return (null);
		}

		public void show() {
		}

		public Object sceneRect() {
			return null;
		}

		public void setScene(GraphicsScene graphicsScene) {
		}
	}

	// TODO It is only for project to compile.
	private static class SignalMapper {

		public void setMapping(ParallelAnimationGroup xy, int kolo) {
		}

		public void setMapping(SequentialAnimationGroup rozd, int kolo) {
		}

		public void setMapping(PropertyAnimation anim, int kolo) {
		}
	}

	// TODO It is only for project to compile.
	static class Pixmap {
		public Pixmap(String string) {
		}
	}

	// TODO It is only for project to compile.
	private static class GraphicsEllipseItem {
		public GraphicsEllipseItem(int i, int j, int k, int l) {
		}

		public void setPen(Object fromRgb) {
		}

		public void setBrush(Brush brush) {
		}

		public void setData(int i, String string) {
		}
	}

	// TODO It is only for project to compile.
	private static class Settings {

		public static final int IniFormat = 0;

		public Settings(String string, int value) {
		}

		public String value(String string, int i) {
			return "";
		}

		public String value(String string, String string2) {
			return "";
		}
	}

	// TODO It is only for project to compile.
	static class Font {

		public void setBold(boolean b) {
		}

		public void setFamily(String string) {
		}

		public void setPointSize(int i) {
		}

		public int pointSize() {
			return 0;
		}
	}

	// TODO It is only for project to compile.
	private static class GraphicsItem {

		public String data(int i) {
			return null;
		}

		public GraphicsItem parentItem() {
			return null;
		}
	}

	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(DeskView.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * instance
	 */
	private static DeskView instance;

	/**
	 * 
	 */
	private GraphicsScene graphicsScene;

	/**
	 * 
	 */
	private GraphicsView graphicsView;

	/**
	 * 
	 */
	private SignalMapper signalMapper;

	/**
	 * Pointer to game
	 */
	private Game game;

	/**
	 * Pointers to graphics items - cards. They are only moved from one position
	 * to another instead of creating and destroying.
	 */
	private CardItem cardItem[] = new CardItem[32];

	/**
	 * Image of the card back.
	 */
	private Pixmap rub;

	/**
	 * Images of cards.
	 */
	private Pixmap images[] = new Pixmap[32];

	/**
	 * Tromf icons images.
	 */
	private Pixmap suits[] = new Pixmap[4];

	/**
	 * Console output.
	 */
	private List<String> out = new ArrayList<String>();

	/**
	 * positions in the graphics view for each of the 3 players: stack
	 */
	private Point poziciaKopy[] = new Point[3];

	/**
	 * cards (upper left corner of the leftmost card)
	 */
	private Point poziciaKariet[] = new Point[3];

	/**
	 * tromf icon
	 */
	private Point poziciaTromfu[] = new Point[3];

	/**
	 * name label
	 */
	private Point poziciaMena[] = new Point[3];

	/**
	 * where stich(trick) goes when a players takes it
	 */
	private Point poziciaStichov[] = new Point[3];

	/**
	 * where supertext is animated
	 */
	private Point poziciaTextu[] = new Point[3];

	/**
	 * deck and then talon
	 */
	private Point poziciaBase;

	/**
	 * graphics items of forhont light
	 */
	private GraphicsEllipseItem lightSimple[] = new GraphicsEllipseItem[3];

	/**
	 * 
	 */
	private GraphicsEllipseItem lightGradient[] = new GraphicsEllipseItem[3];

	/**
	 * 
	 */
	private Settings settings;

	/**
	 * 
	 */
	private Font font;

	/**
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public static DeskView Instance() {
		if (instance == null) {
			LOGGER.info("DeskView: no instance to get");
		}
		return instance;
	}

	/**
	 * Instance should be created only once. Now it is created by Marias class.
	 * It takes two arguments - ui of the marias for access to graphics view and
	 * game for access to state of the game and players
	 * 
	 * @param ui
	 * @param g
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public static void createInstance(Marias ui, Game g) {
		if (instance != null) {
			LOGGER.info("DeskView: create instance called twice");
		}

		instance = new DeskView(ui, g);
	}

	/**
	 * Instance is created by Marias, so it is also destroyed only by Marias.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public static void destroyInstance() {
		if (instance == null) {
			LOGGER.info("DeskView: destroying null class");
		}

		instance = null;
	}

	/**
	 * Cleans graphics scene of graphics items, so they won't be stored in
	 * memory anymore. By default, cards and animations are not removed, because
	 * cards are not drawed, only moved, so they stay. Animations are removed
	 * after they are finished.
	 * 
	 * @param all
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public static void cleanGraphics(boolean all) {
		Instance()._cleanGraphics(all);
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public static void cleanGraphics() {
		cleanGraphics(false);
	}

	/**
	 * Gathers all cards from desk, cleans all animations if some remains and
	 * recreates the cards at deck.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public static void gather() {
		Instance()._gather();
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public static void intro() {
		Instance()._intro();
	}

	/**
	 * Moves card with id cid to position so it appears at the hand of player
	 * with id pid at position offset. It is used during card dealing and in
	 * case when a players plays a card and a gap arises in the hand - the cards
	 * are shifted then. Note: offset is zero-based.
	 * 
	 * @param cid
	 * @param pid
	 * @param offset
	 * @param pause
	 * @param last
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public static void rozdaj(int cid, int pid, int offset, int pause,
			boolean last) {
		Instance()._rozdaj(cid, pid, offset, pause, last);
	}

	/**
	 * ...
	 * 
	 * @param cid
	 * @param pid
	 * @param offset
	 * @param pause
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void rozdaj(int cid, int pid, int offset, int pause) {
		rozdaj(cid, pid, offset, pause, false);
	}

	/**
	 * Talon is a special keyword of this game. Google translated it as "kitty",
	 * but it evokes me some kind of cat:) The forhont chooses 2 cards that
	 * other players don't see and he is not playing with that cards. After
	 * this, all players has exactly 10 cards. Talon consists of that 2 cards
	 * and is located at the position of the former card pile. cid is id of the
	 * card, forhont wants to give away. If the flag last is on, it means, the
	 * last card has been given to talon and the finish of the animation is
	 * connected to next action.
	 * 
	 * @param cid
	 * @param last
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void talon(int cid, boolean last) {
		Instance()._talon(cid, last);
	}

	/**
	 * ...
	 * 
	 * @param cid
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void talon(int cid) {
		talon(cid, false);
	}

	/**
	 * Moves cards in player's hand to their correct positions.
	 * 
	 * @param pid
	 * @param last
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void fixHand(int pid, boolean last) {
		Instance()._fixHand(pid, last);
	}

	/**
	 * 
	 * @param pid
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void fixHand(int pid) {
		fixHand(pid, false);
	}

	/**
	 * Tromf is also a special keyword, it is translated as "trump". It is a
	 * "chosen card" - chosen by forhont. The color(or suit) of the tromf will
	 * be stronger than other colors. At the start of the bidding, it is
	 * revealed to other players. Tromf is then displayed in front of forhont,
	 * so players can remember the chosen color. This function is only called
	 * after tromf has been chosen, so it only ejects the card softly to be
	 * visible.
	 * 
	 * @param cid
	 * @param reverse
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void ejectTromf(int cid, boolean reverse) {
		Instance()._ejectTromf(cid, reverse);
	}

	/**
	 * 
	 * @param cid
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void ejectTromf(int cid) {
		ejectTromf(cid, false);
	}

	/**
	 * Sets the card image. If the former was back of the card, now it will be
	 * front.
	 * 
	 * @param cid
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void revealCard(int cid) {
		Instance()._revealCard(cid);
	}

	/**
	 * Sets the card image to back. The value of the card will therefore be
	 * hidden.
	 * 
	 * @param cid
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void hideCard(int cid) {
		Instance()._hideCard(cid);
	}

	/**
	 * Draws light at forhont, name labels with money, console with game
	 * messages and tromf suit.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void draw() {
		Instance()._draw();
		return;
	}

	/**
	 * Cleans all the graphics and displays results. They are stored in
	 * game.stav.hra.results
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void drawResults() {
		Instance()._drawResults();
	}

	/**
	 * Animate card cid from player pid to the stack (poziciaKopy[pid])
	 * 
	 * @param cid
	 * @param pid
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void animateCard(int cid, int pid) {
		Instance()._animateCard(cid, pid);
	}

	/**
	 * Animate stich(trick) - all 3 cards at the stack(kopa) are transferred to
	 * player pid.
	 * 
	 * @param pid
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void animateStich(int pid) {
		Instance()._animateStich(pid);
	}

	/**
	 * deprecated
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void show() {
		Instance().show();
		return;
	}

	/**
	 * Dirty hook how anyone can easily get pointer to Game (Game have access to
	 * any class).
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static Game getGame() {
		return Instance()._getGame();
	}

	/**
	 * Prints animated text s that says player with id pos. Also logs it.
	 * 
	 * @param s
	 * @param pos
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void print(String s, int pos) {
		Instance()._print(s, pos);
	}

	/**
	 * ...
	 * 
	 * @param s
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void print(String s) {
		print(s, 0);
	}

	/**
	 * Adds given string to console output.
	 * 
	 * @param s
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public static void log(String s) {
		Instance()._log(s);
	}

	/**
	 * 
	 * @param ui
	 * @param g
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 19 Aug 2013
	 */
	public DeskView(Marias ui, Game g) {
		game = g;

		signalMapper = new SignalMapper();

		// TODO Qt specific code.
		// QObject.connect(signalMapper,SIGNAL(mapped(int)),game,SLOT(animationFinished(int)));

		graphicsView = ui.graphicsView;
		graphicsScene = new GraphicsScene(graphicsView.sceneRect());
		graphicsView.setScene(graphicsScene);
		settings = new Settings("marias.ini", Settings.IniFormat);

		/*
		 * INIT IMAGES
		 */

		String fName = settings.value("cards/images", "img/classic7");

		Dir dir = new Dir(fName);

		if (dir.exists() == false) {
			LOGGER.info("Cannot find the directory: " + fName + "now: "
					+ Dir.current());
		}

		DirIterator it = new DirIterator(dir);

		while (it.hasNext() == true) {
			LOGGER.info("" + it.hasNext());
			if (it.fileInfo().isFile()) {
				LOGGER.info(it.fileInfo().baseName());
				if (it.fileInfo().baseName().contains("s")) {
					int k = it.fileInfo().baseName().charAt(1) - '0';
					if (k < 4 && k >= 0) {
						suits[k] = new Pixmap((String) it.filePath());
						LOGGER.info("suit loaded: " + k);
					}
				} else {
					int k = new Integer(it.fileInfo().baseName());
					if (k < 32 && k >= 0) {
						images[k] = new Pixmap((String) it.filePath());
						LOGGER.info("image loaded: " + k);
					}
				}
			}
		}

		rub = new Pixmap("" + settings.value("cards/rub", "img/rub.png"));

		LOGGER.info("rub loaded");

		/*
		 * INIT ITEM POSITIONS
		 */

		poziciaKopy[0] = new Point(330, 275);
		poziciaKopy[1] = new Point(295, 235);
		poziciaKopy[2] = new Point(365, 245);

		poziciaKariet[0] = new Point(200, 445);
		poziciaKariet[1] = new Point(70, 50);
		poziciaKariet[2] = new Point(400, 50);

		poziciaTromfu[0] = new Point(300, 395);
		poziciaTromfu[1] = new Point(50, 160);
		poziciaTromfu[2] = new Point(420, 160);

		poziciaMena[0] = new Point(550, 395);
		poziciaMena[1] = new Point(100, 160);
		poziciaMena[2] = new Point(480, 160);

		poziciaStichov[0] = new Point(700, 445);
		poziciaStichov[1] = new Point(1, 50);
		poziciaStichov[2] = new Point(700, 50);

		poziciaTextu[0] = new Point(400, 350);
		poziciaTextu[1] = new Point(200, 150);
		poziciaTextu[2] = new Point(600, 150);

		poziciaBase = new Point(700, 200);

		/*
		 * INIT LIGHT
		 */

		int cx[] = new int[3];
		int cy[] = new int[3];
		int r[] = new int[3];

		cx[0] = 600;
		cy[0] = 800;
		r[0] = 500;

		cx[1] = -100;
		cy[1] = -100;
		r[1] = 500;

		cx[2] = 700;
		cy[2] = -200;
		r[2] = 500;

		for (int i = 0; i < 3; i++) {
			RadialGradient gradient = new RadialGradient(cx[i], cy[i], r[i]);

			gradient.setColorAt(0, Color.fromRgbF(1, 1, 1, 1));
			gradient.setColorAt(1, Color.fromRgbF(0, 0, 0, 0));

			Brush brush = new Brush(gradient);
			lightGradient[i] = new GraphicsEllipseItem(cx[i] - r[i], cy[i]
					- r[i], 2 * r[i], 2 * r[i]);
			lightGradient[i].setPen(Color.fromRgb(255, 255, 255, 0));
			lightGradient[i].setBrush(brush);
			lightGradient[i].setData(0, "light");
		}

		lightSimple[0] = new GraphicsEllipseItem(100, 300, 800, 600);
		lightSimple[1] = new GraphicsEllipseItem(-300, -300, 800, 600);
		lightSimple[2] = new GraphicsEllipseItem(300, -300, 800, 600);
		for (int i = 0; i < 3; i++) {
			lightSimple[i].setPen(Color.fromRgb(255, 255, 255, 0));
			lightSimple[i].setBrush(new Brush(Color.fromRgb(255, 255, 255,
					i == 0 ? 15 : 25), Qt.SolidPattern));
			lightSimple[i].setData(0, "light");
		}

		font = new Font();
	}

	/**
	 * ...
	 * 
	 * @param all
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 15 Aug 2013
	 */
	public void _cleanGraphics(boolean all) {
		for (GraphicsItem git : graphicsView.scene().items()) {
			if (git.data(0).equals("card") == false
					&& git.data(0).equals("anim") == false
					&& git.parentItem().data(0).equals("anim") == false) {

				graphicsView.scene().removeItem(git);

				if (git.data(0).equals("light") == false) {
				}
			} else if (all == true) {
				if (git.data(0).equals("light") == false) {
					graphicsView.scene().removeItem(git);
				}
			}
		}
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _cleanGraphics() {
		_cleanGraphics(false);
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _gather() {
		cleanGraphics(true);
		for (int i = 0; i < 32; i++) {
			CardItem ci = new CardItem();
			ci.setPixmap(rub);
			ci.setPos(poziciaBase);
			ci.setZValue(i);
			ci.setOpacity(1);
			ci.cid = i;
			ci.setData(0, "card");
			ci.setData(1, i);
			graphicsView.scene().addItem(ci);
			cardItem[i] = ci;
		}
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _intro() {
		gather();

		int n = new Integer(settings.value("intro/length", 10));
		int delay = new Integer(settings.value("graphics/delay", 100));

		List<List<Double>> x = new ArrayList<List<Double>>();
		List<List<Double>> y = new ArrayList<List<Double>>();

		for (int i = 0; i < 32; i++) {
			x.add(new ArrayList<Double>());
			y.add(new ArrayList<Double>());

			for (int j = 0; j < n; j++) {
				x.get(i).add((double) MainActivity.prng.nextInt(700));
				y.get(i).add((double) MainActivity.prng.nextInt(500));
			}

			revealCard(i);
		}

		ParallelAnimationGroup xy = new ParallelAnimationGroup();

		for (int i = 0; i < 32; i++) {
			SequentialAnimationGroup xs = new SequentialAnimationGroup();
			SequentialAnimationGroup ys = new SequentialAnimationGroup();

			cardItem[i].setPos(x.get(i).get(0), y.get(i).get(0));

			for (int j = 1; j < n; j++) {
				PropertyAnimation ax = new PropertyAnimation(cardItem[i], "x");
				ax.setStartValue(x.get(i).get(j - 1));
				ax.setEndValue(x.get(i).get(j));
				ax.setDuration(15 * delay + MainActivity.prng.nextInt(5 * delay));
				ax.setEasingCurve(EasingCurve.InOutSine);
				xs.addAnimation(ax);

				PropertyAnimation ay = new PropertyAnimation(cardItem[i], "y");
				ay.setStartValue(y.get(i).get(j - 1));
				ay.setEndValue(y.get(i).get(j));
				ay.setDuration(15 * delay + MainActivity.prng.nextInt(5 * delay));
				ay.setEasingCurve(EasingCurve.InOutSine);
				ys.addAnimation(ay);
			}

			PropertyAnimation ax = new PropertyAnimation(cardItem[i], "x");
			ax.setStartValue(x.get(i).get(n - 1));
			ax.setEndValue(poziciaBase.x());
			ax.setDuration(15 * delay + MainActivity.prng.nextInt(5 * delay));
			ax.setEasingCurve(EasingCurve.InOutSine);
			xs.addAnimation(ax);

			PropertyAnimation ay = new PropertyAnimation(cardItem[i], "y");
			ay.setStartValue(y.get(i).get(n - 1));
			ay.setEndValue(poziciaBase.y());
			ay.setDuration(15 * delay + MainActivity.prng.nextInt(5 * delay));
			ay.setEasingCurve(EasingCurve.InOutSine);
			ys.addAnimation(ay);

			xy.addAnimation(xs);
			xy.addAnimation(ys);
		}

		signalMapper.setMapping(xy, game.stav.kolo);

		// TODO Qt specific code.
		// QObject.connect(xy,SIGNAL(finished()),signalMapper,SLOT(map()));

		xy.start(AbstractAnimation.DeleteWhenStopped);
	}

	/**
	 * 
	 * @param cid
	 * @param pid
	 * @param offset
	 * @param pause
	 * @param last
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _rozdaj(int cid, int pid, int offset, int pause, boolean last) {
		cardItem[cid].setZValue(offset);

		SequentialAnimationGroup rozd = new SequentialAnimationGroup();

		PropertyAnimation anim = new PropertyAnimation(cardItem[cid], "pos");

		// TODO Plus overload is not possible in Java.
		// anim.setEndValue(poziciaKariet[pid]+Point(offset*(pid==0?30:20),0));

		anim.setDuration(3 * Integer.valueOf(settings.value("graphics/delay",
				100)));

		if (pause > 0) {
			rozd.addPause(pause
					* Integer.valueOf(settings.value("graphics/delay", 100)));
		}

		rozd.addAnimation(anim);

		if (last == true) {
			signalMapper.setMapping(rozd, game.stav.kolo);

			// TODO Qt secific code.
			// QObject.connect(rozd,SIGNAL(finished()),signalMapper,SLOT(map()));
		}

		rozd.start(AbstractAnimation.DeleteWhenStopped);
	}

	/**
	 * 
	 * @param cid
	 * @param pid
	 * @param offset
	 * @param pause
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _rozdaj(int cid, int pid, int offset, int pause) {
		_rozdaj(cid, pid, offset, pause, false);
	}

	/**
	 * 
	 * @param pid
	 * @param last
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _fixHand(int pid, boolean last) {
		int lastBad = 0;

		for (int i = 0; i < game.players.get(pid).hand.size(); i++) {
			int cid = game.players.get(pid).hand.get(i);
			cardItem[cid].setZValue(i);
			if (cardItem[cid].x() != poziciaKariet[pid].x() + i
					* (pid == 0 ? 30 : 20)) {
				lastBad = i;
			}
		}

		for (int i = 0; i < game.players.get(pid).hand.size(); i++) {
			int cid = game.players.get(pid).hand.get(i);

			if (cardItem[cid].x() != poziciaKariet[pid].x() + i
					* (pid == 0 ? 30 : 20)) {
				PropertyAnimation anim = new PropertyAnimation(cardItem[cid],
						"x");

				anim.setEndValue(poziciaKariet[pid].x() + i
						* (pid == 0 ? 30 : 20));

				anim.setDuration(3 * Integer.valueOf(settings.value(
						"graphics/delay", 100)));

				if (last && i == lastBad) {
					signalMapper.setMapping(anim, game.stav.kolo);

					// TODO Qt specific code.
					// QObject.connect(anim,SIGNAL(finished()),signalMapper,SLOT(map()));
				}

				anim.start(AbstractAnimation.DeleteWhenStopped);
			}
		}
	}

	/**
	 * 
	 * @param cid
	 * @param last
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _talon(int cid, boolean last) {
		cardItem[cid].setPixmap(rub);

		PropertyAnimation anim = new PropertyAnimation(cardItem[cid], "pos");

		anim.setEndValue(poziciaBase);

		anim.setDuration(3 * Integer.valueOf(settings.value("graphics/delay",
				100)));

		if (last == true) {
			signalMapper.setMapping(anim, game.stav.kolo);

			// TODO Qt specific code.
			// QObject.connect(anim,SIGNAL(finished()),signalMapper,SLOT(map()));
		}

		anim.start(AbstractAnimation.DeleteWhenStopped);
	}

	/**
	 * 
	 * @param cid
	 * @param reverse
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _ejectTromf(int cid, boolean reverse) {
		int vysunutie = ((game.stav.forhont == 0) ^ reverse) ? -120 : 120;

		cardItem[cid].setY(cardItem[cid].y() + vysunutie);

		if (game.stav.forhont != 0) {
			hideCard(cid);
		}
	}

	/**
	 * 
	 * @param cid
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _revealCard(int cid) {
		cardItem[cid].setPixmap(images[cid]);
	}

	/**
	 * 
	 * @param cid
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _hideCard(int cid) {
		cardItem[cid].setPixmap(rub);
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _draw() {
		LOGGER.info("start draw");
		cleanGraphics(false);

		/*
		 * DRAW FORHONT LIGHT
		 */

		if (settings.value("graphics/light_type", "simple") == "gradient") {
			graphicsScene.addItem(lightGradient[game.stav.forhont]);
		} else if (settings.value("graphics/light_type", "simple") == "simple") {
			graphicsScene.addItem(lightSimple[game.stav.forhont]);
		}

		/*
		 * DRAW LABELS
		 */

		font.setBold(false);
		font.setFamily("Monospace");
		font.setPointSize(12);

		for (int i = 0; i < 3; i++) {
			GraphicsTextItem text = new GraphicsTextItem();

			text.setHtml(game.players.get(i).name
					+ ": <span style=\"font-size: xx-large;\">"
					+ (game.players.get(i).peniaze) + (" xxx</span>"));

			text.setPos(poziciaMena[i]);

			text.setFont(font);

			if (game.stav.forhont == i) {
				text.setDefaultTextColor(Color.WHITE);
			} else {
				text.setDefaultTextColor(Color.YELLOW);
			}

			graphicsScene.addItem(text);
		}

		/*
		 * DRAW CONSOLE
		 */

		font.setBold(false);

		font.setPointSize(9);

		int i = 0;
		String o = "";
		for (String s : out) {
			if (i == 0) {
				font.setPointSize(11);
				font.setBold(true);
			} else {
				font.setPointSize(9);
				font.setBold(false);
			}

			GraphicsTextItem output = new GraphicsTextItem("> " + s);

			output.setPos(10, 350 - i);
			output.setFont(font);
			output.setDefaultTextColor(Color.YELLOW);

			graphicsScene.addItem(output);

			i += 6 + font.pointSize();
		}

		/*
		 * DRAW TROMF SUIT
		 */

		if (game.stav.forhont > -1 && game.stav.hra.tromf > -1) {
			GraphicsPixmapItem pi = new GraphicsPixmapItem();

			pi.setPixmap(suits[game.stav.hra.tromf / 8]);
			pi.setPos(poziciaTromfu[game.stav.forhont]);
			pi.setZValue(1000);

			graphicsView.scene().addItem(pi);
		}
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _drawResults() {
		LOGGER.info("start draw results");
		cleanGraphics(true);
		draw();

		Font font = new Font();
		font.setBold(false);
		font.setFamily("Monospace");
		font.setPointSize(12);

		GraphicsTextItem text = new GraphicsTextItem();
		String html = "<table cellpadding=\"2\" cellspacing=\"0\" border=\"1\"><tr><td align=\"center\" colspan=\"2\">"
				+ game.stav.hra.forhontPoints
				+ ":"
				+ game.stav.hra.oppPoints
				+ "</td></tr>";

		for (eu.veldsoft.marias.ResRow row : game.stav.res) {
			html += "<tr" + (row.third() ? " style=\"font-weight:bold;\"" : "")
					+ "><td>" + row.first() + "</td><td align=\"right\">"
					+ row.second() + "</td></tr>";
			if (row.third() == true) {
				html += "<tr><td></td><td></td></tr>";
			}
		}

		html += "</table><a href=\"#\" style=\"color:white;\">" + "New game"
				+ "</a>";

		text.setHtml(html);
		text.setPos(new Point(270, 220));
		text.setFont(font);
		text.setDefaultTextColor(Color.WHITE);
		text.setTextInteractionFlags(Qt.TextBrowserInteraction);
		text.setCursor(Qt.PointingHandCursor);

		// TODO Qt specific code.
		// QObject.connect(text,SIGNAL(linkActivated(String)),game,SLOT(newGame()));

		graphicsScene.addItem(text);
	}

	/**
	 * 
	 * @param cid
	 * @param pid
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _animateCard(int cid, int pid) {
		cardItem[cid].setPixmap(images[cid]);

		ParallelAnimationGroup hodKartu = new ParallelAnimationGroup();

		PropertyAnimation anim = new PropertyAnimation(cardItem[cid], "pos");

		anim.setEndValue(poziciaKopy[pid]);

		anim.setDuration(3 * Integer.valueOf(settings.value("graphics/delay",
				100)));

		hodKartu.addAnimation(anim);

		anim = new PropertyAnimation(cardItem[cid], "z");
		anim.setEndValue(game.stav.kopa.size());
		anim.setDuration(3 * Integer.valueOf(settings.value("graphics/delay",
				100)));

		hodKartu.addAnimation(anim);

		signalMapper.setMapping(hodKartu, game.stav.kolo);

		// TODO Qt specific code.
		// QObject.connect(hodKartu,SIGNAL(finished()),signalMapper,SLOT(map()));

		hodKartu.start(AbstractAnimation.DeleteWhenStopped);
	}

	/**
	 * 
	 * @param pid
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 20 Aug 2013
	 */
	public void _animateStich(int pid) {
		LOGGER.info("animate stich " + pid);

		for (int i = 0; i < 3; i++) {
			fixHand(i);
		}

		ParallelAnimationGroup berStich = new ParallelAnimationGroup();

		for (int i = 0; i < 3; i++) {
			cardItem[game.stav.kopa.get(i)].setZValue(cardItem[game.stav.kopa
					.get(i)].zValue() - 1000 + 100 * game.stav.kolo);

			PropertyAnimation anim = new PropertyAnimation(
					cardItem[game.stav.kopa.get(i)], "pos");

			anim.setEndValue(poziciaStichov[pid]);

			anim.setDuration(3 * Integer.valueOf(settings.value(
					"graphics/delay", 100)));

			berStich.addAnimation(anim);

			anim = new PropertyAnimation(cardItem[game.stav.kopa.get(i)],
					"opacity");

			anim.setEndValue(0);

			anim.setDuration(3 * Integer.valueOf(settings.value(
					"graphics/delay", 100)));

			berStich.addAnimation(anim);
		}

		SequentialAnimationGroup sag = new SequentialAnimationGroup();

		sag.addPause(7 * Integer.valueOf(settings.value("graphics/delay", 100)));
		sag.addAnimation(berStich);

		signalMapper.setMapping(sag, game.stav.kolo);

		// TODO Qt specific code.
		// QObject.connect(sag,SIGNAL(finished()),signalMapper,SLOT(map()));

		sag.start(AbstractAnimation.DeleteWhenStopped);
	}

	/**
	 * ...
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void _show() {
		graphicsView.show();
	}

	/**
	 * Game instance getter.
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public Game _getGame() {
		return (game);
	}

	/**
	 * ...
	 * 
	 * @param s
	 * @param pos
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void _print(String s, int pos) {
		/*
		 * nice animation
		 */
		int textWidth = 750;

		font.setBold(false);
		font.setFamily("Monospace");
		font.setPointSize(50);

		SuperText sText = new SuperText();
		sText.setHtml("<p align=\"center\">" + s + "</p>");
		sText.setTextWidth(textWidth);
		sText.setData(0, "anim");
		if (pos > 2 || pos < 0)
			pos = 0;
		// TODO Need to be implemented in more complex way.
		// sText.setPos(poziciaTextu[pos]-Point(textWidth/2,0));
		sText.setFont(font);
		sText.setDefaultTextColor(Color.YELLOW);
		graphicsScene.addItem(sText);

		PropertyAnimation superTextAnim = new PropertyAnimation(sText,
				"opacity");
		superTextAnim.setDuration(10 * Integer.valueOf(settings.value(
				"graphics/delay", 100)));
		superTextAnim.setEndValue(0);
		superTextAnim.setEasingCurve(EasingCurve.InQuad);
		superTextAnim.start(AbstractAnimation.DeleteWhenStopped);

		_log(s);
		_draw();
	}

	/**
	 * ...
	 * 
	 * @param s
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 16 Aug 2013
	 */
	public void _log(String s) {
		if (out.size() > 5) {
			out.remove(out.size() - 1);
		}
		out.add(0, s);
	}
}
