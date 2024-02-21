package host.julio.calc.model;

public class Util {
    private static final Util instancia = new Util();

    private Util() {}

    public static Util getInstancia() {
        return instancia;
    }

    public void addVirgula(Memoria memoria) {
        String textoAtual = memoria.getTextoAtual();

        // Faz as verificações lógicas e se estiver tudo certo, adiciona a vírgula.
        if (!textoAtual.contains(",") || memoria.isSubstituir())
            memoria.setTextoAtual(memoria.getTextoAtual() + ",");
    }

    public void resetarCalculadora(Memoria memoria) {
        // Reseta toda a memória da calculadora.
        memoria.setTextoAtual("");
        memoria.setTextoBuffer("");
        memoria.setSubstituir(false);
        memoria.setUltimaOperacao(null);
    }

    public void inverterSinal(Memoria memoria) {
        var textoAtual = memoria.getTextoAtual();

        if (!textoAtual.isEmpty() && !textoAtual.equals("0")) {
            if (textoAtual.startsWith("-")) {
                memoria.setTextoAtual(textoAtual.substring(1));
                return;
            }
            memoria.setTextoAtual("-"+textoAtual);
        }
    }

    public void retroceder(Memoria memoria) {
        var textoAtual = memoria.getTextoAtual();

        if (!textoAtual.isEmpty() && !textoAtual.equals("0"))
            if (textoAtual.length() == 2 && textoAtual.contains("-"))
                memoria.setTextoAtual("");
            else
                memoria.setTextoAtual(textoAtual.substring(0, textoAtual.length() - 1));
    }

    public void processarNumeros(Memoria memoria,String texto) {
        var textoAtual = memoria.getTextoAtual();

        if (memoria.isSubstituir() || textoAtual.equals("0")) {
            memoria.setTextoAtual(texto);
            memoria.setSubstituir(false);
        } else {
            memoria.setTextoAtual(textoAtual + texto);
        }
    }

    public double resultadoOperacao(Memoria memoria, Double numeroBuffer) {
        double numeroAtual =
                Double.parseDouble(memoria.getTextoAtual().replace(",", "."));

        var ultimaOperacao = memoria.getUltimaOperacao();

        if (ultimaOperacao== TipoComando.SOMA)
            return numeroBuffer + numeroAtual;
        if (ultimaOperacao == TipoComando.DIVISAO)
            return numeroBuffer / numeroAtual;
        if (ultimaOperacao == TipoComando.MULTIPLICACAO)
            return numeroBuffer * numeroAtual;
        if (ultimaOperacao == TipoComando.SUBTRACAO)
            return numeroBuffer - numeroAtual;
        if (ultimaOperacao == TipoComando.RAIZ_CUBICA)
            return Math.cbrt(numeroAtual);
        if (ultimaOperacao == TipoComando.MODULO)
            return numeroBuffer % numeroAtual;
        if (ultimaOperacao == TipoComando.QUADRADO)
            return Math.pow(numeroAtual, 2);
        if (ultimaOperacao ==  TipoComando.RAIZ_QUADRADA)
            return Math.sqrt(numeroAtual);
        return 0;
    }

}
