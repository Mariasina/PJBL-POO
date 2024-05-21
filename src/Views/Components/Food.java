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
        this.x = generator.nextInt(1000);
        this.y = generator.nextInt(700);
        this.points = 5;
        this.size = 20;

        System.out.println(x);
        System.out.println(y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }   
    
}
