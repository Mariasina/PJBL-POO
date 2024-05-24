package Views.Frames;

import Views.Components.Food;
import Views.Components.Snake;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JFrame implements KeyListener{
    private int score;
    private int snake_movX = 0; // Inicialmente, a cobra não se move
    private int snake_movY = 0;

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
            public void actionPerformed(ActionEvent e) {
                snake.setSnakeX(snake.getSnakeX() + snake_movX); 
                snake.setSnakeY(snake.getSnakeY() + snake_movY);

                if(((snake.getSnakeX() - 15) >= food.getFoodX()) && (snake.getSnakeX()-15 <= food.getFoodX() + food.getFoodSize()) 
                && (snake.getSnakeY() -15 >= food.getFoodY()) && (snake.getSnakeY() -15 <= food.getFoodY() + food.getFoodSize())){
                    score = score + food.getPoints();
                    food.respawn();                    
                }        

                System.out.println(score);
            }
        };

        Timer timer = new Timer(50, gameLoop);
        timer.start();

        this.add(food);
        this.add(snake);
        this.setVisible(true);

        this.addKeyListener(this);
        this.setFocusable(true);
        snake.requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT: 
                snake_movX = -5; snake_movY = 0; // Move para a esquerda
                break; 
            case KeyEvent.VK_RIGHT: 
                snake_movX = 5; snake_movY = 0; // Move para a direita
                break;
            case KeyEvent.VK_UP: 
                snake_movX = 0; snake_movY = -5; // Move para cima
                break; 
            case KeyEvent.VK_DOWN: 
                snake_movX = 0; snake_movY = 5; // Move para baixo
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

    
}
