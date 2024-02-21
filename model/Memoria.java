package host.julio.calc.model;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private static final Memoria instancia = new Memoria();
    private String textoAtual = "";
    private String textoBuffer = "";
    private boolean substituir = false;
    private TipoComando ultimaOperacao = null;

    private final List<MemoriaObserver> observers = new ArrayList<>();

    private Memoria() {
    }

    public static Memoria getInstancia() {
        return instancia;
    }

    public void adicionarObservador(MemoriaObserver o) {
        observers.add(o);
    }

    public String getTextoAtual() {
        if (textoAtual.isEmpty())
            return "0";

        return textoAtual;
    }

    public void processarComando(String texto) {
        TipoComando tipoComando = detectarTipoComando(texto);

        if (tipoComando == null)
            return;

        Comandos.getInstancia().acoes(texto).getOrDefault(tipoComando, t -> {
            substituir = true;
            textoAtual = obterResultadoOperacao();
            textoBuffer = getTextoAtual();
            ultimaOperacao = tipoComando;
        }).accept(texto);

        observers.forEach(o -> o.valorAlterado(getTextoAtual()));
    }

    private String obterResultadoOperacao() {
        if (ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL)
            return textoAtual;

        double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));

        double resultado = Util.getInstancia().resultadoOperacao(instancia, numeroBuffer);

        String texto = Double.toString(resultado).replace(".", ",");

        if (texto.endsWith(",0"))
            return texto.replace(",0", "");

        return texto;
    }

    private TipoComando detectarTipoComando(String texto) {
        if (textoAtual.isEmpty() && texto.equals("0"))
            return null;

        TipoComando tipoComando = Comandos.getInstancia().getTipoComando(texto);

        if (tipoComando != null)
            return tipoComando;

        try {
            Integer.parseInt(texto);
            return TipoComando.NUMERO;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean isSubstituir() {
        return substituir;
    }

    public TipoComando getUltimaOperacao() {
        return ultimaOperacao;
    }

    public void setTextoAtual(String textoAtual) {
        this.textoAtual = textoAtual;
    }

    public void setTextoBuffer(String textoBuffer) {
        this.textoBuffer = textoBuffer;
    }

    public void setSubstituir(boolean substituir) {
        this.substituir = substituir;
    }

    public void setUltimaOperacao(TipoComando ultimaOperacao) {
        this.ultimaOperacao = ultimaOperacao;
    }
}
