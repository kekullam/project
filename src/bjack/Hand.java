package bjack;

import java.util.ArrayList;

/**
 * Created by Kerdo Kullam�e on 5.11.2015.
 */
public class Hand {

    private ArrayList<Card> hand;


    public Hand() {

        hand = new ArrayList<Card>();
    }

    public void addCard(Card a) {
        hand.add(a);
      }

    public int getCardCount() {
        return hand.size();
    }

    public Card getCard(int pos) {
        return hand.get(pos);
    }


}


