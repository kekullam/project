package bjack;

/**
 * Created by Kerdo Kullam‰e on 6.11.2015.
 */

/**
 * Hand klassi alamklass.
 */
public class BlackJackHand extends Hand {

    /**
     * Meetod, mis tagastab kaartide v‰‰rtuse vastavalt blackjacki
     * reeglitele.
     * @return kaartide v‰‰rtus vastavalt blackjacki reeglitele.
     */
    public int getBlackJackValue() {

        int sum = 0;
        boolean aces = false;
        int cards = getCardCount();

        for (int i = 0; i < cards; i++) {
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
            if (aces == true && sum + 10 <= 21) {
                sum += 10;
            }
        return sum;
    }
}
