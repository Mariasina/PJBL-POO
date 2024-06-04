package Views.Frames;

import javax.swing.*;
import Controllers.ScoreController;
import Models.Entity.Score;
import java.awt.*;
import Views.Components.TextPanel;
import java.util.List;

public class ScoreboardScreen extends JFrame {
    private List<Score> scoreList;

    public ScoreboardScreen() {
        scoreList = new ScoreController().listScores();

        this.setTitle("Scoreboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600, 600);

        // Configuração dos Painéis 
        JPanel btBackPanel = new JPanel(); 
        btBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JPanel scorePanel = new JPanel(new GridBagLayout());
        scorePanel.setBackground(new Color(37, 38, 37));

        // Configuração dos demais elementos 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 30, 5, 30);  
        TextPanel scoreboard = new TextPanel("Scoreboard");
        JButton btBack = new JButton("Voltar");
        JLabel lbUserTitle = new JLabel("Usuário:");
        lbUserTitle.setForeground(Color.WHITE);
        JLabel lbScoreTitle = new JLabel("Score:");
        lbScoreTitle.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        scorePanel.add(lbUserTitle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        scorePanel.add(lbScoreTitle, gbc);

        int row = 1;
        for (Score currentScore : scoreList) {
            JLabel lbUsername = new JLabel(currentScore.getIdUser().getUsername());
            lbUsername.setForeground(Color.WHITE);
            JLabel lbScore = new JLabel(String.valueOf(currentScore.getValue()));
            lbScore.setForeground(Color.WHITE);

            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.anchor = GridBagConstraints.WEST;
            scorePanel.add(lbUsername, gbc);

            gbc.gridx = 1;
            gbc.gridy = row;
            gbc.anchor = GridBagConstraints.EAST;
            scorePanel.add(lbScore, gbc);

            row++;
        }

        btBackPanel.add(btBack);
        btBackPanel.setBackground(new Color(37, 38, 37));

        topPanel.add(btBackPanel);
        topPanel.add(scoreboard);

        JScrollPane scrollPane = new JScrollPane(scorePanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); 

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(mainPanel);

   
        btBack.addActionListener(e -> {
            ScoreboardScreen.this.setVisible(false);
            new MainMenuScreen(); 
        });
    }
}
