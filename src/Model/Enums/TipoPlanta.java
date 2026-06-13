package Model.Enums;

import java.util.List;

public enum TipoPlanta {
    // PRIMAVERA
    ALHO           ("Alho",            List.of(Estacoes.PRIMAVERA), 4,  40,  60,0),
    BATATA         ("Batata",          List.of(Estacoes.PRIMAVERA), 6,  50,  80,0),
    CHIRIVIA       ("Chirívia",        List.of(Estacoes.PRIMAVERA), 4,  20,  35,0),
    COUVE          ("Couve",           List.of(Estacoes.PRIMAVERA), 6,  70,  110,0),
    COUVE_FLOR     ("Couve-flor",      List.of(Estacoes.PRIMAVERA), 12, 80,  100,0),
    MORANGO        ("Morango",         List.of(Estacoes.PRIMAVERA), 8, 100, 120,4),
    VAGEM          ("Vagem",           List.of(Estacoes.PRIMAVERA), 10, 60,  40,3),
    // VERÃO
    MELAO          ("Melão",         List.of(Estacoes.VERAO),  12, 80,  120,0),
    TOMATE         ("Tomate",        List.of(Estacoes.VERAO),  11, 50,  75,4),
    MIRTILO        ("Mirtilo",       List.of(Estacoes.VERAO),  13, 80,  120,4),
    PIMENTA        ("Pimenta",       List.of(Estacoes.VERAO),  5,  40,  60,3),
    TRIGO          ("Trigo",         List.of(Estacoes.VERAO, Estacoes.OUTONO),  4,  10,  25,0),
    RABANETE     ("Rabanete",      List.of(Estacoes.VERAO),  6,  40,  60,0),
    LUPULO       ("Lúpulo",        List.of(Estacoes.VERAO),  11, 60,  90,1),
    MILHO        ("Milho",         List.of(Estacoes.VERAO, Estacoes.OUTONO),  14, 150, 190,4),
    REPOLHO_ROXO ("Repolho Roxo",  List.of(Estacoes.VERAO),  9,  100, 140,0),
    CARAMBOLA    ("Carambola",      List.of(Estacoes.VERAO),13, 100,75,0),
    // OUTONO
    BERINJELA      ("Berinjela",      List.of(Estacoes.OUTONO),5,  20,  40,5),
    ABOBORA        ("Abóbora",        List.of(Estacoes.OUTONO),13, 100, 160,0),
    ACELGA_CHINESA ("Acelga Chinesa", List.of(Estacoes.OUTONO),4,  50,  80,0),
    INHAME         ("Inhame",         List.of(Estacoes.OUTONO),10, 60,  100,0),
    OXICOCO        ("Oxicoco",        List.of(Estacoes.OUTONO),7,  240, 360,5),
    AMARANTO       ("Amaranto",       List.of(Estacoes.OUTONO),7,  70,  110,0),
    UVA            ("Uva",            List.of(Estacoes.OUTONO),10, 60,  80,3),
    ABACAXI  ("Abacaxi",  List.of(Estacoes.VERAO),  7, 100, 300,7);



    private final String nome;
    private final List<Estacoes> estacao;
    private final int diasCrescimento;
    private final int precoCompra;
    private final int precoVenda;
    private final int diasRegenera;

    TipoPlanta(String nome, List<Estacoes> estacao, int diasCrescimento, int precoCompra, int precoVenda, int diasRegenera) {
        this.nome = nome;
        this.estacao = estacao;
        this.diasCrescimento = diasCrescimento;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.diasRegenera = diasRegenera;
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

    public List<Estacoes> getEstacao() {
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