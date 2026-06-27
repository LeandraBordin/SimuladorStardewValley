package Model.Enums;

public enum TipoConstrucao {
    CELEIRO("Celeiro", 4, 6000,CategoriaConstrucao.CELEIRO),
    CELEIRO_GRANDE("Celeiro Grande",8,12000,CategoriaConstrucao.CELEIRO ),
    CELEIRO_LUXO("Celeiro de Luxo",12,25000,CategoriaConstrucao.CELEIRO ),
    GALINHEIRO("Galinheiro",4,4000,CategoriaConstrucao.GALINHEIRO ),
    GALINHEIRO_GRANDE("Galinheiro Grande",8,10000,CategoriaConstrucao.GALINHEIRO ),
    GALINHEIRO_LUXO("Galinheiro de Luxo",12,20000,CategoriaConstrucao.GALINHEIRO );


    private final String nome;
    private final int capacidade;
    private final int valor;
    private final CategoriaConstrucao categoriaCompativel;

    TipoConstrucao(String nome, int capacidade, int valor, CategoriaConstrucao categoriaCompativel) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.valor = valor;
        this.categoriaCompativel = categoriaCompativel;
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

    public CategoriaConstrucao getCategoriaCompativel() {
        return categoriaCompativel;
    }
}
