import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    private final GamePanel gamePanel;

    public GameFrame() {
        setTitle("Pong");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                gamePanel.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gamePanel.keyReleased(e);
            }
        });

        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.setPreferredSize(new Dimension(800, 600));

        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.playBackgroundMusic();

        Settings settings = new Settings(cardLayout, cardPanel, audioPlayer);

        HighScores highScores = new HighScores(cardLayout, cardPanel);

        gamePanel = new GamePanel(cardLayout, cardPanel, highScores, audioPlayer);

        MainMenu mainMenu = new MainMenu(cardLayout, cardPanel, gamePanel);

        cardPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                pack();
            }
        });

        cardPanel.add(mainMenu, "mm");
        cardPanel.add(gamePanel, "gp");
        cardPanel.add(settings, "st");
        cardPanel.add(highScores, "hs");

        add(cardPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}