package Views.Frames;

import javax.swing.*;

public class EditScreen extends JFrame{
    
    public EditScreen(){
        this.setTitle("Edição de usuário");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JPanel mainPanel = new JPanel();
        JTextField tfUser = new JTextField("", 20);
        JButton btEdit = new JButton("Editar");

        mainPanel.add(tfUser);
        mainPanel.add(btEdit);

        this.add(mainPanel);
        this.pack();
    }
}
