package bjack;

import java.util.ArrayList;

/**
 * Created by Kerdo Kullamäe on 5.11.2015.
 */
public class Hand {

    private ArrayList<Card> hand;


    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void clear() {
        hand.clear();
    }

    public void addCard(Card a) {
        hand.add(a);
    }

    public void removeCard(int pos) {
        hand.remove(pos);
    }

    public int getCardCount() {
        return hand.size();
    }

    public Card getCard(int pos) {
        return hand.get(pos);
    }


}


