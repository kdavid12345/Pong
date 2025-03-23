import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {
    private final JSlider volumeSlider;

    public Settings(CardLayout cardLayout, JPanel cardPanel, AudioPlayer audioPlayer) {
        setBackground(Color.white);
        setLayout(new GridLayout(3, 1));
        setSize(new Dimension(300, 170));

        JLabel volumeLabel = new JLabel("  Zene hangereje:");
        volumeLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 70);
        volumeSlider.setBackground(Color.white);
        volumeSlider.setForeground(Color.black);
        volumeSlider.setFont(new Font("Consolas", Font.PLAIN, 14));
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintLabels(true);
        volumeSlider.addChangeListener(_ -> audioPlayer.setMusicVolume((float) volumeSlider.getValue() / 100));

        JButton backButton = new JButton("Vissza a menübe");
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.white);
        backButton.addActionListener(_ -> {
            cardPanel.setPreferredSize(new Dimension(800, 600));
            cardPanel.setSize(new Dimension(800, 600));
            cardLayout.show(cardPanel, "mm");
        });

        add(volumeLabel);
        add(volumeSlider);
        add(backButton);
    }
}