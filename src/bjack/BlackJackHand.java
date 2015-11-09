package bjack;

/**
 * Created by Kerdo Kullam‰e on 6.11.2015.
 */
public class BlackJackHand extends Hand {

    // Tagastab kaardi v‰‰rtused vastavalt blackjacki reeglitele
    public int getBlackJackValue() {

        int sum = 0;
        int aces = 0;
        int cards = getCardCount();

            for (int i=0;i<cards;i++) {
                switch(i) {
                    case 1: sum += 1;
                    case 2: sum += 2;
                    case 3: sum += 3;
                    case 4: sum += 4;
                    case 5: sum += 5;
                    case 6: sum += 6;
                    case 7: sum += 7;
                    case 8: sum += 8;
                    case 9: sum += 9;
                    case 10: sum += 10;
                    case 11: sum += 10;
                    case 12: sum += 10;
                    case 13: sum += 10;
                }
            }
        for (int i=0; i < aces; i++) {
            if (sum > 10) {
                sum += 1;
            } else {
                sum += 11;
            }
        }
        return sum;
    }
}
