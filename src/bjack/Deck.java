package bjack;

/**
 * Created by Kerdo Kullamäe on 5.11.2015.
 */

/**
 * Kaardipaki objekt, tavaline 52 kaardiga pakk.
 */
public class Deck {
    /**
     * Kaartide massiiv.
     */
    private Card[] deck;

    /**
     * Loendab kui mitu kaarti on pakist ära jagatud/eemaldatud.
     */
    private int cardsUsed;

    /**
     * Konstruktor, mis koostab 52 kaardiga kaardipaki.
     * Algselt on kaardid sorteeritud.
     */
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

    /**
     * Meetod, mis paneb kõik kaardid kaardipakki ning segab kaardipaki ära.
     */
    public void shuffle() {
        for ( int i = deck.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card atm = deck[i];
            deck[i] = deck[rand];
            deck[rand] = atm;
        }
        cardsUsed = 0;
    }

    /**
     * Võtab kaardi pakist kaardi ning tagastab selle.
     * @return kaart, mis on eemaldatud pakist.
     */
    public Card dealCard() {
        cardsUsed++;
        return deck[cardsUsed-1];
    }
}
