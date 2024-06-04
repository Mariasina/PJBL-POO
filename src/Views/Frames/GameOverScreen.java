package Views.Frames;

import Controllers.ScoreController;
import Models.Entity.User;
import Models.Exceptions.InvalidEntityException;
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
        this.setSize(600, 600);

        // Configuração dos Painéis 
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel =  new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 70, 10));
        buttonsPanel.setBackground(new Color(37, 38, 37));

        // Configuração dos demais elementos 
        TextPanel gameOver = new TextPanel("Game Over");
        TextPanel scoreText = new TextPanel("Score: " + String.valueOf(score));
        JButton btEnd = new JButton("Menu"); 
        btEnd.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btReplay = new JButton("Jogar novamente"); 
        btReplay.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adicionando elementos em seus respectivos painéis
        buttonsPanel.add(btEnd);
        buttonsPanel.add(btReplay);
        
        mainPanel.add(gameOver, BorderLayout.NORTH);
        mainPanel.add(scoreText, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        this.add(mainPanel);

        // Actions de cada botão
        btEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameOverScreen.this.setVisible(false);
                new MainMenuScreen();
            }
        });

        btReplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameOverScreen.this.setVisible(false);
                new GameScreen(user);
            }
        });

        onClickRegister();  
    }

    //Função para cadastrar o Score do usuário que estava jogando assim que a tela for carregada
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
        } catch (InvalidEntityException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
