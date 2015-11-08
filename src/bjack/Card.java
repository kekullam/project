package bjack;

/**
 * Created by Kerdo Kullam‰e on 5.11.2015.
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

    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    // Tagastab kaardi masti
    public int getSuit() {
        return suit;
    }

    // Tagastab kaardi v‰‰rtuse
    public int getValue() {
        return value;
    }


}