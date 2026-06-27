package Model;

import Model.Enums.TipoConstrucao;

import java.util.ArrayList;
import java.util.List;

public class Carpintaria {
    private List<TipoConstrucao> catalogo;

    public Carpintaria() {
        this.catalogo = new ArrayList<>(List.of(TipoConstrucao.values()));
    }

    public List<TipoConstrucao> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<TipoConstrucao> catalogo) {
        this.catalogo = catalogo;
    }

    public boolean construir(TipoConstrucao construcao, Jogador jogador){
        Construcao construcaoAdicionada = new Construcao(construcao);
        if (jogador.getDinheiro() > construcao.getValor()){
            jogador.setDinheiro(jogador.getDinheiro() - construcao.getValor());
            jogador.getFazenda().adicionarConstrucao(construcaoAdicionada);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean aprimorar(Construcao construcao,Jogador jogador){
        if(construcao.getTipo() == TipoConstrucao.CELEIRO_LUXO || construcao.getTipo() == TipoConstrucao.GALINHEIRO_LUXO){
            System.out.println("Construção já está maximizada");
            return false;
        }
        TipoConstrucao prox = getProximoNivel(construcao.getTipo());
        if (jogador.getDinheiro() < prox.getValor()){
            throw new IllegalStateException("Dinheiro insuficiente para aprimorar para " + prox.getNome());
        }
        jogador.setDinheiro(jogador.getDinheiro() - prox.getValor());
        construir(prox,jogador);
       jogador.getFazenda().getConstrucoes().remove(construcao);
       return true;
    }

    public TipoConstrucao getProximoNivel(TipoConstrucao construcao) {
        TipoConstrucao[] valores = TipoConstrucao.values();
        for (int i = 0; i < valores.length - 1; i++) {
            if (valores[i] == construcao) {
                return valores[i + 1];
            }
        }
        return null;
    }
}
