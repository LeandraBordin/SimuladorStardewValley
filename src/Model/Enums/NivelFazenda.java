package Model.Enums;

public enum NivelFazenda {
    NIVEL_1 (1, 5,  5,0),
    NIVEL_2 (2, 8,  8,10000),
    NIVEL_3 (3, 12, 12,16000),
    NIVEL_4 (4, 16, 16,24000),
    NIVEL_5 (5, 20, 20,30000);

    private int nivel;
    private int largura;
    private int altura;
    private int valorUpgrade;

    NivelFazenda(int nivel, int largura, int altura, int valorUpgrade) {
        this.nivel = nivel;
        this.largura = largura;
        this.altura = altura;
        this.valorUpgrade = valorUpgrade;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getValorUpgrade() {
        return valorUpgrade;
    }

    public void setValorUpgrade(int valorUpgrade) {
        this.valorUpgrade = valorUpgrade;
    }

    public int getCapacidade(){
        return largura * altura;
    }
}
