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
import java.text.SimpleDateFormat;
import java.util.List;

public class LoginScreen extends JFrame {
    private JTextField tfUser;
    private JTextField tfPassword;
    private JPanel userPanel;

    public LoginScreen() {
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);

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
        JButton btLogin = new JButton("Entrar");
        JButton btBack = new JButton("Voltar");
        JLabel lbRegistered = new JLabel("Usuários cadastrados:");
        TextPanel title = new TextPanel("Login");
        JLabel lbRegister = new JLabel("Ainda não possuí um usuário?");
        JButton btRegister = new JButton("Cadastrar");

        lbUser.setForeground(Color.WHITE);
        lbPassword.setForeground(Color.WHITE);
        lbRegister.setForeground(Color.WHITE);

        // Configuração do painel de botões
        btAddPanel.setLayout(new BoxLayout(btAddPanel, BoxLayout.Y_AXIS));
        btBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinha à esquerda

        // Adicionar botões aos seus respectivos painéis
        btLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btRegister.setAlignmentX(Component.CENTER_ALIGNMENT);

        btAddPanel.add(btLogin);
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
        addPanel.add(lbRegister);
        addPanel.add(btRegister);

        // Configurações do painel de usuários registrados
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.add(lbRegistered);

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
                LoginScreen.this.setVisible(false);
                new MainMenu();
            }
        });

        // ActionListener para o botão Adicionar
        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickFind();
                
                
            }
        });

        btRegister.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                LoginScreen.this.setVisible(false);
                new RegisterScreen();
                
            }
        });
    }

    private void onClickFind() {
        UserController cc = new UserController();
        try {
            User c = cc.searchUsername(tfUser.getText());
            if (c == null) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado!");
                clearFields();
                return;
            }
            String pass = c.getUserPassword();
            if ("admin".equals(tfUser.getText()) && "admin123".equals(tfPassword.getText())) {
                LoginScreen.this.setVisible(false);
                new AdmMenuScreen();
            } else {
                if (pass.equals(tfPassword.getText())) {              
                    LoginScreen.this.setVisible(false);
                    new GameScreen(c);
                } else {
                    JOptionPane.showMessageDialog(this, "Senha incorreta!");
                    clearFields();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Ocorreu um erro, tente novamente!\n" + 
                e.getLocalizedMessage()
            );
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, 
                "Contato não localizado ou não existe!\n" + 
                e.getLocalizedMessage()
            );
        }
    }
    
    

    private void clearFields() {
        tfPassword.setText("");
    }
}
