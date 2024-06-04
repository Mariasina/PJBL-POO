package Views.Frames;

import Controllers.UserController;
import Models.Entity.User;
import Models.Exceptions.InvalidEntityException;
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
    
    public LoginScreen() {
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        
        // Configuração dos Painéis 
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel addPanel = new JPanel();
        JPanel buttonsPanel = new JPanel(new BorderLayout());
        JPanel btAddPanel = new JPanel(); 
        btAddPanel.setLayout(new BoxLayout(btAddPanel, BoxLayout.Y_AXIS));
        btAddPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 70, 10));
        btAddPanel.setBackground(new Color(37, 38, 37));
        JPanel btBackPanel = new JPanel();
        btBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        btBackPanel.setBackground(new Color(37, 38, 37));
        JPanel formPanel = new JPanel(); 
        
        // Configuração dos demais elementos 
        JLabel lbUser = new JLabel("Username:");
        lbUser.setForeground(Color.WHITE);
        tfUser = new JTextField("", 20);
        JLabel lbPassword = new JLabel("Senha:");
        lbPassword.setForeground(Color.WHITE);
        tfPassword = new JTextField("", 20);
        JButton btLogin = new JButton("Entrar");
        btLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btBack = new JButton("Voltar");
        TextPanel title = new TextPanel("Login");
        JLabel lbRegister = new JLabel("Ainda não possuí um usuário?");
        lbRegister.setForeground(Color.WHITE);
        lbRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btRegister = new JButton("Cadastrar");
        btRegister.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adicionar botões aos seus respectivos painéis
        btAddPanel.add(btLogin);

        btBackPanel.add(btBack);

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
        addPanel.add(lbRegister);
        addPanel.add(btRegister);

        addPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(addPanel, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setVisible(true);

        // ActionListener para o botão Voltar
        btBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen.this.setVisible(false);
                new MainMenuScreen();
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
        }  catch (InvalidEntityException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }    

    private void clearFields() {
        tfPassword.setText("");
    }
}
