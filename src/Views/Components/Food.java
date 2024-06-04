package Views.Components;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Food extends JComponent{
    private int x;
    private int y;
    private int points;
    private int size;
    private final int MARGIN = 20; // Define a margem mínima de distância das bordas

    public Food(){
        Random generator = new Random();
        this.size = 20;
        this.points = 5;
        this.x = generator.nextInt(800 - size - 2 * MARGIN) + MARGIN;
        this.y = generator.nextInt(500 - size - 2 * MARGIN) + MARGIN;

        System.out.println(x);
        System.out.println(y);
    }

    public void respawn(){
        Random generator = new Random();
        this.x = generator.nextInt(800 - size - 2 * MARGIN) + MARGIN;
        this.y = generator.nextInt(500 - size - 2 * MARGIN) + MARGIN;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
    }

    public int getFoodX() {
        return x;
    }

    public void setFoodX(int x) {
        this.x = x;
    }

    public int getFoodY() {
        return y;
    }

    public void setFoodY(int y) {
        this.y = y;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFoodSize() {
        return size;
    }

    public void setFoodSize(int size) {
        this.size = size;
    }   
    
}
