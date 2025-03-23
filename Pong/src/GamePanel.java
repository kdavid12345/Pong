import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private final HighScores highScores;
    private final AudioPlayer audioPlayer;
    private final Ball ball;
    private final Paddle leftPaddle;
    private final Paddle rightPaddle;
    private final Score scoreLeft;
    private final Score scoreRight;
    private boolean multiplayer;
    private final Timer updateTimer;
    private final JButton backButton;
    private final JButton restartButton;
    private final JLabel gameOverLabel;
    private final JLabel multiplayerScoresLabel;

    public GamePanel(CardLayout cardLayout, JPanel cardPanel, HighScores highScores, AudioPlayer audioPlayer) {
        setLayout(null);
        setSize(800, 600);

        this.highScores = highScores;
        this.audioPlayer = audioPlayer;

        ball = new Ball(300, 400);

        leftPaddle = new Paddle(0, 250);
        rightPaddle = new Paddle(getWidth() - 20, 250);

        scoreLeft = new Score(0, null);
        scoreRight = new Score(0, null);

        multiplayer = false;

        multiplayerScoresLabel = new JLabel("0   0");
        multiplayerScoresLabel.setVerticalAlignment(JLabel.TOP);
        multiplayerScoresLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
        multiplayerScoresLabel.setLocation(331, 10);
        multiplayerScoresLabel.setSize(150, 150);
        multiplayerScoresLabel.setForeground(Color.WHITE);
        multiplayerScoresLabel.setVisible(false);

        gameOverLabel = new JLabel();
        gameOverLabel.setLocation(100, 250);
        gameOverLabel.setSize(650, 150);
        gameOverLabel.setFont(new Font("Consolas", Font.PLAIN, 22));
        gameOverLabel.setVerticalAlignment(SwingConstants.TOP);
        gameOverLabel.setForeground(Color.WHITE);

        updateTimer = new Timer(15, _ -> update());

        backButton = new JButton("Vissza a menübe");
        backButton.setBackground(Color.white);
        backButton.setFont(new Font("Consolas", Font.PLAIN, 14));
        backButton.setBounds(100, 300, 200, 50);
        backButton.addActionListener(_ -> {
            highScores.saveScores();
            cardLayout.show(cardPanel, "mm");
            audioPlayer.playBackgroundMusic();
        });

        restartButton = new JButton("Újrakezdés");
        restartButton.setBackground(Color.white);
        restartButton.setFont(new Font("Consolas", Font.PLAIN, 14));
        restartButton.setBounds(500, 300, 150, 50);
        restartButton.addActionListener(_ -> {
            startGame(multiplayer, scoreLeft.getName(), scoreRight.getName());
            audioPlayer.playBackgroundMusic();
        });

        add(backButton);
        add(restartButton);
        add(gameOverLabel);
        add(multiplayerScoresLabel);
        setBackground(Color.BLACK);
    }

    public void startGame(boolean multiplayer, String leftName, String rightName) {
        this.multiplayer = multiplayer;
        if (multiplayer) {
            multiplayerScoresLabel.setVisible(true);
        }
        multiplayerScoresLabel.setText("0   0");

        scoreLeft.setName(leftName);
        scoreRight.setName(rightName);
        scoreLeft.setScore(0);
        scoreRight.setScore(0);

        backButton.setVisible(false);
        restartButton.setVisible(false);
        gameOverLabel.setVisible(false);

        ball.reset(400, 300);
        leftPaddle.reset(0, 250);
        rightPaddle.reset(getWidth() - 20, 250);

        updateTimer.start();
    }

    private void gameOver() {
        updateTimer.stop();

        audioPlayer.stopBackgroundMusic();

        if (multiplayer) {
            gameOverLabel.setText("Játék vége!                 " + ((scoreLeft.getScore() > scoreRight.getScore()) ? scoreLeft.getName() : scoreRight.getName()) + " nyert!");
            gameOverLabel.setVisible(true);
        } else {
            gameOverLabel.setText("Játék vége!                 Pontszám: " + scoreLeft.getScore());
            gameOverLabel.setVisible(true);
            highScores.addScore(scoreLeft);
        }

        backButton.setVisible(true);
        restartButton.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawLine(400, 0, 400, 600);
        ball.draw(g);
        leftPaddle.draw(g);
        rightPaddle.draw(g);
    }

    private void update() {
        if (multiplayer) {
            if (ball.collides(leftPaddle)) {
                audioPlayer.playEffect();
                ball.bounceX();
            }

            if (ball.collides(rightPaddle)) {
                audioPlayer.playEffect();
                ball.bounceX();
            }

            if (ball.getY() < 0 || ball.getY() + ball.getBounds().height > getHeight()) {
                ball.bounceY();
            }

            if (ball.getX() > getWidth()) {
                scoreLeft.increase();
                ball.reset(400, 300);
                leftPaddle.reset(0, 250);
                rightPaddle.reset(getWidth() - 20, 250);
            }

            if (ball.getX() < 0) {
                scoreRight.increase();
                ball.reset(400, 300);
                leftPaddle.reset(0, 250);
                rightPaddle.reset(getWidth() - 20, 250);
            }

            rightPaddle.move();

            multiplayerScoresLabel.setText(scoreLeft.getScore() + "   " + scoreRight.getScore());

            if (scoreLeft.getScore() == 5 || scoreRight.getScore() == 5) {
                gameOver();
            }
        } else {
            if (ball.collides(leftPaddle)) {
                scoreLeft.increase();
                audioPlayer.playEffect();
                ball.bounceX();
            }

            if (ball.collides(rightPaddle)) {
                audioPlayer.playEffect();
                ball.bounceX();
            }

            if (ball.getY() < 0 || ball.getY() + 20 > getHeight()) {
                ball.bounceY();
            }

            if (ball.getX() < 0) {
                gameOver();
            }

            if (ball.getX() > getWidth()) {
                scoreLeft.increase();
                ball.reset(400, 300);
                leftPaddle.reset(0, 250);
            }

            rightPaddle.moveAI(ball);
        }
        ball.move();
        leftPaddle.move();
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            leftPaddle.setVelocityUp(9);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            leftPaddle.setVelocityDown(9);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            rightPaddle.setVelocityUp(9);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            rightPaddle.setVelocityDown(9);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            leftPaddle.setVelocityUp(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            leftPaddle.setVelocityDown(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            rightPaddle.setVelocityUp(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            rightPaddle.setVelocityDown(0);
        }
    }
}