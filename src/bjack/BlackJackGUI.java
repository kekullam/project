package bjack;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by Kerdo Kullamäe on 6.11.2015.
 */
public class BlackJackGUI extends JPanel {

    public static void main (String[] args) {
        JFrame window = new JFrame("Blackjack");
        BlackJackGUI content = new BlackJackGUI();
        window.setContentPane(content);
        window.setSize(700, 550);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public BlackJackGUI() {

        setLayout(new BorderLayout(0, 0));

        CardPanel board = new CardPanel();
        board.setBackground(new Color(0, 102, 0));
        add(board, BorderLayout.CENTER);

        JPanel  buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 102, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        JSlider slider = new JSlider();
        slider.setBackground(new Color(0, 102, 0));
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
        slider.setPaintLabels(true);
        slider.setValue(0);
        slider.addChangeListener(board);
        buttonPanel.add(slider);


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

    private class CardPanel extends JPanel implements ActionListener, ChangeListener {

        Deck deck;
        BlackJackHand hand;
        BlackJackHand dealerHand;
        String message;  // Text
        String message2; // Money
        int playerBet;
        int playerMoney;

        boolean gameInProgress;
        boolean gameStopped;

        Font customFont;

        Image cardImages;

        CardPanel() {
            loadImage();
            setBackground(new Color(0, 102, 0));
            setForeground(Color.WHITE);
            customFont = new Font("Serif", Font.PLAIN, 20);
            setPreferredSize(new Dimension(15 + 4 * (15 + 79), 185));
            doNewGame();
        }

        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                 playerBet = source.getValue();
            }
        }

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
                if (command.equals("DEAL") && playerBet != 0 && playerBet <= playerMoney) {
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

            g.setFont(customFont);
            g.drawString(message, 50, 400);
            g.drawString(message2, 50, 360);

            int cardCt = hand.getCardCount();
            int cardCtDealer = dealerHand.getCardCount();
            if (gameInProgress) {
                for (int i = 0; i < cardCt; i++) {
                    drawCard(g, hand.getCard(i), 75 + i * (15 + 79), 200);
                }
                for (int i = 0; i < cardCtDealer; i++) {
                    if (cardCtDealer < 2) {
                        drawCard(g, dealerHand.getCard(0), 75, 50);
                        drawCard(g, null, 75 +(15 + 79), 50);
                    } else {
                        drawCard(g, dealerHand.getCard(i), 75 + i * (15 + 79), 50);
                    }
                }
            } else {
                drawCard(g, null, 75, 50);
                drawCard(g, null, 75 +(15 + 79), 50);
                drawCard(g, null, 75, 200);
                drawCard(g, null, 75 +(15 + 79), 200);
            }
        }

        void doNewGame() {
            playerMoney = 100;
            deck = new Deck();
            hand = new BlackJackHand();
            dealerHand = new BlackJackHand();
            message = "Total: " + 0;
            message2 = "Money: " + playerMoney;
            gameInProgress = false;
            gameStopped = true;
            repaint();
        }

        void doHit() {
            hand.addCard(deck.dealCard());
            message = "Total: " + hand.getBlackJackValue();
            if (hand.getBlackJackValue() == 21) {
                message = "You have 21";
            } if (hand.getBlackJackValue() > 21) {
                message = "You went over 21";
                gameStopped = true;
            }
            repaint();

        }

        void doStand() {

            dealerHand.addCard(deck.dealCard());
            if (dealerHand.getBlackJackValue() == 21) {
            message = "You lost, dealer got a BLACKJACK";
                message2 = "Money: " + playerMoney;
            } if (dealerHand.getBlackJackValue() < 16) {
                dealerHand.addCard(deck.dealCard());
            } if (dealerHand.getBlackJackValue() > 21) {
                message = "You won, dealer went over 21";
                playerMoney += playerBet*2;
                message2 = "Money: " + playerMoney;
            } else if (dealerHand.getBlackJackValue() >= 16 && dealerHand.getBlackJackValue() > hand.getBlackJackValue()){
                message = "You lose!       You: " + hand.getBlackJackValue() + "    Dealer: " + dealerHand.getBlackJackValue();
                message2 = "Money: " + playerMoney;
            } else {
                message = "You win!        You: " + hand.getBlackJackValue() + "    Dealer: " + dealerHand.getBlackJackValue();
                playerMoney += playerBet*2;
                message2 = "Money: " + playerMoney;
            }
            gameStopped = true;
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
                message = "Lucky you! You have blackjack already";
            } else {
                message = "Total: " + hand.getBlackJackValue();
            }
            dealerHand.addCard(deck.dealCard());
            playerMoney -= playerBet;
            message2 = "Money: " + playerMoney ;
            gameInProgress = true;
            gameStopped = false;
            repaint();
        }
    }
}

