package Views.Frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import Models.Entity.User;

public class AdmMenuScreen extends JFrame{
    private List<User> userList;


    public AdmMenuScreen(){
        this.setTitle("Menu do Administrador");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 500);

        JPanel mainPanel = new JPanel();
        JPanel userPanel = new JPanel();

        JButton btAdd = new JButton("Adicionar usuário");
        JButton btEdit = new JButton("Editar usuário");
        JButton btDelete = new JButton("Excluir usuário");
        JButton btSearch = new JButton("Procurar usuário");
        JButton btPlay = new JButton("Jogar jogo");
        JLabel lbRegistered = new JLabel("Usuários cadastrados:");

        mainPanel.add(btAdd);
        mainPanel.add(btEdit);
        mainPanel.add(btDelete);
        mainPanel.add(btSearch);
        mainPanel.add(btPlay);

        userPanel.add(lbRegistered);

        // for (User currentUser : userList) {
        //     JLabel lbUsername = new JLabel();
        //     lbUsername.setText(currentUser.getUsername());
        //     userPanel.add(lbUsername); 
        // }

        mainPanel.add(userPanel);

        btAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                AdmMenuScreen.this.setVisible(false);
                RegisterScreen register = new RegisterScreen();
            }
        });

        btEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                AdmMenuScreen.this.setVisible(false);
                EditScreen edit = new EditScreen();
            }
        });

        btDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                AdmMenuScreen.this.setVisible(false);
                DeleteScreen delete = new DeleteScreen();
            }
        });

        btPlay.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                AdmMenuScreen.this.setVisible(false);
                
            }
        });

        this.add(mainPanel);
        this.pack();
        


    }
    
}
