package Model.Enums;

public enum TipoConstrucao {
    CELEIRO("Celeiro", 4, 6000),
    CELEIRO_GRANDE("Celeiro Grande",8,12000),
    CELEIRO_LUXO("Celeiro de Luxo",12,25000),
    GALINHEIRO("Galinheiro",4,4000),
    GALINHEIRO_GRANDE("Galinheiro Grande",8,10000),
    GALINHEIRO_LUXO("Galinheiro de Luxo",12,20000);


    String nome;
    int capacidade;
    int valor;

    TipoConstrucao(String nome, int capacidade, int valor) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.valor = valor;
    }
    
    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getValor() {
        return valor;
    }
}
