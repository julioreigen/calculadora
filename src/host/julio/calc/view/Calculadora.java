package host.julio.calc.view;

import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {
    public Calculadora() {
        organizarLayout();

        setSize(330, 460);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void organizarLayout() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        Display display = new Display();
        display.setPreferredSize(new Dimension(331, 90));
        add(display, BorderLayout.NORTH);

        Teclado teclado = new Teclado();
        add(teclado, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
