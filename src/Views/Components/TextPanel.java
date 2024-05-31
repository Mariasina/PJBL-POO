package Views.Components;

import java.awt.*;
import javax.swing.*;

public class TextPanel extends JPanel {
    private String text;

    public TextPanel(String text) {
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(new Color(37, 38, 37));
        this.text = text;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // por motivos de compatibilidade com a AWT
        g2d.setColor(Color.GREEN); // define a cor em uso
        g2d.setFont(new Font("Verdana", Font.BOLD, 30)); // define a fonte em uso
    
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.drawString(text, x, y);
    }

    public void setText(String text) {
        this.text = text;
        repaint(); // Solicita a repintura do componente para refletir o novo texto
    }
}
