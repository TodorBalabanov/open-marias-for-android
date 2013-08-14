package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.List;

public class DeskView {

	// TODO It is only for project to compile.
	private class Ui_Marias {
	}

	// TODO It is only for project to compile.
	private class GraphicsScene {
	}

	// TODO It is only for project to compile.
	private class GraphicsView {
	}

	// TODO It is only for project to compile.
	private class SignalMapper {
	}

	// TODO It is only for project to compile.
	private class Pixmap {
	}

	// TODO It is only for project to compile.
	private class Point {
	}

	// TODO It is only for project to compile.
	private class GraphicsEllipseItem {
	}

	// TODO It is only for project to compile.
	private class Settings {
	}

	// TODO It is only for project to compile.
	private class Font {
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

	public static DeskView Instance() {
		// TODO To be done by ...
		return (null);
	}

	/**
	 * Instance should be created only once. Now it is created by Marias class.
	 * It takes two arguments - ui of the marias for access to graphics view and
	 * game for access to state of the game and players
	 * 
	 */
	public static void createInstance(Ui_Marias ui, Game g) {
		// TODO To be done by ...
	}

	/**
	 * Instance is created by Marias, so it is also destroyed only by Marias.
	 * 
	 */
	public static void destroyInstance() {
		// TODO To be done by ...
	}

	/**
	 * Cleans graphics scene of graphics items, so they won't be stored in
	 * memory anymore. By default, cards and animations are not removed, because
	 * cards are not drawed, only moved, so they stay. Animations are removed
	 * after they are finished.
	 * 
	 */
	public static void cleanGraphics(boolean all) {
		// TODO To be done by ...
	}

	public static void cleanGraphics() {
		cleanGraphics(false);
	}

	/**
	 * Gathers all cards from desk, cleans all animations if some remains and
	 * recreates the cards at deck.
	 * 
	 */
	public static void gather() {
		// TODO To be done by ...
	}

	public static void intro() {
		// TODO To be done by ...
	}

	/**
	 * Moves card with id cid to position so it appears at the hand of player
	 * with id pid at position offset. It is used during card dealing and in
	 * case when a players plays a card and a gap arises in the hand - the cards
	 * are shifted then. Note: offset is zero-based.
	 * 
	 */
	public static void rozdaj(int cid, int pid, int offset, int pause,
			boolean last) {
		// TODO To be done by ...
	}

	public static void rozdaj(int cid, int pid, int offset, int pause) {
		rozdaj(cid, pid, offset, pause, false);
		// TODO To be done by ...
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
	 */
	public static void talon(int cid, boolean last) {
		// TODO To be done by ...
	}

	public static void talon(int cid) {
		talon(cid, false);
	}

	/**
	 * Moves cards in player's hand to their correct positions.
	 * 
	 */
	public static void fixHand(int pid, boolean last) {
	}

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
	 */
	public static void ejectTromf(int cid, boolean reverse) {
		// TODO To be done by ...
	}

	public static void ejectTromf(int cid) {
		// TODO To be done by ...
	}

	/**
	 * Sets the card image. If the former was back of the card, now it will be
	 * front.
	 * 
	 */
	public static void revealCard(int cid) {
		// TODO To be done by ...
	}

	/**
	 * Sets the card image to back. The value of the card will therefore be
	 * hidden.
	 * 
	 */
	public static void hideCard(int cid) {
		// TODO To be done by ...
	}

	/**
	 * Draws light at forhont, name labels with money, console with game
	 * messages and tromf suit.
	 * 
	 */
	public static void draw() {
		// TODO To be done by ...
	}

	/**
	 * Cleans all the graphics and displays results. They are stored in
	 * game->stav.hra.results
	 * 
	 */
	public static void drawResults() {
		// TODO To be done by ...
	}

	/**
	 * Animate card cid from player pid to the stack (poziciaKopy[pid])
	 * 
	 */
	public static void animateCard(int cid, int pid) {
		// TODO To be done by ...
	}

	/**
	 * Animate stich(trick) - all 3 cards at the stack(kopa) are transferred to
	 * player pid.
	 * 
	 */
	public static void animateStich(int pid) {
		// TODO To be done by ...
	}

	/**
	 * deprecated
	 */
	public static void show() {
		// TODO To be done by ...
	}

	/**
	 * Dirty hook how anyone can easily get pointer to Game (Game have access to
	 * any class).
	 * 
	 */
	public static Game getGame() {
		// TODO To be done by ...
		return (null);
	}

	/**
	 * Prints animated text s that says player with id pos. Also logs it.
	 * 
	 */
	public static void print(String s, int pos) {
		// TODO To be done by ...
	}

	public static void print(String s) {
		print(s, 0);
	}

	/**
	 * Adds given string to console output.
	 * 
	 */
	public static void log(String s) {
		// TODO To be done by ...
	}

	public DeskView(Ui_Marias ui, Game g) {
		// TODO To be done by ...
	}

	public void _cleanGraphics(boolean all) {
		// TODO To be done by ...
	}

	public void _cleanGraphics() {
		_cleanGraphics(false);
	}

	public void _gather() {
		// TODO To be done by ...
	}

	public void _intro() {
		// TODO To be done by ...
	}

	public void _rozdaj(int cid, int pid, int offset, int pause, boolean last) {
		// TODO To be done by ...
	}

	public void _rozdaj(int cid, int pid, int offset, int pause) {
		_rozdaj(cid, pid, offset, pause, false);
	}

	public void _fixHand(int pid, boolean last) {
		// TODO To be done by ...
	}

	public void _talon(int cid, boolean last) {
		// TODO To be done by ...
	}

	public void _ejectTromf(int cid, boolean reverse) {
		// TODO To be done by ...
	}

	public void _revealCard(int cid) {
		// TODO To be done by ...
	}

	public void _hideCard(int cid) {
		// TODO To be done by ...
	}

	public void _draw() {
		// TODO To be done by ...
	}

	public void _drawResults() {
		// TODO To be done by ...
	}

	public void _animateCard(int cid, int pid) {
		// TODO To be done by ...
	}

	public void _animateStich(int pid) {
		// TODO To be done by ...
	}

	public void _show() {
		// TODO To be done by ...
	}

	public Game _getGame() {
		// TODO To be done by ...
		return (null);
	}

	public void _print(String s, int pos) {
		// TODO To be done by ...
	}

	public void _log(String s) {
		// TODO To be done by ...
	}
}
