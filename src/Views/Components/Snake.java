package Views.Components;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Snake extends JComponent implements KeyListener {
    private int x;
    private int y;
    private int size;
    private int velocity;
    private int dx; // Variável para armazenar a mudança de x
    private int dy; // Variável para armazenar a mudança de y

    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 30;
        this.velocity = 20;
        this.dx = 0; // Inicialmente, a cobra não se move
        this.dy = 0;

        Timer timer = new Timer(100, e -> {
            this.x += this.dx; // Atualiza a posição de x
            this.y += this.dy;
            repaint();
        });
        timer.start(); // Inicia o Timer

        setFocusable(true); // Necessário para que o componente possa receber foco
        addKeyListener(this); // Adiciona o KeyListener ao componente
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT: 
                dx = -5; dy = 0; // Move para a esquerda
                break; 
            case KeyEvent.VK_RIGHT: 
                dx = 5; dy = 0; // Move para a direita
                break;
            case KeyEvent.VK_UP: 
                dx = 0; dy = -5; // Move para cima
                break; 
            case KeyEvent.VK_DOWN: 
                dx = 0; dy = 5; // Move para baixo
                break; 
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não utilizado, mas precisa estar aqui devido à interface KeyListener
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Não utilizado, mas precisa estar aqui devido à interface KeyListener
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, size, size);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
