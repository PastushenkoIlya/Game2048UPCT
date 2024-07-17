package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jugador extends JDialog {
    private JTextField nameField;
    private String playerName;

    public Jugador(Frame parent) {
        super(parent, "Enter Player Name", true);
        setLayout(new BorderLayout());

        nameField = new JTextField(20);
        JButton okButton = new JButton("OK");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText().trim();
                if (!playerName.isEmpty()) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(Jugador.this, "Please enter a valid name.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(okButton);

        add(panel, BorderLayout.CENTER);

        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    public String getPlayerName() {
        return playerName;
    }
}

