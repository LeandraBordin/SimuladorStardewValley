package Controller;

import Model.Fazenda;

public class TempoController {

    public void passarDia(Fazenda fazenda){
        fazenda.passarDia();

    }

    public int passarEstacao(Fazenda fazenda){
        return fazenda.passarEstacao();
    }
}
