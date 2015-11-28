package bjack;

import java.util.ArrayList;

/**
 * Created by Kerdo Kullam�e on 5.11.2015.
 */

/**
 * K�e objekt, mis kujutab endast k�es olevaid kaarte. Kaardid kuuluvad Cards klassi.
 */
public class Hand {

    private ArrayList<Card> hand;

    /**
     * Konstruktor, mis koostab t�hja k�e.
     */
    public Hand() {

        hand = new ArrayList<Card>();
    }

    /**
     * Meetod, mis lisab k�tte �he uue kaardi.
     * @param a kaart, mis lisatakse.
     */
    public void addCard(Card a) {
        hand.add(a);
      }

    /**
     * Meetod, mis tagastab kaartide arvu k�es.
     * @return kaartide arv k�es.
     */
    public int getCardCount() {
        return hand.size();
    }

    /**
     * Meetod, mis tagastab valitud asukohal oleva kaardi k�est.
     * @param pos kaardi positsioon k�es.
     * @return valitud asukohal olev kaart.
     */
    public Card getCard(int pos) {
        return hand.get(pos);
    }


}


