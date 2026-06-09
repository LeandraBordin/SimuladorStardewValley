package Model.Enums;

public enum NivelFazenda {
    NIVEL_1 (1, 5,  5),
    NIVEL_2 (2, 8,  8),
    NIVEL_3 (3, 12, 12),
    NIVEL_4 (4, 16, 16),
    NIVEL_5 (5, 20, 20);



    private int nivel;
    private int largura;
    private int altura;

    NivelFazenda(int nivel, int largura, int altura) {
        setNivel(nivel);
        setLargura(largura);
        setAltura(altura);
    }

    public int getCapacidade(){
        return largura * altura;
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
}
