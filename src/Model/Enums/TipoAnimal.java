package Model.Enums;

import Model.Armazenavel;

public enum TipoAnimal implements Armazenavel {

    GALINHA ("Galinha",  800,  "Ovo",   100,CategoriaConstrucao.GALINHEIRO),
    OVELHA  ("Ovelha",  8000,  "Lã",    500,CategoriaConstrucao.CELEIRO),
    PORCO   ("Porco",   16000, "Trufa", 900,CategoriaConstrucao.CELEIRO),
    VACA    ("Vaca",    1500,  "Leite", 250,CategoriaConstrucao.CELEIRO);

    private final String nomeAnimal;
    private final int precoCompra;
    private final String drop;
    private final int valorDrop;
    private final CategoriaConstrucao construcaoCompativel;

    TipoAnimal(String nomeAnimal, int precoCompra, String drop, int valorDrop, CategoriaConstrucao construcaoCompativel) {
        this.nomeAnimal = nomeAnimal;
        this.precoCompra = precoCompra;
        this.drop = drop;
        this.valorDrop = valorDrop;
        this.construcaoCompativel = construcaoCompativel;
    }

    /**
     * Nome do animal (ex: "Galinha"), usado ao comprar no Rancho e ao
     * listar os animais da fazenda.
     */
    public String getNomeAnimal() {
        return nomeAnimal;
    }

    /**
     * Nome exibido quando este TipoAnimal é tratado como Armazenavel,
     * ou seja, na hora de vender o drop no Armazém. Retorna o nome do
     * drop (ex: "Ovo"), não o nome do animal.
     */
    @Override
    public String getNome() {
        return drop;
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