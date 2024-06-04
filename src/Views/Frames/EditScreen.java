package Views.Frames;

import Models.Entity.User;
import Models.Exceptions.InvalidEntityException;
import Views.Components.TextPanel;
import Controllers.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class EditScreen extends JFrame{
    private JTextField tfUser;
    private User user;
    
    public EditScreen(User user){
        this.user = user;
        this.setTitle("Edição de usuário");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600, 600);
        
        // Configuração dos Painéis 
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(37, 38, 37));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel buttonsPanel = new JPanel(new BorderLayout()); 
        JPanel btSavePanel = new JPanel();
        btSavePanel.setLayout(new BoxLayout(btSavePanel, BoxLayout.Y_AXIS));
        btSavePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 70, 10));
        btSavePanel.setBackground(new Color(37, 38, 37));
        JPanel btBackPanel = new JPanel();
        btBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        btBackPanel.setBackground(new Color(37, 38, 37));
        JPanel formPanel = new JPanel(); 
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(37, 38, 37));        
        
        // Configuração dos demais elementos 
        JLabel lbUser = new JLabel("Username:");
        lbUser.setForeground(Color.WHITE);
        tfUser = new JTextField("", 20);
        tfUser.setText(user.getUsername());
        JButton btSave = new JButton("Salvar");
        btSave.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btBack = new JButton("Voltar");
        TextPanel title = new TextPanel("Edição");
        
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

        // Adicionando elementos em seus respectivos painéis
        btSavePanel.add(btSave);

        btBackPanel.add(btBack);

        buttonsPanel.add(btSavePanel, BorderLayout.CENTER);

        mainPanel.add(btBackPanel);
        mainPanel.add(title);
        mainPanel.add(formPanel);
        mainPanel.add(buttonsPanel);

        this.add(mainPanel);
        this.setVisible(true);

        // Actions de cada botão
        btBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditScreen.this.setVisible(false);
                new MainMenuScreen();
            }
        });

        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickAlter();
                EditScreen.this.setVisible(false);
                new AdmMenuScreen();
            }
        });

        this.add(mainPanel);
    }

    //Função para editar nome de usuário assim que o botão alvar for clicado
    private void onClickAlter() {
        UserController cc = new UserController();
       
		long id = user.getId();
		
        try {
            cc.alter(id, tfUser.getText());
            JOptionPane.showMessageDialog(this, "Usuário alterado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Nao foi possivel alterar usuário!n" + e.getLocalizedMessage());
        } catch (InvalidEntityException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
