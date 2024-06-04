package Views.Frames;

import Controllers.UserController;
import Models.Entity.User;
import Views.Components.TextPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

public class AdmMenuScreen extends JFrame {
    private List<User> userList;
    private JButton btEdit;
    private JButton btDelete;
    private JPanel userPanel;
    private JTextField searchField;
    private JButton searchButton;

    public AdmMenuScreen() {
        this.setTitle("Menu do Administrador");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);

        // Configuração dos Painéis
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel btBackPanel = new JPanel(); 
        btBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        btBackPanel.setBackground(new Color(37, 38, 37));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); 
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBackground(new Color(37, 38, 37));
                
        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS)); 
        userPanel.setBackground(new Color(37, 38, 37));
        userPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(userPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Configuração dos demais elementos 
        JButton btBack = new JButton("Voltar");
        TextPanel admTitle = new TextPanel("Menu do ADM");        
        searchField = new JTextField(20);
        searchButton = new JButton("Buscar");

        // Adicionando elementos em seus respectivos painéis
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        btBackPanel.add(btBack);

        topPanel.add(btBackPanel);
        topPanel.add(admTitle);
        topPanel.add(searchPanel); 

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setVisible(true);

        refreshUserList(null); 

        // Actions de cada botão
        btBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdmMenuScreen.this.setVisible(false);
                new MainMenuScreen();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                refreshUserList(searchText);
            }
        });
    }

    // Função para deletar usuário ao clicar no botão de Deletar 
    private void onClickDelete(User user) {
        UserController userC = new UserController();
        try {
            Long id = user.getId();
            userC.delete(id);
            JOptionPane.showMessageDialog(this, "Usuário deletado!");
            refreshUserList(null); // Atualizar a lista de usuários após a exclusão
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Ocorreu um erro, tente novamente!\n" +
                e.getLocalizedMessage()
            );
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(this,
                "Usuário não localizado ou não existe!\n" +
                e.getLocalizedMessage()
            );
        }
    }

    // Função que da refresh na lista de usuários sempre que uma mudança é feita
    private void refreshUserList(String searchText) {
        userList = new UserController().listUsers();
    
        if (searchText != null && !searchText.trim().isEmpty()) {
            userList = userList.stream()
                .filter(user -> user.getUsername().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
        }

        userPanel.removeAll();
    
        JLabel lbRegistered = new JLabel("Usuários cadastrados:");
        lbRegistered.setForeground(Color.WHITE);
        lbRegistered.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.add(lbRegistered);
    
        if (userList.size() > 1) {
            for (int i = 1; i < userList.size(); i++) {
                User currentUser = userList.get(i);
    
                JPanel currentPanel = new JPanel(new BorderLayout());
                currentPanel.setBackground(new Color(37, 38, 37));
    
                JLabel lbUsername = new JLabel();
                lbUsername.setForeground(Color.WHITE);
                lbUsername.setText(currentUser.getUsername());
                lbUsername.setAlignmentX(Component.LEFT_ALIGNMENT);
    
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.setBackground(new Color(37, 38, 37));
    
                btEdit = new JButton("Editar");
                btDelete = new JButton("Excluir");
    
                buttonPanel.add(btEdit);
                buttonPanel.add(btDelete);
    
                currentPanel.add(lbUsername, BorderLayout.WEST);
                currentPanel.add(buttonPanel, BorderLayout.EAST);
                userPanel.add(currentPanel);
    
                btEdit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AdmMenuScreen.this.setVisible(false);
                        EditScreen edit = new EditScreen(currentUser);
                    }
                });
    
                btDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onClickDelete(currentUser);
                    }
                });
            }
        }
    
        userPanel.revalidate();
        userPanel.repaint();
    }
    
}
