package Views.Frames;

import Controllers.UserController;
import Models.Entity.User;
import Views.Components.TextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class RegisterScreen extends JFrame {
    private JTextField tfUser;
    private JTextField tfPassword;
    private List<User> userList;
    private JPanel userPanel;

    public RegisterScreen() {
        userList = new UserController().listUsers();
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);

        // Painéis principais
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel addPanel = new JPanel();
        userPanel = new JPanel();
        JPanel buttonsPanel = new JPanel(new BorderLayout()); // Layout para botões
        JPanel btAddPanel = new JPanel(); // Painel para centralizar btAdd
        JPanel btBackPanel = new JPanel(); // Painel para alinhar btBack à esquerda
        JPanel formPanel = new JPanel(); // Painel para alinhar os campos do formulário à esquerda

        // Componentes de texto e botões
        JLabel lbUser = new JLabel("Username:");
        tfUser = new JTextField("", 20);
        JLabel lbPassword = new JLabel("Senha:");
        tfPassword = new JTextField("", 20);
        JButton btAdd = new JButton("Adicionar");
        JButton btBack = new JButton("Voltar");
        JLabel lbRegistered = new JLabel("Usuários cadastrados:");
        TextPanel title = new TextPanel("Login");

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

        // Layout e configurações do painel de adição
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.setBackground(new Color(37, 38, 37));

        addPanel.add(btBackPanel);
        addPanel.add(title);
        addPanel.add(formPanel);
        addPanel.add(buttonsPanel);

        // Configurações do painel de usuários registrados
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.add(lbRegistered);

        // Adicionar usuários ao painel de usuários registrados
        for (User currentUser : userList) {
            JLabel lbUsername = new JLabel();
            lbUsername.setText(currentUser.getUsername());
            userPanel.add(lbUsername);
        }

        // Adicionar espaçamento ao painel de botões
        addPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        userPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adicionar painéis ao painel principal
        mainPanel.add(addPanel, BorderLayout.NORTH);
        mainPanel.add(userPanel, BorderLayout.SOUTH);

        // Adicionar painel principal ao frame
        this.add(mainPanel);
        this.setVisible(true);

        // ActionListener para o botão Voltar
        btBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterScreen.this.setVisible(false);
                new MainMenu();
            }
        });

        // ActionListener para o botão Adicionar
        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickRegister();
                new GameScreen();
            }
        });
    }

    private void onClickRegister() {
        UserController cc = new UserController();
        try {
            cc.register(tfUser.getText(), tfPassword.getText());
            JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
            clearFields();
            userList = new UserController().listUsers();
            refreshUserList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Nao foi possivel salvar usuário!" +
                e.getLocalizedMessage()
            );
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                "Data possui formato inválido!\n" +
                e.getLocalizedMessage()
            );
        }
    }

    private void clearFields() {
        tfUser.setText("");
        tfPassword.setText("");
    }

    private void refreshUserList() {
        userPanel.removeAll();
        userPanel.add(new JLabel("Usuários cadastrados:"));
        for (User currentUser : userList) {
            JLabel lbUsername = new JLabel(currentUser.getUsername());
            userPanel.add(lbUsername);
        }
        userPanel.revalidate();
        userPanel.repaint();
    }
}
