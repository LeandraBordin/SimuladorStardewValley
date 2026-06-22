package Controller;

import Model.Enums.Estacoes;
import Model.Fazenda;

public class TempoController {

    public void passarDia(Fazenda fazenda){
        fazenda.passarDia();
    }

    public void passarEstacao(Fazenda fazenda){
        fazenda.passarEstacao();
    }
}
