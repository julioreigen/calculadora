package host.julio.calc.view;

import host.julio.calc.model.Memoria;
import host.julio.calc.model.MemoriaObserver;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel implements MemoriaObserver {
    private final JLabel label;
    public Display() {
        Memoria.getInstancia().adicionarObservador(this);

        setBackground(new Color(32, 32, 32));
        label = new JLabel(Memoria.getInstancia().getTextoAtual());
        label.setForeground(Color.WHITE);
        label.setFont(new Font("courier", Font.PLAIN, 26));

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));

        add(label);
    }

    @Override
    public void valorAlterado(String novoValor) {
        label.setText(novoValor);
    }

}
