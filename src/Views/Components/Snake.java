package Views.Components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Snake extends JComponent{
    private int x;
    private int y;
    private int size;
    private int velocity;

    public Snake(int x, int y) {
        this.x = x - 15;
        this.y = y - 15;
        this.size = 30;
        this.velocity = 20;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, size, size);
    }

    public int getSnakeX() {
        return x;
    }

    public void setSnakeX(int x) {
        this.x = x;
        repaint();
    }

    public int getSnakeY() {
        return y;
    }

    public void setSnakeY(int y) {
        this.y = y;
        repaint();
    }
}
