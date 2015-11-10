package bjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by Kerdo Kullamäe on 6.11.2015.
 */
public class BlackJackGUI extends JPanel {

    public static void main (String[] args) {
        JFrame window = new JFrame("BlackJack");
        BlackJackGUI content = new BlackJackGUI();
        window.setContentPane(content);
        window.setSize(700, 500);
        window.setResizable(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);


    }

    public BlackJackGUI() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout(0, 0));

        CardPanel board = new CardPanel();
        board.setBackground(new Color(0, 120, 0));
        add(board, BorderLayout.CENTER);


        JPanel  buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 120, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        JTextField bet = new JTextField( 5);
        buttonPanel.add(bet);

        JButton hit = new JButton("HIT");
        hit.addActionListener(board);
        buttonPanel.add(hit);

        JButton stand = new JButton("STAND");
        stand.addActionListener(board);
        buttonPanel.add(stand);

        JButton deal = new JButton("DEAL");
        deal.addActionListener(board);
        buttonPanel.add(deal);

        JButton newGame = new JButton("NEW GAME");
        newGame.addActionListener(board);
        board.add(newGame);

    }

    private class CardPanel extends JPanel implements ActionListener {

        Deck deck;
        BlackJackHand hand;
        BlackJackHand dealerHand;
        String message;  // Text
        String message2; // Money
        String message3; // bet
        int playerMoney;
        int playerBet;


        boolean gameInProgress;

        Font bigFont;

        Image cardImages;


        CardPanel() {
            loadImage();
            setBackground(new Color(0, 120, 0));
            setForeground(Color.BLACK);
            bigFont = new Font("Serif", Font.BOLD, 15);
            setPreferredSize(new Dimension(15+4*(15+79), 185));
            doNewGame();
        }

        public void actionPerformed(ActionEvent evt) {
            String command = evt.getActionCommand();
                if (command.equals("HIT")) {
                    doHit();
                }
                if (command.equals("STAND")) {
                    doStand();
                }
                if (command.equals("NEW GAME")) {
                doNewGame();
                }
                if (command.equals("DEAL")) {
                    doDeal();
                }
        }

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

        private void loadImage() {
            ClassLoader c1 = getClass().getClassLoader();
            URL imageURL = c1.getResource("cards.png");
            if (imageURL != null) {
                cardImages = Toolkit.getDefaultToolkit().createImage(imageURL);
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (cardImages == null) {
                g.drawString("Error: Can't get card image", 10, 30);
                return;
            }
            g.setFont(bigFont);
            g.drawString(message, 50, 400);
            g.drawString(message2, 50, 380);

            int cardCt = hand.getCardCount();
            int cardCtDealer = dealerHand.getCardCount();
            if (gameInProgress) {
                // drawCard(g, null, 75 + cardCt * (15+79), 150);
                for (int i = 0; i < cardCt; i++) {
                    drawCard(g, hand.getCard(i), 75 + i * (15 + 79), 200);
                }
                for (int i = 0; i < cardCtDealer; i++) {
                    if (cardCtDealer <= 2) {
                        drawCard(g, dealerHand.getCard(0), 75 + 0 * (15 + 79), 50);
                        drawCard(g, null, 75 + 1 * (15 + 79), 50);
                    } else {
                        drawCard(g, dealerHand.getCard(i), 75 + i * (15 + 79), 50);
                    }


                }
            }
        }

        void opening() {



        }

        void doNewGame() {
            //if (gameInProgress) {
            //    message = "You still have to finish your game!";
              //  repaint();
               // return;
            //}
            playerMoney = 100;
            deck = new Deck();
            hand = new BlackJackHand();
            dealerHand = new BlackJackHand();
            deck.shuffle();
            hand.addCard(deck.dealCard());
            hand.addCard(deck.dealCard());
            if (hand.getBlackJackValue() == 21) {
                message = "Lucky you! You have blackjack already!";
            } else {
                message = "Total: " + hand.getBlackJackValue();
            }
            dealerHand.addCard(deck.dealCard());
                //dealerHand.addCard(deck.dealCard());
            message2 = "Money: " + playerMoney;
            message3 = "Bet";
            gameInProgress = true;
            repaint();
        }

        void doHit() {
            hand.addCard(deck.dealCard());
            message = "Total: " + hand.getBlackJackValue();
            if (hand.getBlackJackValue() == 21) {
                message = "You got BLACKJACK!";

                // gameInProgress = false;
            } if (hand.getBlackJackValue() > 21) {
                message = "You went over 21!";
                // gameInProgress = false;


            }
            repaint();




        }

        void doStand() {

            // message = "You decided to stand! Your final is: " + hand.getBlackJackValue();
            if (dealerHand.getBlackJackValue() < 16) {
                dealerHand.addCard(deck.dealCard());
                dealerHand.addCard(deck.dealCard());
            } else if (dealerHand.getBlackJackValue() == 21) {
                message = "You lost, dealer got a BLACKJACK!";
            } else if (dealerHand.getBlackJackValue() > 21) {
                message = "You won, dealer went over 21";
                //gameInProgress = false;
            } else if (dealerHand.getBlackJackValue() >= 16 && dealerHand.getBlackJackValue() > hand.getBlackJackValue()) {
                message = "You lose!   Dealer: " + dealerHand.getBlackJackValue() + "   You: " + hand.getBlackJackValue();
                //gameInProgress = false;
            } else  {
                message = "You win!     Dealer:" + dealerHand.getBlackJackValue() + "   You: " + hand.getBlackJackValue();
                //gameInProgress = false;
            }
            repaint();
        }

        public void doDeal() {
            deck = new Deck();
            hand = new BlackJackHand();
            dealerHand = new BlackJackHand();
            deck.shuffle();
            hand.addCard(deck.dealCard());
            hand.addCard(deck.dealCard());
            if (hand.getBlackJackValue() == 21) {
                message = "Lucky you! You have blackjack already!";
            } else {
                message = "Total: " + hand.getBlackJackValue();
            }
            dealerHand.addCard(deck.dealCard());
            //dealerHand.addCard(deck.dealCard());
            message2 = "Money: " + playerMoney;
            gameInProgress = true;
            repaint();

        }

    }

}
