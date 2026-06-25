package Model;

import java.io.Serializable;

public class Jogador implements Serializable, Comparable<Jogador> {
    private int id;
    private String nome;
    private int dinheiro;
    private Fazenda fazenda;


    public Jogador(String nome) {
        setNome(nome);
       setDinheiro(500);
    }
    public void adicionarFazenda(Fazenda fazenda){
        setFazenda(fazenda);
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
        if (dinheiro >= 0){
            this.dinheiro = dinheiro;
        }
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dinheiro=" + dinheiro +
                ", fazenda=" + fazenda +
                '}';
    }
    @Override
    public int compareTo(Jogador o) {
        return this.nome.compareTo(o.nome);
    }
}
