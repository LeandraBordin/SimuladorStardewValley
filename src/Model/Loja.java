package Model;

import Model.Enums.Estacoes;
import Model.Enums.TipoPlanta;

import java.util.ArrayList;
import java.util.List;

public class Loja {

    List<TipoPlanta> catalogo;

    public Loja() {
        this.catalogo = new ArrayList<>(List.of(TipoPlanta.values()));;
    }

    public List<TipoPlanta> getCatalogo() {
        return catalogo;
    }

    public List<TipoPlanta> getCatalogoPorEstacao(Estacoes estacao){
        List<TipoPlanta> catalogoPorEstacao = new ArrayList<>();
        for (TipoPlanta planta : catalogo){
            if (planta.getEstacao().contains(estacao)){
                catalogoPorEstacao.add(planta);
            }
        }
        return catalogoPorEstacao;
    }
}
