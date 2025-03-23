import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final GamePanel gamePanel;
    private final JTextField leftNameFiled, rightNameField;
    private final JCheckBox multiPlayerCB;

    public MainMenu(CardLayout cardLayout, JPanel cardPanel, GamePanel gamePanel) {
        setLayout(null);
        setSize(new Dimension(800, 600));
        setBackground(Color.black);

        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.gamePanel = gamePanel;

        leftNameFiled = new JTextField();
        leftNameFiled.setBackground(Color.black);
        leftNameFiled.setForeground(Color.white);
        leftNameFiled.setFont(new Font("Consolas", Font.PLAIN, 16));
        leftNameFiled.setBounds(80, 270, 100, 30);
        leftNameFiled.setText("Játékos1");

        rightNameField = new JTextField();
        rightNameField.setBackground(Color.black);
        rightNameField.setForeground(Color.white);
        rightNameField.setFont(new Font("Consolas", Font.PLAIN, 16));
        rightNameField.setBounds(80, 320, 100, 30);
        rightNameField.setText("Játékos2");
        rightNameField.setVisible(false);

        multiPlayerCB = new JCheckBox("Multiplayer");
        multiPlayerCB.setBackground(Color.black);
        multiPlayerCB.setForeground(Color.white);
        multiPlayerCB.setFont(new Font("Consolas", Font.PLAIN, 16));
        multiPlayerCB.setBounds(80, 200, 200, 70);
        multiPlayerCB.addActionListener(_ -> rightNameField.setVisible(multiPlayerCB.isSelected()));

        JLabel MainTitle = new JLabel("Pong");
        MainTitle.setForeground(Color.white);
        MainTitle.setFont(new Font("Consolas", Font.BOLD, 40));
        MainTitle.setBounds(0, 50, 800, 100);
        MainTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JButton startGameButton = new JButton("Játék indítása");
        startGameButton.setBackground(Color.white);
        startGameButton.setFont(new Font("Consolas", Font.PLAIN, 14));
        startGameButton.setBounds(300, 200, 200, 70);
        startGameButton.addActionListener(_ -> startGame());

        JButton settingsButton = new JButton("Beállítások");
        settingsButton.setBackground(Color.white);
        settingsButton.setFont(new Font("Consolas", Font.PLAIN, 14));
        settingsButton.setBounds(300, 300, 200, 70);
        settingsButton.addActionListener(_ -> showSettings());

        JButton viewHighScoresButton = new JButton("Ranglista");
        viewHighScoresButton.setBackground(Color.white);
        viewHighScoresButton.setFont(new Font("Consolas", Font.PLAIN, 14));
        viewHighScoresButton.setBounds(300, 400, 200, 70);
        viewHighScoresButton.addActionListener(_ -> viewHighScores());

        JButton exitButton = new JButton("Kilépés");
        exitButton.setBackground(Color.white);
        exitButton.setFont(new Font("Consolas", Font.PLAIN, 14));
        exitButton.setBounds(300, 500, 200, 70);
        exitButton.addActionListener(_ -> System.exit(0));

        add(leftNameFiled);
        add(rightNameField);
        add(multiPlayerCB);
        add(MainTitle);
        add(startGameButton);
        add(settingsButton);
        add(viewHighScoresButton);
        add(exitButton);
    }

    private void startGame() {
        boolean multiplayer = multiPlayerCB.isSelected();
        cardPanel.setPreferredSize(new Dimension(800, 600));
        cardPanel.setSize(new Dimension(800, 600));
        cardLayout.show(cardPanel, "gp");
        gamePanel.startGame(multiplayer,
                (leftNameFiled.getText().length() > 15) ? leftNameFiled.getText().substring(0, 15) : leftNameFiled.getText(),
                (rightNameField.getText().length() > 15) ? rightNameField.getText().substring(0, 15) : rightNameField.getText());
    }

    private void showSettings() {
        cardPanel.setPreferredSize(new Dimension(300, 170));
        cardPanel.setSize(new Dimension(300, 170));
        cardLayout.show(cardPanel, "st");
    }

    private void viewHighScores() {
        cardPanel.setPreferredSize(new Dimension(400, 300));
        cardPanel.setSize(new Dimension(400, 300));
        cardLayout.show(cardPanel, "hs");
    }
}