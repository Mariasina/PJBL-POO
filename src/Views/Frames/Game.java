package Views.Frames;

import Views.Components.Food;
import Views.Components.Snake;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Game extends JFrame{
    private int score;

    public Game(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        getContentPane().setBackground(new Color(117, 237, 92));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        Food food = new Food();
        Snake snake = new Snake(300, 200);
        snake.setBounds(0, 0, 1000, 700);
        food.setBounds(0, 0, 1000, 700);

        ActionListener gameLoop = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                m.fall();
                frame.repaint();
            }
        };

        if((food.getX() == snake.getX()) && (food.getY() == snake.getY())){
            score = score + food.getPoints();
        }

        System.out.println(score);

        this.add(food);
        this.add(snake);
        this.setVisible(true);

        snake.requestFocusInWindow();
    }

    
}
