package Views.Frames;

import javax.swing.*;

import Controllers.UserController;
import Models.Entity.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.text.ParseException;

public class DeleteScreen extends JFrame{
    private JTextField tfUser;

    public DeleteScreen(){
        this.setTitle("Deleção de usuário");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JPanel mainPanel = new JPanel();
        JButton btDelete = new JButton("Deletar");
        JButton btBack = new JButton("Voltar");


        tfUser = new JTextField("", 20);

        mainPanel.add(btBack);
        mainPanel.add(tfUser);
        mainPanel.add(btDelete);

        btBack.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                DeleteScreen.this.setVisible(false);
                AdmMenuScreen adm = new AdmMenuScreen();
            }
        });

        btDelete.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onClickDelete();
                }
            }
        );

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

        
    }

    private void onClickDelete() {
        UserController userC = new UserController();
        try {
            User user = userC.searchUsername(tfUser.getText());
            Long id = user.getId();
            userC.delete(id);
            JOptionPane.showMessageDialog(this, "Usuário deletado!");
            clearFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
				"Ocorreu um erro, tente novamente!n" + 
				e.getLocalizedMessage()
			);
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(this, 
				"Usuário não localizdo ou não existe!n" + 
				e.getLocalizedMessage()
			);
        }
    }

    private void clearFields() {
        tfUser.setText("");
    }

}
