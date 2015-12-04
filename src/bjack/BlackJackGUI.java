package bjack;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Kerdo Kullamäe on 6.11.2015.
 */

/**
 * Klass, mis põhimõtteliselt paneb kõik otsad kokku ja võimaldab
 * avada mängu.
 */
public class BlackJackGUI extends JPanel {

    /**
     * Avab akna, mis kuvab BlackJackGUI sisu.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Blackjack");
        BlackJackGUI content = new BlackJackGUI();
        window.setContentPane(content);
        window.setSize(700, 500);
        window.setResizable(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * Konstruktor, mis moodustab akna kuva. Keskmise osa moodustab CardPanel,
     * alumise osa moodustab buttonPanel, kus on nupud ja slider.
     */
    public BlackJackGUI() {

        setLayout(new BorderLayout());

        CardPanel board = new CardPanel();
        board.setBackground(new Color(0, 102, 0));
        add(board, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 102, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        JLabel text = new JLabel();
        text.setForeground(Color.WHITE);
        text.setText("BET: ");
        buttonPanel.add(text);

        JSlider slider = new JSlider();
        slider.setBackground(new Color(0, 102, 0));
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setForeground(Color.WHITE);
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
}
