package Views.Frames;

import Controllers.ScoreController;
import Models.Entity.User;
import Views.Components.TextPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class GameOverScreen extends JFrame {
    private int score;
    private User user;

    public GameOverScreen(User user, int score) {
        this.user = user;
        this.score = score;

        this.setTitle("Game Over");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel =  new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 70, 10));
        buttonsPanel.setBackground(new Color(37, 38, 37));

        TextPanel gameOver = new TextPanel("Game Over");
        TextPanel scoreText = new TextPanel("Score: " + String.valueOf(score));
        JButton btEnd = new JButton("Menu"); 
        JButton btReplay = new JButton("Jogar novamente"); 

        btEnd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btReplay.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonsPanel.add(btEnd);
        buttonsPanel.add(btReplay);
        
        mainPanel.add(gameOver, BorderLayout.NORTH);
        mainPanel.add(scoreText, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        this.add(mainPanel);

        btEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameOverScreen.this.setVisible(false);
                new MainMenu();
            }
        });

        btReplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameOverScreen.this.setVisible(false);
                new GameScreen(user);
            }
        });

        onClickRegister();  // Chame onClickRegister após definir user e score
    }

    private void onClickRegister() {
        ScoreController sc = new ScoreController();
        try {
            sc.register(score, user);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Nao foi possivel salvar usuário!\n" +
                e.getLocalizedMessage()
            );
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                "Data possui formato inválido!\n" +
                e.getLocalizedMessage()
            );
        }
    }
}
