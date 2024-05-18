package Views;
import Controllers.UserController;
import Models.Entity.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.swing.*;

public class Menu extends JFrame{
    private JTextField tfUser;
    private List<User> userList;

    public Menu(){
        userList = new UserController().listUsers();
        this.setTitle("Cadastro de usuário");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        JPanel addPanel = new JPanel();
        JPanel userPanel = new JPanel();
        JLabel lbUser = new JLabel();
        tfUser = new JTextField("", 20);
        JButton btAdd = new JButton("Adicionar"); 
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        addPanel.add(lbUser);
        addPanel.add(tfUser);
        addPanel.add(btAdd);

        for (User currentUser : userList) {
            JLabel lbUsername = new JLabel();
            lbUsername.setText(currentUser.getUsername());
            userPanel.add(lbUsername); 
        }

        mainPanel.add(addPanel);
        mainPanel.add(userPanel);
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

        btAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                onClickRegister();         
            }
        });
    }
    
    private void onClickRegister() {
        UserController cc = new UserController();
        try {
            cc.register(tfUser.getText());
            JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
            clearFields();
            userList = new UserController().listUsers();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
				"Nao foi possivel salvar usuário!" + 
				e.getLocalizedMessage()
			);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, 
				"Data possui formato inválido!n" + 
				e.getLocalizedMessage()
		);
        }
    }

    private void clearFields() {
        tfUser.setText("");
    }
}
