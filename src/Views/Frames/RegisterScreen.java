package Views.Frames;

import Controllers.UserController;
import Models.Exceptions.InvalidEntityException;
import Views.Components.TextPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.*;

public class RegisterScreen extends JFrame {
    private JTextField tfUser;
    private JTextField tfPassword;

    public RegisterScreen() {
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);

        // Configuração dos Painéis 
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel addPanel = new JPanel();
        JPanel buttonsPanel = new JPanel(new BorderLayout()); 
        JPanel btAddPanel = new JPanel(); 
        JPanel btBackPanel = new JPanel(); 
        JPanel formPanel = new JPanel(); 

        // Configuração dos demais elementos 
        JLabel lbUser = new JLabel("Username:");
        tfUser = new JTextField("", 20);
        JLabel lbPassword = new JLabel("Senha:");
        tfPassword = new JTextField("", 20);
        JButton btAdd = new JButton("Adicionar");
        JButton btBack = new JButton("Voltar");
        TextPanel title = new TextPanel("Cadastro");

        lbUser.setForeground(Color.WHITE);
        lbPassword.setForeground(Color.WHITE);

        // Configuração do painel de botões
        btAddPanel.setLayout(new BoxLayout(btAddPanel, BoxLayout.Y_AXIS));
        btBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinha à esquerda

        // Adicionar botões aos seus respectivos painéis
        btAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btAddPanel.add(btAdd);
        btAddPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 70, 10));
        btAddPanel.setBackground(new Color(37, 38, 37));

        btBackPanel.add(btBack);
        btBackPanel.setBackground(new Color(37, 38, 37));

        buttonsPanel.add(btAddPanel, BorderLayout.CENTER);

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
        formPanel.add(lbPassword, gbc);

        gbc.gridx = 1;
        formPanel.add(tfPassword, gbc);

        formPanel.setBackground(new Color(37, 38, 37));

        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.setBackground(new Color(37, 38, 37));

        addPanel.add(btBackPanel);
        addPanel.add(title);
        addPanel.add(formPanel);
        addPanel.add(buttonsPanel);

        addPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(addPanel, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setVisible(true);

        // ActionListener para o botão Voltar
        btBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterScreen.this.setVisible(false);
                new MainMenuScreen();
            }
        });

        // ActionListener para o botão Adicionar
        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickRegister();
            }
        });
    }

    private void onClickRegister() {
        UserController cc = new UserController();
        try {
            cc.register(tfUser.getText(), tfPassword.getText());
            JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
            clearFields();
        } catch (SQLException e) {
            if (e.getMessage().contains("Username already exists")) {
                JOptionPane.showMessageDialog(this, "Nome de usuário já existe. Escolha um nome diferente.");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Não foi possível salvar usuário! " + e.getLocalizedMessage()
                );
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                "Data possui formato inválido!\n" + e.getLocalizedMessage()
            );
        } catch (InvalidEntityException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void clearFields() {
        tfUser.setText("");
        tfPassword.setText("");
    }
}
