package Model;

import Model.Enums.TipoAnimal;
import Model.Enums.TipoPlanta;

import java.util.ArrayList;
import java.util.List;

public class Rancho {

    private List<TipoAnimal> catalogo;

    public Rancho(List<TipoAnimal> catalogo) {
        setCatalogo(new ArrayList<>(List.of(TipoAnimal.values())));
    }

    public List<TipoAnimal> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<TipoAnimal> catalogo) {
        this.catalogo = catalogo;
    }

    public boolean comprar(Jogador jogador, TipoAnimal tipoAnimal, int quantidade){
        int totalApagar = tipoAnimal.getPrecoCompra() * quantidade;
        if (jogador.getDinheiro() < totalApagar){
            return false;
        }

        return true;
    }
}
