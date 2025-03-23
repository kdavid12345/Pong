import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class HighScores extends JPanel {
    private List<Score> scores;
    private final JTextArea textArea;

    public HighScores(CardLayout cardLayout, JPanel cardPanel) {
        setBackground(Color.white);
        setLayout(null);
        setSize(new Dimension(400, 300));

        scores = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/highscores.txt"));
            scores = reader.lines()
                    .map(line -> line.split("\\.+"))
                    .map(parts -> new Score(Integer.parseInt(parts[1]), parts[0]))
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Nem sikerült betölteni a pontszámokat.");
        }
        textArea = new JTextArea();
        textArea.setBounds(65, 0, 350, 250);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 23));
        textArea.setEditable(false);
        saveScores();

        JButton backButton = new JButton("Vissza a menübe");
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.white);
        backButton.setFont(new Font("Consolas", Font.PLAIN, 12));
        backButton.setBounds(0, 250, 400, 50);
        backButton.addActionListener(_ -> {
            cardPanel.setPreferredSize(new Dimension(800, 600));
            cardPanel.setSize(new Dimension(800, 600));
            cardLayout.show(cardPanel, "mm");
        });
        add(textArea);
        add(backButton);
    }

    public void addScore(Score score) {
        scores.add(score);
        scores.sort(Collections.reverseOrder());
        if (scores.size() > 5) {
            scores.removeLast();
        }
    }

    public void saveScores() {
        StringBuilder scoresText = new StringBuilder();
        for (Score sc : scores) {
            scoresText.append(sc.getName()).append(".".repeat(20 - sc.getName().length())).append(sc.getScore()).append("\n");
        }
        textArea.setText((scoresText.toString().isEmpty()) ? "Nincs mentett pontszám!" : scoresText.toString());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/highscores.txt"))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            System.out.println("Nem sikerült menteni a pontszámokat.");
        }
    }
}