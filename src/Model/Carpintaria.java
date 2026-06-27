package Model;

import Model.Enums.TipoConstrucao;

import java.util.ArrayList;
import java.util.List;

public class Carpintaria {
    private List<TipoConstrucao> catalogo;

    public Carpintaria(List<TipoConstrucao> catalogo) {
        this.catalogo = new ArrayList<>(List.of(TipoConstrucao.values()));
    }

    public List<TipoConstrucao> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<TipoConstrucao> catalogo) {
        this.catalogo = catalogo;
    }

    public void aprimorar(TipoConstrucao construcao,Jogador jogador){
        if(jogador.getDinheiro() > construcao.getValor()){
        }
    }
}
