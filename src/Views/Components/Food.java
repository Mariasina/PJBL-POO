package Views.Components;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Food extends JComponent{
    private int x;
    private int y;
    private int points;
    private int size;

    public Food(){
        Random generator = new Random();
        this.x = (generator.nextInt(900 - size)) - 10;
        this.y = (generator.nextInt(500 - size)) - 10;
        this.points = 5;
        this.size = 20;

        System.out.println(x);
        System.out.println(y);
    }

    public void respawn(){
        Random generator = new Random();
        this.x =(generator.nextInt(900 - size)) - 10;
        this.y = (generator.nextInt(600 - size)) - 10;
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
