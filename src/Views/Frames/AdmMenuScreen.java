package Views.Frames;

import Controllers.UserController;
import Models.Entity.User;
import Views.Components.TextPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;

public class AdmMenuScreen extends JFrame {
    private List<User> userList;
    private JButton btEdit;
    private JButton btDelete;
    private JPanel userPanel;

    public AdmMenuScreen() {
        this.setTitle("Menu do Administrador");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel btBackPanel = new JPanel(); // Painel para alinhar btBack à esquerda
        btBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinha à esquerda
        btBackPanel.setBackground(new Color(37, 38, 37));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); // Adicionar BoxLayout
                
        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS)); // Adicionar BoxLayout
        userPanel.setBackground(new Color(37, 38, 37));
        userPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton btBack = new JButton("Voltar");
        TextPanel admTitle = new TextPanel("Menu do ADM");

        btBackPanel.add(btBack);

        topPanel.add(btBackPanel);
        topPanel.add(admTitle);

        JScrollPane scrollPane = new JScrollPane(userPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setVisible(true);

        refreshUserList(); // Chamar refreshUserList após inicializar userPanel

        btBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdmMenuScreen.this.setVisible(false);
                new MainMenu();
            }
        });
    }

    private void onClickDelete(User user) {
        UserController userC = new UserController();
        try {
            Long id = user.getId();
            userC.delete(id);
            JOptionPane.showMessageDialog(this, "Usuário deletado!");
            refreshUserList();
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

    private void refreshUserList() {
        // Atualizar a lista de usuários
        userList = new UserController().listUsers();

        // Limpar o painel de usuários
        userPanel.removeAll();

        // Adicionar o rótulo "Usuários cadastrados:"
        JLabel lbRegistered = new JLabel("Usuários cadastrados:");
        lbRegistered.setForeground(Color.WHITE);
        lbRegistered.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.add(lbRegistered);

        for (User currentUser : userList) {
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
                    EditScreen edit = new EditScreen();
                }
            });

            btDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onClickDelete(currentUser);
                }
            });

        }

        // Revalidar e repintar o painel de usuários
        userPanel.revalidate();
        userPanel.repaint();
    }
}
