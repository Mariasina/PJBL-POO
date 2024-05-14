package views;
import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{
    private JFrame frame;
    private JButton button;
    private int count = 0;

    public Menu(String name){
        frame = new JFrame(name);
        frame.add(this);
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button = new JButton("Clique"); 
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g; // por motivos de compatibilidade com a AWT
        g2d.setColor(Color.GRAY); // define a cor em uso
        g2d.setFont(new Font("Verdana", Font.BOLD, 16)); // define a fonte em uso
        g2d.drawString("Contador: " + String.valueOf(count), 10, 30); // escreve uma string
    }
        
}
