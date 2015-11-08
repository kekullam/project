package bjack;

/**
 * Created by Kerdo Kullamäe on 6.11.2015.
 */
public class BlackJackHand extends Hand {


    public int getBlackJackValue() {

        int sum;
        boolean ace;
        int cards;

        sum = 0;
        ace = false;
        cards = getCardCount();
        for (int i = 0; i < cards; i++) {
            Card card;
            int cardVal;
            card = getCard(i);
            cardVal = card.getValue();
            if (cardVal > 10) {
                cardVal = 10;
            }
            if (cardVal == 1) {
                ace = true;
            }
            sum = sum + cardVal;
        }
        if (ace = true && sum + 10 <=21) {
            sum = sum + 10;

        }
            return sum;
        }
    }

