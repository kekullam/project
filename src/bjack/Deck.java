package bjack;

/**
 * Created by Kerdo Kullamäe on 5.11.2015.
 */
public class Deck {
    private Card[] deck;
    private int cardsUsed;

    // Teeb uue täis kaardipaki
    public Deck() {
        deck = new Card[52];
        int count = 0;
        for (int suit=0;suit<=3;suit++) {
            for (int value=1;value<=13;value++) {
                deck[count] = new Card(suit, value);
                count++;
            }
            cardsUsed = 0;
        }
    }
    // Paneb kõik kasutusel olevad kaardid pakki ning segab paki ära
    public void shuffle() {
        for ( int i = deck.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        cardsUsed = 0;
    }

    public Card dealCard() {
        cardsUsed++;
        return deck[cardsUsed-1];
    }
}
