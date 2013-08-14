package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.List;

public class DeskView {

	/**
	 * instance
	 */
	private static DeskView instance;

	/**
		 * 
		 */
	// private QGraphicsScene graphicsScene;

	/**
		 * 
		 */
	// private QGraphicsView graphicsView;

	/**
		 * 
		 */
	// private QSignalMapper signalMapper;

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
	// private QPixmap rub;

	/**
	 * Images of cards.
	 */
	// private QPixmap images[32];

	/**
	 * Tromf icons images.
	 */
	// private QPixmap suits[4];

	/**
	 * Console output.
	 */
	private List<String> out = new ArrayList<String>();

	/**
	 * positions in the graphics view for each of the 3 players: stack
	 */
	// private QPoint poziciaKopy[3];

	/**
	 * cards (upper left corner of the leftmost card)
	 */
	// private QPoint poziciaKariet[3];

	/**
	 * tromf icon
	 */
	// private QPoint poziciaTromfu[3];

	/**
	 * name label
	 */
	// private QPoint poziciaMena[3];

	/**
	 * where stich(trick) goes when a players takes it
	 */
	// private QPoint poziciaStichov[3];

	/**
	 * where supertext is animated
	 */
	// private QPoint poziciaTextu[3];

	/**
	 * deck and then talon
	 */
	// private QPoint poziciaBase;

	/**
	 * graphics items of forhont light
	 */
	// private QGraphicsEllipseItem* lightSimple[3];

	/**
		 * 
		 */
	// private QGraphicsEllipseItem* lightGradient[3];

	/**
		 * 
		 */
	// private QSettings* settings;

	/**
		 * 
		 */
	// private QFont font;

	public static DeskView Instance() {
		// TODO To be done by ...
		return (null);
	}

	// TODO It is only for project to compile.
	private class Ui_Marias {
	}

	/**
	 * Instance should be created only once. Now it is created by Marias class.
	 * It takes two arguments - ui of the marias for access to graphics view and
	 * game for access to state of the game and players
	 * 
	 */
	public static void createInstance(Ui_Marias ui, Game g) {
	}

	/**
	 * Instance is created by Marias, so it is also destroyed only by Marias.
	 * 
	 */
	public static void destroyInstance() {
	}

	// /**
	// *Cleans graphics scene of graphics items, so they won't be stored in
	// memory anymore.
	// *By default, cards and animations are not removed, because cards are not
	// drawed, only moved, so they stay.
	// *Animations are removed after they are finished.
	// *
	// */
	// public static void cleanGraphics(bool all=false);
	//
	// /**
	// *Gathers all cards from desk, cleans all animations if some remains and
	// recreates the cards at deck.
	// *
	// */
	// public static void gather();
	//
	// public static void intro();
	//
	// /**
	// *Moves card with id cid to position so it appears at the hand of player
	// with id pid at position offset.
	// *It is used during card dealing and in case when a players plays a card
	// and a gap arises in the hand - the cards are shifted then.
	// *Note: offset is zero-based.
	// *
	// */
	// public static void rozdaj(int cid, int pid,int offset,int pause,bool
	// last=false);
	//
	// /**
	// *Talon is a special keyword of this game. Google translated it as
	// "kitty", but it evokes me some kind of cat:)
	// *The forhont chooses 2 cards that other players don't see and he is not
	// playing with that cards. After this, all players has exactly 10 cards.
	// *Talon consists of that 2 cards and is located at the position of the
	// former card pile.
	// *cid is id of the card, forhont wants to give away.
	// *If the flag last is on, it means, the last card has been given to talon
	// and the finish of the animation is connected to next action.
	// *
	// */
	// public static void talon(int cid,bool last=false);
	//
	// /**
	// *Moves cards in player's hand to their correct positions.
	// *
	// */
	// public static void fixHand(int pid,bool last=false);
	//
	// /**
	// *Tromf is also a special keyword, it is translated as "trump". It is a
	// "chosen card" - chosen by forhont.
	// *The color(or suit) of the tromf will be stronger than other colors. At
	// the start of the bidding, it is revealed to other players.
	// *Tromf is then displayed in front of forhont, so players can remember the
	// chosen color.
	// *This function is only called after tromf has been chosen, so it only
	// ejects the card softly to be visible.
	// *
	// */
	// public static void ejectTromf(int cid,bool reverse=false);
	//
	// /**
	// *Sets the card image. If the former was back of the card, now it will be
	// front.
	// *
	// */
	// public static void revealCard(int cid);
	//
	// /**
	// *Sets the card image to back. The value of the card will therefore be
	// hidden.
	// *
	// */
	// public static void hideCard(int cid);
	//
	// /**
	// *Draws light at forhont, name labels with money, console with game
	// messages and tromf suit.
	// *
	// */
	// public static void draw();
	//
	// /**
	// *Cleans all the graphics and displays results. They are stored in
	// game->stav.hra.results
	// *
	// */
	// public static void drawResults();
	//
	// /**
	// *Animate card cid from player pid to the stack (poziciaKopy[pid])
	// *
	// */
	// public static void animateCard(int cid,int pid);

	/**
	 * Animate stich(trick) - all 3 cards at the stack(kopa) are transferred to
	 * player pid.
	 * 
	 */
	public static void animateStich(int pid) {

	}

	// //deprecated
	// public static void show();
	//
	// /**
	// *Dirty hook how anyone can easily get pointer to Game (Game have access
	// to any class).
	// *
	// */
	// public static Game* getGame();
	//
	// /**
	// *Prints animated text s that says player with id pos. Also logs it.
	// *
	// */
	// public static void print(QString s, int pos=0);

	/**
	 * Adds given string to console output.
	 * 
	 */
	public static void log(String s) {

	}

	// public DeskView(Ui_Marias* ui,Game* g);
	//
	// public void _cleanGraphics(bool all=false);
	//
	// public void _gather();
	//
	// public void _intro();
	//
	// public void _rozdaj(int cid, int pid,int offset,int pause,bool
	// last=false);
	//
	// public void _fixHand(int pid,bool last);
	//
	// public void _talon(int cid,bool last);
	//
	// public void _ejectTromf(int cid,bool reverse);
	//
	// public void _revealCard(int cid);
	//
	// public void _hideCard(int cid);
	//
	// public void _draw();
	//
	// public void _drawResults();
	//
	// public void _animateCard(int cid,int pid);

	public void _animateStich(int pid) {

	}

	// public void _show();
	//
	// public Game* _getGame();
	//
	// public void _print(QString s, int pos);
	//
	// public void _log(QString s);
}
