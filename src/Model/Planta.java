package Model;

public class Planta {
   private TipoPlanta tipo;
   private int diasCrescimento;

    public Planta(TipoPlanta tipo) {
        this.tipo = tipo;
        this.diasCrescimento = 0;
    }

    public TipoPlanta getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlanta tipo) {
        this.tipo = tipo;
    }

    public int getDiasCrescimento() {
        return diasCrescimento;
    }

    public void setDiasCrescimento(int diasCrescimento) {
        this.diasCrescimento = diasCrescimento;
    }

    @Override
    public String toString() {
        return "Planta{" +
                "tipo=" + tipo +
                ", diasCrescimento=" + diasCrescimento +
                '}';
    }
}
