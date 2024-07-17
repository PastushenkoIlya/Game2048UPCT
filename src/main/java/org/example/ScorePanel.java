package org.example;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private JLabel scoreLabel;

    public ScorePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        add(scoreLabel, BorderLayout.CENTER);
    }

    public void updateScore(long score) {
        scoreLabel.setText("Score: " + score);
    }
}