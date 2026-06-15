package Controller;

import Model.Fazenda;
import Model.Planta;

public class FazendaController {
    private Fazenda fazenda;

    public FazendaController(Fazenda fazenda){
        this.fazenda = fazenda;
    }

    public boolean plantar(Planta planta){
        if(fazenda.getNivel().getCapacidade() == fazenda.getPlantasPlantadas().size()){
            System.out.println("Limite de plantação máxima atingido");
            return false;
        }
        fazenda.plantarPlanta(planta);
        return true;
    }

    public void colher(Planta planta){
        if(planta.prontoParaColheita(planta)){
            fazenda.guardarPlanta(planta);
            fazenda.removerPlanta(planta);
        }
        System.out.println("Ainda não é possível colher!");
    }

    public void passarDia(){
        fazenda.passarDia();
    }

    public void passarEstacao(){
        passarEstacao();
    }
}
