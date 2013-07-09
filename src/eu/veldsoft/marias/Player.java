package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Player is class that is partially abstract. Extending classes implement it's methods. It also stores player data.
 *
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 06 Jul 2013
 */
class Player {
    /**
     * Logger for debug.
     */
    private final static Logger LOGGER = Logger.getLogger(Player.class.getName());

    static {
        LOGGER.setLevel(Level.INFO);
    }

    /**
     * Pseudo random number generator.
     */
    public static Random prng = new Random();

    /**
     * Player name.
     */
    public String name;

    /**
     * Player type.
     */
    public String type;

    /**
     * Player hand.
     */
    public List<Integer> hand = new ArrayList<Integer>();

    /**
     * Player points.
     */
    public int body;

    /**
     * Player money.
     */
    public int peniaze;

    /**
     * Pocet hlasok.
     */
    public int hlasky;

    /**
     * ...
     */
    public Stav stav;

    /**
     * ...
     */
    public int id;

    /**
     * ...
     */
    public String message;

    /**
     * ...
     */
    public boolean quickGame;

    /**
     * Profiler reference.
     */
    public Profiler profiler;

    /**
     * If the player is forhont, he should know what is in talon.
     */
    public int talonCards[] = new int[2];

    /**
     * Constructor without parameters.
     *
     * @author Todor Balabanov
     * @email tdb@tbsoft.eu
     * @date 06 Jul 2013
     */
    public Player() {
        type = "default";
        body = 0;
        peniaze = 0;
    }

    /**
     * Initialize is implemented in the children.
     *
     * @author Todor Balabanov
     * @email tdb@tbsoft.eu
     * @date 06 Jul 2013
     */
    public void init() {
    }

    /**
     * Stav setter.
     *
     * @param s Stav value.
     * @author Todor Balabanov
     * @email tdb@tbsoft.eu
     * @date 06 Jul 2013
     */
    public void setStav(Stav s) {
        //TODO Do defensive copy.
        stav = s;
    }

    /**
     * Player id setter.
     *
     * @param pid Player id.
     * @author Todor Balabanov
     * @email tdb@tbsoft.eu
     * @date 06 Jul 2013
     */
    public void setId(int pid) {
        id = pid;
    }

    /**
     * ...
     *
     * @return int Id of the played card.
     * @author Todor Balabanov
     * @email tdb@tbsoft.eu
     * @date 06 Jul 2013
     */
    public int play() {
        LOGGER.info("default");

        /*
         * Method should be implemented as abstract method.
         */
        return (-1);
    }

    /**
     * @param stav State of the game.
     * @return int Id of two cards for talon (32*id_1 + id_2).
     */
    public int talon() {
        //TODO To be done by Miro.
        return (0);
    }

    /**
     * @return int Id of the tromf card.
     */
    public int tromf() {
        //TODO To be done by Miro.
        return (0);
    }

    /**
     * @param stav State of the game.
     * @return int Bid (3 bits in one number less than 8) First bit is whether
     *         the player bids the game, second is bid for the seven and third
     *         is bid for the hundred.
     */
    public int bid() {
        //TODO To be done by Miro.
        return (0);
    }

    /**
     * Removes card from hand.
     *
     * @param cid Car id.
     * @author Todor Balabanov
     * @email tdb@tbsoft.eu
     * @date 06 Jul 2013
     */
    public void removeCard(int cid) {
        //TODO hand.remove( new Integer(cid) );
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).intValue() == cid) {
                hand.remove((int) i);
                return;
            }
        }

        LOGGER.info("Player::removeCard(): " + name + " dont have " + cid);
    }

    /**
     * ...
     *
     * @param s
     * @author Todor Balabanov
     * @email tdb@tbsoft.eu
     * @date 06 Jul 2013
     */
    public void say(String s) {
        LOGGER.info("(" + type + ") " + name + ": " + s);
        message += " " + s;
    }

    /**
     * Utility method, used by validation, it must check, if the player is able
     * to beat given card (to beat = prebit).
     *
     * @see validate
     */
    public boolean mozePrebit(int cid, Stav s, List<Integer> h) {
        return (false);
    }

    public boolean mozePrebit(int cid) {
        return (mozePrebit(cid, null, null));
    }

    /**
     * Utility method, used by validation, it must check, if the player is able
     * to beat given card (to beat = prebit) with card of same color(suit).
     *
     * @see validate
     */
    public boolean mozePrebitCisto(int cid, Stav s, List<Integer> h) {
        return (false);
    }

    public boolean mozePrebitCisto(int cid) {
        return (mozePrebitCisto(cid, null, null));
    }

    /**
     * Utility method, used by validation, it checks, if the player can play a
     * card with same color.
     *
     * @see validate
     */
    public boolean maFarbu(int cid, List<Integer> h) {
        return (false);
    }

    public boolean maFarbu(int cid) {
        return (maFarbu(cid));
    }

    public boolean somForhont() {
        return (false);
    }

    public int tromfCount(Stav s, List<Integer> h) {
        return (0);
    }

    public int tromfCount() {
        return (tromfCount(null, null));
    }

    /**
     * Checks, if the card is valid (can be played) in current game state. Or in
     * the given state with given hand.
     */
    public String validate(int cid, Stav s, List<Integer> h) {
        return (null);
    }

    public String validate(int cid) {
        return (validate(cid, null, null));
    }

    /**
     * Returns list of cards that are allowed to play. Optional in the given
     * state and with given hand.
     */
    public List<Integer> getLegalList(Stav s, List<Integer> h) {
        return (null);
    }

    public List<Integer> getLegalList() {
        return (getLegalList(null, null));
    }

    public void sortHand() {
    }
}
