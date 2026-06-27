package Model.Enums;

import Model.Armazenavel;

public enum TipoAnimal implements Armazenavel {

    GALINHA ("Galinha",  800,  "Ovo",   100,CategoriaConstrucao.GALINHEIRO),
    OVELHA  ("Ovelha",  8000,  "Lã",    500,CategoriaConstrucao.CELEIRO),
    PORCO   ("Porco",   16000, "Trufa", 900,CategoriaConstrucao.CELEIRO),
    VACA    ("Vaca",    1500,  "Leite", 250,CategoriaConstrucao.CELEIRO);

    private final String nome;
    private final int precoCompra;
    private final String drop;
    private final int valorDrop;
    private final CategoriaConstrucao construcaoCompativel;

    TipoAnimal(String nome, int precoCompra, String drop, int valorDrop, CategoriaConstrucao construcaoCompativel) {
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.drop = drop;
        this.valorDrop = valorDrop;
        this.construcaoCompativel = construcaoCompativel;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int getValorVenda() {
        return valorDrop;
    }

    public int getPrecoCompra() {
        return precoCompra;
    }
    public String getDrop() {
        return drop;
    }
    public int getValorDrop() {
        return valorDrop;
    }

    public CategoriaConstrucao getConstrucaoCompativel() {
        return construcaoCompativel;
    }
}
