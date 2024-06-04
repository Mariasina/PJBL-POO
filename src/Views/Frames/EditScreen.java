package Views.Frames;

import javax.swing.*;
import java.awt.*;

import Models.Entity.User;
import Views.Components.TextPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditScreen extends JFrame{
    private JTextField tfUser;
    
    public EditScreen(User user){
        this.setTitle("Edição de usuário");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel(new BorderLayout()); // Layout para botões
        JPanel btSavePanel = new JPanel(); // Painel para centralizar btAdd
        JPanel btBackPanel = new JPanel(); // Painel para alinhar btBack à esquerda
        JPanel formPanel = new JPanel(); // Painel para alinhar os campos do formulário à esquerda

        // Componentes de texto e botões
        JLabel lbUser = new JLabel("Username:");
        tfUser = new JTextField("", 20);
        JButton btSave = new JButton("Salvar");
        JButton btBack = new JButton("Voltar");
        TextPanel title = new TextPanel("Edição");

        tfUser.setText(user.getUsername());

        lbUser.setForeground(Color.WHITE);

        // Configuração do painel de botões
        btSavePanel.setLayout(new BoxLayout(btSavePanel, BoxLayout.Y_AXIS));
        btBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinha à esquerda

        // Adicionar botões aos seus respectivos painéis
        btSave.setAlignmentX(Component.CENTER_ALIGNMENT);
        btSavePanel.add(btSave);
        btSavePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 70, 10));
        btSavePanel.setBackground(new Color(37, 38, 37));

        btBackPanel.add(btBack);
        btBackPanel.setBackground(new Color(37, 38, 37));

        buttonsPanel.add(btSavePanel, BorderLayout.CENTER);

        // Configuração do painel do formulário para alinhar à esquerda
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lbUser, gbc);

        gbc.gridx = 1;
        formPanel.add(tfUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.setBackground(new Color(37, 38, 37));

        // Layout e configurações do painel de adição
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(37, 38, 37));

        mainPanel.add(btBackPanel);
        mainPanel.add(title);
        mainPanel.add(formPanel);
        mainPanel.add(buttonsPanel);

        // Adicionar espaçamento ao painel de botões
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adicionar painéis ao painel principal


        // Adicionar painel principal ao frame
        this.add(mainPanel);
        this.setVisible(true);

        // ActionListener para o botão Voltar
        btBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditScreen.this.setVisible(false);
                new MainMenu();
            }
        });

        this.add(mainPanel);
        this.pack();
    }
}
