package bjack;

/**
 * Created by Kerdo Kullam‰e on 6.11.2015.
 */

public class    BlackJackHand extends Hand {

    /**
     * Meetod, mis tagastab kaartide v‰‰rtuse vastavalt blackjacki
     * reeglitele.
     * @return kaartide v‰‰rtus vastavalt blackjacki reeglitele.
     */
    public int getBlackJackValue() {

        int sum = 0;
        boolean aces = false;
        int cardsInHand = getCardCount();

        for (int i = 0; i < cardsInHand; i++) {
            Card card = getCard(i);
            int cardVal = card.getValue();
            if (cardVal > 10) {
                cardVal = 10;
            }
            if (cardVal == 1) {
                aces = true;
            }
            sum += cardVal;
        }
            if (aces && sum + 10 <= 21) {
                sum += 10;
            }
        return sum;
    }
}
