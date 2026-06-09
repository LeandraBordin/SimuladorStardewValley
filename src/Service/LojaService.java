package Service;

import Model.Enums.Estacoes;
import Model.Enums.TipoPlanta;
import Model.Loja;

import java.util.List;

public class LojaService {
    private Loja loja;

    public LojaService(Loja loja) {
        this.loja = loja;
    }

    public List<TipoPlanta> listarCatalogo(){
        return loja.getCatalogo();
    }

    public List<TipoPlanta> listarCatalogoPorEstacao(Estacoes estacao){
        return loja.getCatalogoPorEstacao(estacao);
    }
}
