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
    // Tagastab kaardi v‰‰rtuse sınena
    public String getValueAsString() {
        switch(value) {
            case 1 : return "Ace";
            case 2 : return "2";
            case 3 : return "3";
            case 4 : return "4";
            case 5 : return "5";
            case 6 : return "6";
            case 7 : return "7";
            case 8 : return "8";
            case 9 : return "9";
            case 10 : return "10";
            case 11 : return "Jack";
            case 12 : return "Queen";
            default : return "King";
        }
    }
    // Tagastab kaardi masti sınena
    public String getSuitAsString() {
        switch(suit) {
            case SPADES : return "Spades";
            case CLUBS : return "Clubs";
            case HEARTS : return "Hearts";
            default : return "Diamonds";
        }
    }
    // Tagastab kaardi sınena
    public String toString() {
        return getValueAsString() + " of " + getSuitAsString();
    }

}

