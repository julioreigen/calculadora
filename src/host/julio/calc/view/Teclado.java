package host.julio.calc.view;

import host.julio.calc.model.Memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Teclado extends JPanel implements ActionListener {

    private final Color COR_CINZA_ESCURO = new Color(40, 40, 40);
    private final Color COR_CINZA_CLARO = new Color(56, 56, 56);
    private final Color COR_ROXO_CLARO = new Color(114, 32, 176);

    public Teclado() {
        setBackground(Color.BLACK);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        setLayout(layout);

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        // linha 1
        adicionarBotao("%", COR_CINZA_ESCURO, c, 0, 0);
        adicionarBotao("AC", COR_CINZA_ESCURO, c, 1, 0);
        c.gridwidth = 2;
        adicionarBotao("⌫", COR_CINZA_ESCURO, c, 2, 0);
        c.gridwidth = 1;
        // linha 2
        adicionarBotao("\uD835\uDC65²", COR_CINZA_ESCURO, c, 0, 1);
        adicionarBotao("²√\uD835\uDC65", COR_CINZA_ESCURO, c, 1, 1);
        adicionarBotao("³√\uD835\uDC65", COR_CINZA_ESCURO, c, 2, 1);
        adicionarBotao("÷", COR_CINZA_ESCURO, c, 3, 1);
        // linha 3
        adicionarBotao("7", COR_CINZA_CLARO, c, 0, 2);
        adicionarBotao("8", COR_CINZA_CLARO, c, 1, 2);
        adicionarBotao("9", COR_CINZA_CLARO, c, 2, 2);
        adicionarBotao("×", COR_CINZA_ESCURO, c, 3, 2);
        // linha 4
        adicionarBotao("4", COR_CINZA_CLARO, c, 0, 3);
        adicionarBotao("5", COR_CINZA_CLARO, c, 1, 3);
        adicionarBotao("6", COR_CINZA_CLARO, c, 2, 3);
        adicionarBotao("-", COR_CINZA_ESCURO, c, 3, 3);
        // linha 5
        adicionarBotao("1", COR_CINZA_CLARO, c, 0, 4);
        adicionarBotao("2", COR_CINZA_CLARO, c, 1, 4);
        adicionarBotao("3", COR_CINZA_CLARO, c, 2, 4);
        adicionarBotao("+", COR_CINZA_ESCURO, c, 3, 4);
        // linha 6
        adicionarBotao("±", COR_CINZA_ESCURO, c, 0, 5);
        adicionarBotao("0", COR_CINZA_CLARO, c, 1, 5);
        adicionarBotao(",", COR_CINZA_CLARO, c, 2, 5);
        adicionarBotao("=", COR_ROXO_CLARO, c, 3, 5);
    }

    private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
        c.gridx = x;
        c.gridy = y;
        Botao botao = new Botao(texto, cor);
        botao.addActionListener(this);
        add(botao, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton botao) {
            Memoria.getInstancia().processarComando(botao.getText());
        }
    }
}
