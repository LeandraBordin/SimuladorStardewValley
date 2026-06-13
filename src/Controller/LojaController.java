package Controller;

import Model.Enums.Estacoes;
import Model.Loja;
import View.LojaView;

public class LojaController {
        private Loja loja;
        private LojaView lojaView;

    public LojaController(Loja loja, LojaView lojaView) {
        this.loja = loja;
        this.lojaView = lojaView;
    }

    public void exibirCatalogo(){
        lojaView.exibirLoja(loja.getCatalogo());
    }
    public void exibirCatalogoPorEstacao(Estacoes estacao){
        lojaView.exibirLoja(loja.getCatalogoPorEstacao(estacao));
    }
}
