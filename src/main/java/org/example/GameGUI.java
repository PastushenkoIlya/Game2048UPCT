package org.example;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame implements GameOverListener {
    private Canvas canvas;
    private Juego juego;
    private String playerName;
    private Records records;
    private ScorePanel scorePanel;

    public GameGUI() {
        juego = new Juego();
        scorePanel = new ScorePanel();
        records = new Records(this);
        canvas = new Canvas(juego, this, scorePanel);

        setTitle("2048 Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);

        JPanel topPanel = new JPanel(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");


        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> nuevoJuego());
        menu.add(newGameItem);

        JMenuItem recordsItem = new JMenuItem("Records");
        recordsItem.addActionListener(e -> records.setVisible(true));
        menu.add(recordsItem);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        add(scorePanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        setVisible(true);

        nuevoJuego();
    }

    public void nuevoJuego() {
        Jugador jugadorDialog = new Jugador(this);
        jugadorDialog.setVisible(true);
        playerName = jugadorDialog.getPlayerName();

        if (playerName != null && !playerName.isEmpty()) {
            juego.nuevoJuego();
            canvas.repaint();
        } else {
            dispose(); // Close the application if no valid name is entered
        }
    }

    @Override
    public void onGameOver(long points) {
        records.addRecord(playerName, points);
        JOptionPane.showMessageDialog(this, "Game Over! " + playerName + " scored: " + points, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
}