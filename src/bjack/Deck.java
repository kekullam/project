package bjack;

/**
 * Created by Kerdo Kullamäe on 5.11.2015.
 */

import java.util.Random;

/**
 * Kaardipaki klass, tavaline 52 kaardiga pakk.
 */

// http://math.hws.edu/javanotes/source/chapter5/Deck.java
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
     * Meetod, mis segab kaardipaki ära.
     */
    public void shuffle() {
        Random randomGene = new Random();

        for (int i = 0; i<deck.length;i++) {
            int rand = randomGene.nextInt(deck.length);
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
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
