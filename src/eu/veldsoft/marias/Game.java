package eu.veldsoft.marias;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Most important class that handles events, manages players, cards and whole
 * gameplay with results. Main functions are *Clicked() and animationFinished().
 * 
 * TODO: split this class into more smaller with exact functionality.
 * 
 * @author Miso Kovac
 * @email kovac@fmph.uniba.sk
 * @data 14 Aug 2013
 */
class Game {
    /**
     * Logger for debug.
     */
    private final static Logger LOGGER = Logger.getLogger(Game.class.getName());
    static {
        LOGGER.setLevel(Level.INFO);
    }

	// TODO Use shared preferences.
	// public QSettings settings;

    /**
     * Players container.
     */
	public List<Player> players;

    /**
     * Deck of cards.
     */
	public List<Integer> deck;

    /**
     * Current state of the game.
     */
	public Stav stav;

    /**
     * If is now the human turn flag.
     */
	public boolean waitingForClick;

    /**
     *
     */
	public BiddingDialog bd;

    /**
     * Marias game logic.
     */
	public Marias marias;

    /**
     * Quick game flag.
     */
	public boolean quickGame;

    /**
     * Profiler instance.
     */
	public Profiler profiler;

    /**
     * Pseudo-random number generator.
     */
	public Random rg;

    /**
     * for poeple test
     */
	public int stats[] = new int[3];

    /**
     * Constructor.
     *
     * @param m Marias object reference.
     *
     * @author Todor Balabanov
     * @email tdb@tbsoft.eu
     * @date 14 Aug 2013
     */
	public Game(Marias m) {
        //TODO Deep copy is bettter.
        marias = m;

        //TODO Should be implemented because it is different than Qt implementation.
        //settings = new Settings("marias.ini", QSettings::IniFormat);

        bd = new BiddingDialog(this);

        profiler = new Profiler();

        for(int i=0;i<stats.length;i++) {
            stats[i] = 0;
        }
	}

    /**
     * Initialize game.
     *
     *
     * @author Todor Balabanov
     * @email tdb@tbsoft.eu
     * @date 14 Aug 2013
     */
	public void init() {
        LOGGER.info( "game init" );
        quickGame = false;

//TODO Settings should be available in order this fragmetn to work.
//        if(settings.value("shuffling/random",1)==1){
//            LOGGER.info("random");
//            rg.setSeed();
//        }else{
//            LOGGER.info("not random");
//            rg.setSeed(settings.value("shuffling/seed",47).toInt());
//        }
//
//        shuffleDeck();
//
//        players.clear();
//        players.add(PlayerFactory::create(settings.value("players/front","HumanPlayer").toString()));
//        players.add(PlayerFactory::create(settings.value("players/left","RandomPlayer").toString()));
//        players.add(PlayerFactory::create(settings.value("players/right","RandomPlayer").toString()));
//        players[0].name = settings.value("players/front_name",tr("front")).toString();
//        players[1].name = settings.value("players/left_name",tr("left")).toString();
//        players[2].name = settings.value("players/right_name",tr("right")).toString();

        //TODO Use for-each loop.
        for(int i=0;i<3;i++){
            //TODO May be internal objects are unchainged if there is deep copy.
            players.get(i).setStav(stav);
            players.get(i).setId(i);
            players.get(i).profiler = profiler;
            players.get(i).init();
        }

        resetMoney();

        stav.forhont = 2;
        stav.kolo = -6;
    }

	public void resetMoney() {
		// TODO To be done by Miro ...
	}

	public void results() {
        int p = stav.trick();

        if(quickGame == false){
            //TODO DeskView should be implemented.
            //DeskView.log("Stich berie "+players.get(p).name);
            LOGGER.info("Stich berie " + players.get(p).name);
        }

        for(Integer c : stav.kopa){
            if(Card.value(c).equals("10") || Card.value(c).equals("eso")) {
                players.get(p).body += 10;
            }
        }

        if(stav.kolo==9){
            players.get(p).body += 10;
        }

        stav.id = p;

        /*
         * First kolo increase, then animation.
         */
        stav.kolo++;

        if(quickGame == false) {
            //TODO DeskView should be implemented.
            //DeskView.animateStich(p);
        }

        stav.pHist.add(p);
        stav.vysid = p;
        stav.kopa.clear();

        if(stav.kolo==10){
            profiler.stop("game - trick taking");
            int res = stav.results(true);
            LOGGER.info("stav results: " + res);
            players.get(stav.forhont).peniaze += res*2;
            players.get((stav.forhont+1)%3).peniaze -= res;
            players.get((stav.forhont+2)%3).peniaze -= res;
            if(players.get(0).type=="human" && players.get(1).type=="minimax" && players.get(2).type=="smart"){
                stats[stav.forhont] += res*2;
                stats[(stav.forhont+1)%3] -= res;
                stats[(stav.forhont+2)%3] -= res;
            }
        }

        profiler.stop("trick taking - stich results");
        if(quickGame == true) {
            animationFinished(stav.kolo);
        }
	}

	/**
	 * When clicked on CardItem, it depends on stav.kolo (current gamestate)
	 * whether to execute cardClicked, talonClicked or tromfClicked. cardClicked
	 * is called when a human selects a card he wish to play.
	 * 
	 * @see CardItem
	 * 
	 */
	public void cardClicked(int k) {
		// TODO To be done by ...
	}

	/**
	 * Returns "" if the card is valid. Error message otherwise.
	 * 
	 */
	public String validateTalon(int k) {
		// TODO To be done by ...
		return (null);
	}

	/**
	 * When clicked on CardItem, it depends on stav.kolo (current gamestate)
	 * whether to execute cardClicked, talonClicked or tromfClicked.
	 * talonClicked is called when forhont wants to select card to talon.
	 * 
	 * @see CardItem
	 * 
	 */
	public void talonClicked(int k) {
		// TODO To be done by ...
	}

	/**
	 * When clicked on CardItem, it depends on stav.kolo (current gamestate)
	 * whether to execute cardClicked, talonClicked or tromfClicked.
	 * tromfClicked is called when forhont wants to select tromf.
	 * 
	 * @see CardItem
	 * 
	 */
	public void tromfClicked(int k) {
		// TODO To be done by ...
	}

	/**
	 * Current player wants to play card k, this function executes all
	 * necessities.
	 * 
	 */
	public boolean turn(int k) {
		// TODO To be done by ...
		return (false);
	}

	public void shuffleDeck() {
		// TODO To be done by ...
	}

	/**
	 * First round of card dealing. Forhont gets 7 cards, other 5.
	 * 
	 */
	public void rozdaj1() {
		// TODO To be done by ...
	}

	/**
	 * Second round of card dealing. All players get 5 cards.
	 * 
	 */
	public void rozdaj2() {
		// TODO To be done by ...
	}

	/**
	 * Switches player type for player at position i.
	 * 
	 */
	public void changePlayer(int i, String newType) {
		// TODO To be done by ...
	}

	public void newGame() {
		// TODO To be done by ...
	}

	/**
	 * Major function, is responsible for future actions, that depends on game
	 * state. This is due to event driven programming, where main loop is hidden
	 * from programmers. This function must exist, maybe it can split some
	 * functionality in more functions.
	 * 
	 */
	public void animationFinished(int round) {
		// TODO To be done by ...
	}
}
