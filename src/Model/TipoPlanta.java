package Model;

public enum TipoPlanta {
    UVA ("Uva", Estacoes.VERAO,10 , 60,80);
//    ABACAXI ("Abacaxi", 2),
//    BANANA ("Banana", 2),
//    CEREJA ("Cereja", 5),
//    LARANJA ("Laranja", 2),
//    LIMAO ("Limão", 1),
//    MACA ("Maçã", 5),
//    MAMAO ("Mamão", 1),
//    MANGA ("Manga", 5),
//    MELANCIA ("Melancia", 2),
//    MORANGO ("Morango", 1),
//    PESSEGO ("Pêssego", 5),
    private final String nome;
    private final Estacoes estacao;
    private final int diasCrescimento;
    private final int precoCompra;
    private final int precoVenda;

    TipoPlanta(String nome, Estacoes estacao, int diasCrescimento, int precoCompra, int precoVenda) {
        this.nome = nome;
        this.estacao = estacao;
        this.diasCrescimento = diasCrescimento;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }

    public String getNome() {
        return nome;
    }

    public int getDiasCrescimento() {
        return diasCrescimento;
    }

    public int getPrecoCompra() {
        return precoCompra;
    }

    public int getPrecoVenda() {
        return precoVenda;
    }
}