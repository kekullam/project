package bjack;

/**
 * Created by Kerdo Kullam‰e on 5.11.2015.
 */

/**
 * Kaardi klass, millesse kuuluvad neli erinevat masti ja nelja mitte numbrilise kaardi v‰‰rtust.
 */
public class Card {
    public static final int SPADES = 0;
    public static final int CLUBS = 1;
    public static final int HEARTS = 2;
    public static final int DIAMONDS = 3;

    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;

    private final int suit;
    private final int value;

    /**
     * Konstruktor, mis moodustab kaardi antud masti ja v‰‰rtusega.
     * @param suit uue kaardi mast.
     * @param value uue kaardi v‰‰rtus.
     */
    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Meetod, mis tagastab kaardi masti.
     * @return mast(SPADES, HEARTS, CLUBS or DIAMONDS).
     */
    public int getSuit() {
        return suit;
    }

    /**
     * Meetod, mis tagastab kaardi v‰‰rtuse.
     * @return v‰‰rtus(1-13).
     */
    public int getValue() {
        return value;
    }


}