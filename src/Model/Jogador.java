package Model;

import Model.Enums.CorCabelo;
import Model.Enums.CorOlhos;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private int dinheiro;
    private CorCabelo corCabelo;
    private CorOlhos corOlhos;
    private List<Fazenda> fazendas;


    public Jogador(String nome,CorCabelo corCabelo,CorOlhos corOlhos) {
        setNome(nome);
        setCorCabelo(corCabelo);
        setCorOlhos(corOlhos);
       setDinheiro(500);
       setFazendas(new ArrayList<>());
    }
    public void adicionarFazenda(Fazenda fazenda){
        fazendas.add(fazenda);
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(int dinheiro) {
        if (dinheiro > 0){
            this.dinheiro = dinheiro;
        }
    }

    public List<Fazenda> getFazendas() {
        return fazendas;
    }

    public void setFazendas(List<Fazenda> fazendas) {
        this.fazendas = fazendas;
    }

    public CorCabelo getCorCabelo() {
        return corCabelo;
    }

    public void setCorCabelo(CorCabelo corCabelo) {
        this.corCabelo = corCabelo;
    }

    public CorOlhos getCorOlhos() {
        return corOlhos;
    }

    public void setCorOlhos(CorOlhos corOlhos) {
        this.corOlhos = corOlhos;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", dinheiro=" + dinheiro +
                ", corCabelo=" + corCabelo +
                ", corOlhos=" + corOlhos +
                ", fazendas=" + fazendas +
                '}';
    }
}
