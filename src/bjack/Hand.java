package bjack;

import java.util.ArrayList;

/**
 * Created by Kerdo Kullamäe on 5.11.2015.
 */

/**
 * Käe objekt, mis kujutab endast käes olevaid kaarte. Kaardid kuuluvad Cards klassi.
 */
public class Hand {

    private ArrayList<Card> hand;

    /**
     * Konstruktor, mis koostab tühja käe.
     */
    public Hand() {

        hand = new ArrayList<Card>();
    }

    /**
     * Meetod, mis lisab kätte ühe uue kaardi.
     * @param a kaart, mis lisatakse.
     */
    public void addCard(Card a) {
        hand.add(a);
      }

    /**
     * Meetod, mis tagastab kaartide arvu käes.
     * @return kaartide arv käes.
     */
    public int getCardCount() {
        return hand.size();
    }

    /**
     * Meetod, mis tagastab valitud asukohal oleva kaardi käest.
     * @param pos kaardi positsioon käes.
     * @return valitud asukohal olev kaart.
     */
    public Card getCard(int pos) {
        return hand.get(pos);
    }


}


