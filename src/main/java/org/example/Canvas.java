package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Canvas extends JPanel {
    private Juego juego;
    private final GameOverListener gameOverListener;
    private ScorePanel scorePanel;


    public Canvas(Juego juego, GameOverListener gameOverListener, ScorePanel scorePanel) {
        this.juego = juego;
        this.gameOverListener = gameOverListener;
        this.scorePanel = scorePanel;

        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        juego.jugarIzquierda();
                        break;
                    case KeyEvent.VK_RIGHT:
                        juego.jugarDerecha();
                        break;
                    case KeyEvent.VK_UP:
                        juego.jugarArriba();
                        break;
                    case KeyEvent.VK_DOWN:
                        juego.jugarAbajo();
                        break;
                }
                scorePanel.updateScore(juego.puntos()); // Update score after each move
                if (juego.finJuego()) {
                    gameOverListener.onGameOver(juego.puntos());
                    juego.nuevoJuego();
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long[][] tablero = juego.getTablero();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                drawTile(g, tablero[i][j], i, j);
            }
        }
    }

    private void drawTile(Graphics g, long value, int row, int col) {
        int x = col * 100;
        int y = row * 100;
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, 100, 100);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 100, 100);
        if (value != 0) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString(Long.toString(value), x + 30, y + 60);
        }
    }
}