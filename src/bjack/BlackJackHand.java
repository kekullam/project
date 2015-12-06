package bjack;

/**
 * Created by Kerdo Kullam‰e on 6.11.2015.
 */

public class BlackJackHand extends Hand {

    /**
     * Meetod, mis tagastab kaartide v‰‰rtuse vastavalt blackjacki
     * reeglitele.
     * @return kaartide v‰‰rtus vastavalt blackjacki reeglitele.
     */
    public int getBlackJackValue() {

        int sum = 0;
        int cardsInHand = getCardCount();

        for (int i=0;i<cardsInHand;i++) {
            Card card = getCard(i);

            switch(card.getValue()) {
                case 1: if ((sum + 11) <= 21) {
                    sum += 11;
                } else {
                    sum += 1;
                } break;
                case 2: sum +=2; break;
                case 3: sum +=3; break;
                case 4: sum +=4; break;
                case 5: sum +=5; break;
                case 6: sum += 6; break;
                case 7: sum += 7; break;
                case 8: sum += 8; break;
                case 9: sum += 9; break;
                case 10: sum += 10;break;
                case 11: sum += 10;break;
                case 12: sum += 10;break;
                case 13: sum += 10;break;
            }
        }
        return sum;
    }
}
