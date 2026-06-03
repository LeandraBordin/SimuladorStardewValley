package Controller;

import Model.Enums.CorCabelo;
import Model.Enums.CorOlhos;
import Model.Enums.Estacoes;
import Model.Jogador;
import Model.Loja;
import View.LojaView;

public class LojaController {
    public static void main(String[] args) {
         Loja loja = new Loja();
         LojaView lojaView = new LojaView();
        Jogador leandra = new Jogador("Leandra", CorCabelo.CASTANHO, CorOlhos.VERDE);
         lojaView.exibirMensagemBoasVindas(leandra);
         lojaView.exibirLoja(loja.getCatalogo());
//         lojaView.exibirLoja(loja.getCatalogoPorEstacao(Estacoes.PRIMAVERA));
    }

}
