package host.julio.calc.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Comandos {
    private static final Comandos instancia = new Comandos();
    private static final Map<String, TipoComando> comandosMapper = new HashMap<>();
    private static final Memoria memoria = Memoria.getInstancia();

    private Comandos() {}

    public static Comandos getInstancia() {
        return instancia;
    }

    // Usados na detecção dos tipos de comandos
    static {
        comandosMapper.put("AC", TipoComando.ZERAR);
        comandosMapper.put("⌫", TipoComando.RETROCEDER);
        comandosMapper.put("=", TipoComando.IGUAL);
        comandosMapper.put(",", TipoComando.VIRGULA);
        comandosMapper.put("±", TipoComando.INVERTER_SINAL);

        comandosMapper.put("+", TipoComando.SOMA);
        comandosMapper.put("-", TipoComando.SUBTRACAO);
        comandosMapper.put("÷", TipoComando.DIVISAO);
        comandosMapper.put("×", TipoComando.MULTIPLICACAO);
        comandosMapper.put("%", TipoComando.MODULO);
        comandosMapper.put("³√\uD835\uDC65", TipoComando.RAIZ_CUBICA);
        comandosMapper.put("\uD835\uDC65²", TipoComando.QUADRADO);
        comandosMapper.put("²√\uD835\uDC65", TipoComando.RAIZ_QUADRADA);
    }

    // Usados para decidir as ações após detectar os tipos
    public Map<TipoComando, Consumer<String>> acoes(String texto) {
        Map<TipoComando, Consumer<String>> acoesMapper = new HashMap<>();
        acoesMapper.put(TipoComando.ZERAR, txt -> Util.getInstancia().resetarCalculadora(memoria));
        acoesMapper.put(TipoComando.VIRGULA, txt -> Util.getInstancia().addVirgula(memoria));
        acoesMapper.put(TipoComando.INVERTER_SINAL, txt -> Util.getInstancia().inverterSinal(memoria));
        acoesMapper.put(TipoComando.RETROCEDER, txt -> Util.getInstancia().retroceder(memoria));
        acoesMapper.put(TipoComando.NUMERO, txt -> Util.getInstancia().processarNumeros(memoria, texto));

        return acoesMapper;
    }

    public TipoComando getTipoComando(String comando) {
        return comandosMapper.get(comando);
    }

}
