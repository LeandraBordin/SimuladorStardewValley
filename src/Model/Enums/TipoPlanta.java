package Model.Enums;

public enum TipoPlanta {
    ABACAXI  ("Abacaxi",  Estacoes.VERAO,  14, 100, 150),
    BANANA   ("Banana",   Estacoes.VERAO,  28, 200, 150),
    CEREJA   ("Cereja",   Estacoes.PRIMAVERA, 28, 340, 210),
    LARANJA  ("Laranja",  Estacoes.VERAO,  28, 400, 160),
    LIMAO    ("Limão",    Estacoes.PRIMAVERA, 28, 200, 130),
    MACA     ("Maçã",     Estacoes.OUTONO, 28, 1000, 200),
    MAMAO    ("Mamão",    Estacoes.VERAO,  14, 80,  75),
    MANGA    ("Manga",    Estacoes.VERAO,  28, 270, 130),
    MELANCIA ("Melancia", Estacoes.VERAO,  9,  20,  250),
    MORANGO  ("Morango",  Estacoes.PRIMAVERA, 8, 100, 120),
    PESSEGO  ("Pêssego",  Estacoes.VERAO,  28, 600, 140),
    UVA      ("Uva",      Estacoes.VERAO,  10, 60,  80);
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

    public Estacoes getEstacao() {
        return estacao;
    }

    @Override
    public String toString() {
        return "TipoPlanta{" +
                "nome='" + nome + '\'' +
                ", estacao=" + estacao +
                ", diasCrescimento=" + diasCrescimento +
                ", precoCompra=" + precoCompra +
                ", precoVenda=" + precoVenda +
                '}';
    }
}