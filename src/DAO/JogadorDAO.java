package DAO;

import Model.Jogador;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class JogadorDAO implements Serializable {

    private HashMap<Integer, Jogador> dados;
    private static JogadorDAO instance;
    private static int chave;
    private static final String ARQUIVO = "dadosJogador.dat";

    private JogadorDAO() throws IOException, ClassNotFoundException {
        if (GerenciadorArquivos.existeArquivo(ARQUIVO)) {
            dados = (HashMap<Integer, Jogador>) GerenciadorArquivos.lerArquivo(ARQUIVO);
            TreeSet<Integer> chaves = new TreeSet<>(dados.keySet());
            chave = chaves.last();
        } else {
            GerenciadorArquivos.criarArquivo(ARQUIVO);
            dados = new HashMap<>();
            chave = 0;
        }
    }

    public static JogadorDAO getInstance() throws IOException, ClassNotFoundException {
        if (instance == null) {
            instance = new JogadorDAO();
        }
        return instance;
    }

    public boolean add(Jogador jogador) throws IOException {
        if (dados.size() >= 3){
            return false;
        }
        chave++;
        jogador.setId(chave);
        Object o = dados.put(chave, jogador);
        GerenciadorArquivos.gravarArquivo(dados, ARQUIVO);
        return true;
    }

    public boolean remove(Jogador jogador) throws IOException {
        Object o = dados.remove(jogador.getId());
        GerenciadorArquivos.gravarArquivo(dados, ARQUIVO);
        return (o instanceof Jogador);
    }

    public Jogador find(int id) {
        return dados.get(id);
    }

    public Collection<Jogador> getDados() {
        return dados.values();
    }

    public HashMap<Integer, Jogador> getMap() {
        return this.dados;
    }
}