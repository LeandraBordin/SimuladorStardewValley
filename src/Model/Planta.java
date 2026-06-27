package Model;

import Model.Enums.TipoPlanta;

import java.io.Serializable;

public class Planta implements Serializable,Armazenavel  {
    private TipoPlanta tipo;
    private int diasCrescimento;
    private boolean regenerando;

    public Planta(TipoPlanta tipo) {
        this.tipo = tipo;
        this.diasCrescimento = 0;
        this.regenerando = false;
    }

    // Continua igual — conta dias passados
    public void passarDiaCrescimento() {
        diasCrescimento++;
    }

    public boolean prontoParaColheita() {
        int limite = regenerando
                ? tipo.getDiasRecresce()
                : tipo.getDiasCrescimento();
        return diasCrescimento >= limite;
    }
    public boolean colher() {
        if (!prontoParaColheita()) return false;

        if (tipo.temRecrescimento()) {
            diasCrescimento = 0;
            regenerando = true;
        }
        return true;
    }

    public boolean isRegenerando() { return regenerando; }

    public boolean podeDesplantar() {
        return !regenerando;
    }

    public TipoPlanta getTipo() { return tipo; }
    public void setTipo(TipoPlanta tipo) { this.tipo = tipo; }
    public int getDiasCrescimento() { return diasCrescimento; }
    public void setDiasCrescimento(int diasCrescimento) { this.diasCrescimento = diasCrescimento; }

    @Override
    public String getNome() { return tipo.getNome(); }

    @Override
    public int getValorVenda() { return tipo.getPrecoVenda(); }

    @Override
    public String toString() {
        return "Planta{tipo=" + tipo +
                ", diasCrescimento=" + diasCrescimento +
                ", regenerando=" + regenerando + '}';
    }
}
