package Views.Frames;

import Views.Components.TextPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu extends JFrame{

    public MainMenu(){
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel();
        TextPanel title = new TextPanel("SNAKE");
        JButton btPlay = new JButton("Jogar");
        JButton btScore = new JButton("Scoreboard");

        mainPanel.setBackground(new Color(37, 38, 37));

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setAlignmentY(CENTER_ALIGNMENT);
        buttonsPanel.setBackground(new Color(37, 38, 37));

        btPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
        btScore.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonsPanel.add(btPlay);
        buttonsPanel.add(btScore);

        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 70, 10));
        
        mainPanel.add(title, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        this.add(mainPanel);

        btPlay.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                MainMenu.this.setVisible(false);
                LoginScreen login = new LoginScreen();
            }
        });

        btScore.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                MainMenu.this.setVisible(false);
                ScoreboardScreen scoreBoard = new ScoreboardScreen();
            }
        });
    }   

    
}
