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

    static CardPanel board = new CardPanel();

    /**
     * Avab akna, mis kuvab BlackJackGUI sisu.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Blackjack");
        window.setJMenuBar(getMenuBar());
        BlackJackGUI content = new BlackJackGUI();
        window.setContentPane(content);
        window.setSize(700, 520);
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

        this.setLayout(new BorderLayout());

        board.setBackground(new Color(0, 102, 0));
        this.add(board, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(0, 102, 0));
        this.add(buttons, BorderLayout.SOUTH);

        JLabel text = new JLabel();
        text.setForeground(Color.WHITE);
        text.setText("BET: ");
        buttons.add(text);

        JSlider slider = new JSlider();
        slider.setBackground(new Color(0, 102, 0));
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setForeground(Color.WHITE);
        slider.setPaintLabels(true);
        slider.setValue(0);
        slider.addChangeListener(board);
        buttons.add(slider);

        JButton hit = new JButton("HIT");
        hit.addActionListener(board);
        buttons.add(hit);

        JButton stand = new JButton("STAND");
        stand.addActionListener(board);
        buttons.add(stand);

        JButton deal = new JButton("DEAL");
        deal.addActionListener(board);
        buttons.add(deal);
    }

    public static JMenuBar getMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        JMenuItem item = new JMenuItem("New game");
        JMenuItem item2 = new JMenuItem("Exit");
        item.addActionListener(board);
        item2.addActionListener(board);
        menu.add(item);
        menu.add(item2);
        return menuBar;
    }
}