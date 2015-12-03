package bjack;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by Kerdo Kullam�e on 28.11.2015.
 */


/**
 * Klass, mis kuvab kaardid ja k�itub vastavalt m�ngija valikutele
 */
public class CardPanel extends JPanel implements ActionListener, ChangeListener {

    Deck deck;
    BlackJackHand hand;
    BlackJackHand dealerHand;
    String message;  // Text
    String message2; // Money

    int playerBet;
    int playerMoney;

    boolean gameInProgress;
    boolean gameStopped;
    boolean dealActive;

    Font customFont;

    Image cardImages;

    /**
     * Konstruktor, mis m��rab fondi, taga- ja esiplaani v�rvi ja alustab esimest m�ngu.
     */
    CardPanel() {
        loadImage();
        setBackground(new Color(0, 102, 0));
        setForeground(Color.WHITE);
        customFont = new Font("Arial", Font.PLAIN, 18);
        doNewGame();
    }

    /**
     * Kuulab sliderit ning omistab vastava v��rtuse playerBet'ile.
     */
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            playerBet = source.getValue();
        }
    }

    /**
     * Kutsub v�lja meetodi vastavalt m�ngija vajutatud nupule.
     */
    public void actionPerformed(ActionEvent evt) {
        String command = evt.getActionCommand();
        if (command.equals("HIT") && !gameStopped) {
            doHit();
        }
        if (command.equals("STAND") && !gameStopped) {
            doStand();
        }
        if (command.equals("NEW GAME")) {
            doNewGame();
        }
        if (command.equals("DEAL") && gameStopped && dealActive && playerBet != 0 && playerBet <= playerMoney) {
            doDeal();
        }
    }

    /**
     * Joonistab 79x123 pixli suuruse ristk�liku kujulise kaardi. Kaardi joonistamine
     * vajab pildi faili "cards.png".
     * @param g
     * @param card Kaart mis joonistatakse, kui kaardi v��rtus on null, siis joonistatakse seljaga
     * olev kaart
     * @param x kaardi vasaku �lemise nurga x-koordinaat.
     * @param y kaardi vasaku �lemise nurga y-koordinaat.
     */
    public void drawCard(Graphics g, Card card, int x, int y) {
        int cx;
        int cy;
        if (card == null) {
            cy = 4*123;
            cx = 2*79;
        } else {
            cx = (card.getValue()-1) *79;
            switch (card.getSuit()) {
                case Card.CLUBS:
                    cy = 0; break;
                case Card.DIAMONDS:
                    cy = 123; break;
                case Card.HEARTS:
                    cy = 123*2; break;
                default:
                    cy = 123*3; break;
            }
        }
        g.drawImage(cardImages, x, y, x + 79, y + 123, cx, cy, cx + 79, cy + 123, this);
    }

    /**
     * Laeb pildi failist "cards.png"
     */
    private void loadImage() {
        ClassLoader c1 = getClass().getClassLoader();
        URL imageURL = c1.getResource("cards.png");
        if (imageURL != null) {
            cardImages = Toolkit.getDefaultToolkit().createImage(imageURL);
        }
    }

    /**
     * Meetod, mis joonistab s�numid ja jagatud kaardid m��ratud kohta.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(customFont);
        g.drawString(message, 50, 400);
        g.drawString(message2, 50, 360);

        int cardCt = hand.getCardCount();
        int cardCtDealer = dealerHand.getCardCount();
        if (gameInProgress) {
            for (int i = 0; i < cardCt; i++) {
                drawCard(g, hand.getCard(i), 75 + i * 94, 200);
            }
            for (int i = 0; i < cardCtDealer; i++) {
                if (cardCtDealer < 2) {
                    drawCard(g, dealerHand.getCard(0), 75, 50);
                    drawCard(g, null, 169, 50);
                } else {
                    drawCard(g, dealerHand.getCard(i), 75 + i * 94, 50);
                }
            }
        } else {
            drawCard(g, null, 75, 50);
            drawCard(g, null, 75, 200);
            drawCard(g, null, 169, 50);
            drawCard(g, null, 169, 200);
        }
    }

    /**
     * Alustab uut m�ngu kui on v�lja kutsutud.
     */
    void doNewGame() {
        playerMoney = 100;
        deck = new Deck();
        hand = new BlackJackHand();
        dealerHand = new BlackJackHand();
        message = "Total: " + 0;
        message2 = "Money: " + playerMoney;
        gameInProgress = false;
        gameStopped = true;
        dealActive = true;
        repaint();
    }

    /**
     * Kutsutakse v�lja actionPerformed() poolt, kui m�ngija vajutab "HIT" nuppu.
     * Kui m�ngija kaartide v��rtus on �le 21, siis m�ng l�ppeb ja "DEAL" nupp aktiveerub.
     */
    void doHit() {
        hand.addCard(deck.dealCard());
        message = "Total: " + hand.getBlackJackValue();
        if (hand.getBlackJackValue() == 21) {
            message = "You have 21";
        } if (hand.getBlackJackValue() > 21) {
            message = "You went over 21";
            gameStopped = true;
            dealActive = true;
        }
        repaint();
    }
    /**
     * Kutsutakse v�lja actionPerformed() poolt, kui m�ngija vajutab "STAND" nuppu.
     * Jagab diilerile �he kaardi juurde.
     * K�itub vastavalt olukorrale.
     * L�petab m�ngu ning aktiveerib nupu "DEAL".
     */
    void doStand() {
        dealerHand.addCard(deck.dealCard());
        if (dealerHand.getBlackJackValue() == 21) {
            message = "You lost, dealer got a BLACKJACK";
        } while (dealerHand.getBlackJackValue() < 16) {
            dealerHand.addCard(deck.dealCard());
        } if (dealerHand.getBlackJackValue() > 21) {
            message = "You won, dealer went over 21";
            playerMoney += playerBet * 2;
            message2 = "Money: " + playerMoney;
        } else if (dealerHand.getBlackJackValue() == hand.getBlackJackValue()) {
            playerMoney += playerBet;
            message2 = "Money: " + playerMoney;
            message = "Tie, you both have " + dealerHand.getBlackJackValue();
        } else if (dealerHand.getBlackJackValue() >= 16 && dealerHand.getBlackJackValue() > hand.getBlackJackValue()){
            message = "You lose!       You: " + hand.getBlackJackValue() + "      Dealer: " + dealerHand.getBlackJackValue();
        } else {
            message = "You win!       You: " + hand.getBlackJackValue() + "      Dealer: " + dealerHand.getBlackJackValue();
            playerMoney += playerBet*2;
            message2 = "Money: " + playerMoney;
        }
        gameStopped = true;
        dealActive = true;
        repaint();
    }

    /**
     * Kutsutakse v�lja actionPerformed() poolt, kui m�ngija vajutab nuppu "DEAL" ja
     * m�ngija on sisestanud panuse(panus peab olema v�iksem/v�rdne m�ngija rahast).
     * Paneb m�ngu k�ima, jagab kaardid ning muudab "DEAL" nupu mitteaktiivseks.
     */
    public void doDeal() {
        gameInProgress = true;
        deck = new Deck();
        hand = new BlackJackHand();
        dealerHand = new BlackJackHand();
        deck.shuffle();
        hand.addCard(deck.dealCard());
        hand.addCard(deck.dealCard());
        message = "Total: " + hand.getBlackJackValue();
        dealerHand.addCard(deck.dealCard());
        playerMoney -= playerBet;
        message2 = "Money: " + playerMoney ;
        gameStopped = false;
        dealActive = false;
        repaint();
    }
}

