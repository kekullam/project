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
    // http://math.hws.edu/eck/cs124/javanotes4/source/BlackjackHand.java'st algidee
    public int getBlackJackValue() {

        int sum = 0;
        boolean aces = false;

        for (int i = 0; i < getCardCount(); i++) {
            Card card = getCard(i);

            switch (card.getValue()) {
                case 1:sum += 1;break;
                case 2:sum += 2;break;
                case 3:sum += 3;break;
                case 4:sum += 4;break;
                case 5:sum += 5;break;
                case 6:sum += 6;break;
                case 7:sum += 7;break;
                case 8:sum += 8;break;
                case 9:sum += 9;break;
                case 10:case 11:
                case 12:case 13:
                    sum += 10;break;
            }
            if (card.getValue() == 1) {
                aces = true;
            }
        }
        if (aces == true && sum < 12) {
            sum += 10;
        }
        return sum;
    }
}

